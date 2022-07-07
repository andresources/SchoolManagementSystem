package com.pharma.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog {
    public static ProgressDialog showProgressDialog(Context cnt){
        ProgressDialog pd=new ProgressDialog(cnt);
        pd.setTitle("Please wait,data is being submitted...");
        return pd;
    }

}
