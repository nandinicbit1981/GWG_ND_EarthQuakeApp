package com.example.android.quakereport.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nandpa on 4/26/18.
 */

public class DateUtil {
    static SimpleDateFormat sdfDate = new SimpleDateFormat("MMM DD, yyyy");
    static SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a");
    public static String formatDate(long millis) {
        Date date = new Date(millis);
        return sdfDate.format(date);
    }

    public static String formatTime(long millis) {
        Date date = new Date(millis);
        return sdfTime.format(date);
    }

}
