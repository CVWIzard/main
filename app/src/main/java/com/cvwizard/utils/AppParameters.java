package com.cvwizard.utils;

import java.util.regex.Pattern;

/**
 * Created by Pavel on 6/20/2016.
 */

public class AppParameters {
    public static boolean DEBUG = true;
    public static String FILE_NAME = "";
    public static String FILE_FOLDER_NAME = "CV.WIZARD_OUTPUT";
    public static final Pattern sPatternNumbers
            = Pattern.compile("[0-9 ]+");


}
