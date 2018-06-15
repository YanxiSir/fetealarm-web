package com.yanxisir.fetealarm.utils;

import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.Date;

/**
 * @author YanxiSir
 * @since 2018/6/15
 */
public class DateUtils {

    public static Date setCurYear(Date date) {
        String curYear = LocalDate.now().toString("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, Integer.parseInt(curYear));
        return calendar.getTime();
    }
}
