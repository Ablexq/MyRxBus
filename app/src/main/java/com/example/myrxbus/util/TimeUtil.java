package com.example.myrxbus.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String time) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
        return String.valueOf(ts);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateHms(long timeMillis) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDateHm(long timeMillis) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    /*
     * 将毫秒转时分秒
     * */
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

}

