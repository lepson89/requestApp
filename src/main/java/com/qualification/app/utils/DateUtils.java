package com.qualification.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DATE_WITH_TIME = "yyyy-MM-dd hh-ss";

    public static String getStringDateNow(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_WITH_TIME);
        return simpleDateFormat.format(new Date());
    }
}
