package com.example.administrator.mybasewithtitle.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StringUtil {

    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMON_DATE_FORMAT_PRE = "yyyy-MM-dd";
    public static final String COMMON_DATE_FORMAT_SPECIAL = "yyyy-MM-dd  HH:mm";

    public static String date2String(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date string2Date(String dateString, String format) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String null2EmptyString(String message) {
        return message == null ? "" : message;
    }

    /**
     * 格式化时间
     *
     * @param time   时间戳字符串
     * @param format 格式
     * @return
     */
    public static String processTime(String time, String format) {

        if (TextUtils.isEmpty(time)) {
            return "";
        }
        long result = Long.valueOf(time);

        if (time.length() == 10) {
            result = result * 1000;
        }

        SimpleDateFormat dataFormat = new SimpleDateFormat(format);
        dataFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Date date = new Date(result);
        return dataFormat.format(date);
    }

    public static String processTime(long time){
        SimpleDateFormat dataFormat = new SimpleDateFormat(COMMON_DATE_FORMAT);
        dataFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Date date = new Date(time);
        return dataFormat.format(date);
    }

    /**
     * 将double型转换成String
     *
     * @param number  转换数
     * @param decimal 小数位数
     * @return
     */
    public static String double2String(double number, int decimal) {
        String strTotal;
        if (decimal == 1) {
            DecimalFormat format = new DecimalFormat("0.0");
            strTotal = format.format(number);
        } else {
            DecimalFormat format = new DecimalFormat("0.00");
            strTotal = format.format(number);
        }
        return strTotal;
    }

    //获取价格格式字符串
    public static SpannableStringBuilder getPrice(double price) {

        String string = String.format("%.2f", price);
        int index = string.indexOf(".");

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("¥");
        stringBuilder.append(string.substring(0, index));
        stringBuilder.append(string.substring(index, string.length()));

        stringBuilder.setSpan(
                new AbsoluteSizeSpan(40),
                1, index + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

    public static SpannableStringBuilder getMaxPrice(double price) {

        String string = String.format("%.2f", price);
        int index = string.indexOf(".");

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("—");
        stringBuilder.append(string.substring(0, index));
        stringBuilder.append(string.substring(index, string.length()));

        stringBuilder.setSpan(
                new AbsoluteSizeSpan(40),
                1, index + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }
}
