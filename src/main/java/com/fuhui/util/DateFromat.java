package com.fuhui.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFromat {

    /**
     * 将时间戳格式的日期转换
     * @param date
     * @return
     */
    public static String datetimeChange(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = sdf.format(date);
        return string;
    }


    public static Date parse(String str, String pattern, Locale locale) {
        if (str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date date, String pattern, Locale locale) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }


}
