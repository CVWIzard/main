package com.cvwizard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cvwizard.app.R;
import com.cvwizard.storage.StorageClass;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;


public class WelcomeScreen extends AppCompatActivity {

    Button mStartBtn;
    public static Context context;
    final Activity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        context = this;
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "CV.Wizard");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            StorageClass.saveValues("outfolderpath",Environment.getExternalStorageDirectory() +
                    File.separator + "CV.Wizard");
        } else {
            // Do something else on failure
        }


        mStartBtn = (Button) findViewById(R.id.startBtn);

        mStartBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setBackground(ContextCompat.getDrawable(context,R.drawable.signin_hover));
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.setBackground(ContextCompat.getDrawable(context,R.drawable.signin_default));
                        v.invalidate();
                        startWithLinkedIn();
                        break;
                    }
                }
                return false;
            }
        });

      /*  mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                // writtenToFile("FB_KEY_HASH.txt",
                // Base64.encodeToString(md.digest(),
                // Base64.DEFAULT).toString(), false);


                Log.v("Welcomecreen",
                        "KeyHash:"
                                + Base64.encodeToString(md.digest(),
                                Base64.DEFAULT) + " " + getPackageName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().popBackStack();
    }

    private void startWithLinkedIn(){
    LISessionManager.getInstance(getApplicationContext()).init(thisActivity, buildScope(), new AuthListener() {
        @Override
        public void onAuthSuccess() {
            String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,picture-url,email-address)";
            APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
            apiHelper.getRequest(WelcomeScreen.this, url, new ApiListener() {
                @Override
                public void onApiSuccess(ApiResponse apiResponse) {
                    // Success!
                    JSONObject responseObj = apiResponse.getResponseDataAsJson();
                    Intent intent = new Intent(getApplicationContext(),AccountLobby.class);
                    Log.i("Linkedin response",apiResponse.getResponseDataAsString());
                    try {
                        intent.putExtra("fullName",responseObj.getString("firstName") + " " + responseObj.getString("lastName"));
                        intent.putExtra("pictureUrl",responseObj.getString("pictureUrl"));
                        StorageClass.saveValues("fullname",responseObj.getString("firstName") + " " + responseObj.getString("lastName"));
                        StorageClass.saveValues("email",responseObj.getString("emailAddress"));
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(WelcomeScreen.context,"Response retrival failed (json error)",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onApiError(LIApiError liApiError) {
                    // Error making GET request!
                    try {
                        Log.e("LinkedIn",liApiError.getApiErrorResponse().getMessage());
                        Toast.makeText(WelcomeScreen.context,"Response retrival failed (response error: " + liApiError.getApiErrorResponse().getMessage() + " )",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Intent intent = new Intent(getApplicationContext(),AccountLobby.class);
                        startActivity(intent);
                    }
                }
            });


            Toast.makeText(WelcomeScreen.this, "Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthError(LIAuthError error) {
            Toast.makeText(WelcomeScreen.this, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
            Log.i(this.getClass().getSimpleName(),"error: " + error.toString());
        }
    },true);

 }

    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE,Scope.R_EMAILADDRESS, Scope.W_SHARE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        SplashFragment splashFragment = new SplashFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_welcome_screen,splashFragment,"splash").addToBackStack("splash").commitAllowingStateLoss();
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);


    }



    public static class SplashFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.splash_screen,container,false);
        }
    }
}
