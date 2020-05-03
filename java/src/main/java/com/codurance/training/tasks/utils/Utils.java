package com.codurance.training.tasks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Integer tryParseInt(String candidate){
        try {
            return Integer.valueOf(candidate.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Date tryParseDate(String dateString)  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToString(Date date)  {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

}
