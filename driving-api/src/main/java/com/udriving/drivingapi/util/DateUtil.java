package com.udriving.drivingapi.util;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

/**
 * 时间工具类
 */
public class DateUtil {
    /**
     * 一分钟秒数
     */
    public static final byte ONE_MINUTES_HAVE_SECOND = 60;
    /**
     * 一小时分钟数
     */
    public static final byte ONE_HOURS_HAVE_MINUTES = 60;
    /**
     * 一天小时数
     */
    public static final byte ONE_DAY_HAVE_HOURS = 24;

    /**
     * 一秒毫秒数
     */
    public static final short ONE_SECOND = 1000;
    /**
     * 一分钟毫秒数
     */
    public static final int ONE_MINUTES = ONE_SECOND * ONE_MINUTES_HAVE_SECOND;
    /**
     * 一小时毫秒数
     */
    public static final int ONE_HOURS = ONE_MINUTES * ONE_HOURS_HAVE_MINUTES;
    /**
     * 一天毫秒数
     */
    public static final int ONE_DAY = ONE_HOURS * ONE_DAY_HAVE_HOURS;

    /**
     * 计算天数
     *
     * @param timestamp1 毫秒时间戳1
     * @param timestamp2 毫秒时间戳2
     * @return
     */
    public static final short countDays(long timestamp1, long timestamp2) {
        return (short) ((timestamp1 - timestamp2) / ONE_DAY);
    }

    /**
     * 将毫秒时间戳格式化为：YYYY.MM.DD HH:MM:SS格式
     *
     * @param timestamp 毫秒时间戳
     * @return 时间字符串表示
     */
    public static final String formatYYYYMMDDHHMMSS(long timestamp) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timestamp);
        StringBuilder dateString = new StringBuilder();
        dateString.append(gregorianCalendar.get(YEAR));
        dateString.append(".");
        dateString.append(gregorianCalendar.get(MONTH) + 1);
        dateString.append(".");
        dateString.append(gregorianCalendar.get(DAY_OF_MONTH));
        dateString.append(" ");
        dateString.append(gregorianCalendar.get(HOUR_OF_DAY));
        dateString.append(":");
        dateString.append(gregorianCalendar.get(MINUTE));
        dateString.append(":");
        dateString.append(gregorianCalendar.get(SECOND));
        return dateString.toString();
    }
}
