package com.cvwizard.Interfaces;

/**
 * Created by Pavel on 9/13/2017.
 */

public interface OnNewDataSet {
    void addToDataSet(int recourceID);
    void addToDataSet(String fileName);
    void removeFromDataSet(int position);
}
