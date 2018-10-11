package com.qualification.app.utils;

public class FileUtils {

    public static String prepareFileName(){
        return Constants.FILE_PREFIX + DateUtils.getStringDateNow() + Constants.FILE_SUFFIX;
    }
}
