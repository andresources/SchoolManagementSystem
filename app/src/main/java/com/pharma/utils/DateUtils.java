package com.pharma.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T = "yyyy-MM-dd";
    public static String SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_T1 = "yyyy/MM/dd";
    public static String SIMPLE_DATE_FORMAT_DAY_FIRST_AND_T = "dd/MM/yyyy";
    public static String SIMPLE_DATE_FORMAT_YEAR_FIRST_AND_TIME_24 = "dd MMM, yyyy";
    public static String SIMPLE_TIME_FORMAT_24_HOURS= "HH:mm:ss";
    public static String SIMPLE_TIME_FORMAT_AM_PM= "hh:mm a";

    public static String getDesiredDateFormat(String existFormat, String desiredFormat, String timeString) {
        String desiredDate = "";
        try {
            SimpleDateFormat desiredSDFormat = new SimpleDateFormat(desiredFormat);
            SimpleDateFormat existSDFormat = new SimpleDateFormat(existFormat);//HH:mm:ss
            Date date = existSDFormat.parse(timeString);
            desiredDate = desiredSDFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Errr",ex.getMessage()+"..");
            desiredDate = timeString;
        }
        return desiredDate;
    }
    public static String getDesiredTimeFormat(String existFormat, String desiredFormat, String timeString) {
        String desiredDate = "";
        try {
            SimpleDateFormat desiredSDFormat = new SimpleDateFormat(desiredFormat);
            SimpleDateFormat existSDFormat = new SimpleDateFormat(existFormat);//HH:mm:ss
            Date date = existSDFormat.parse(timeString);
            desiredDate = desiredSDFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Errr",ex.getMessage()+"..");
            desiredDate = timeString;
        }
        return desiredDate;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
