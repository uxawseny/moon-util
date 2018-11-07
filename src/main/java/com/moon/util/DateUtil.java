package com.moon.util;

import com.moon.enums.Const;
import com.moon.lang.LongUtil;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.moon.lang.ThrowUtil.noInstanceError;

/**
 * @author benshaoye
 * @date 2018/9/11
 */
public final class DateUtil {

    public final static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss SSS";
    public final static String yyyy_MM_dd_hh_mm_ss_SSS = "yyyy-MM-dd hh:mm:ss SSS";
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyy_MM_dd_hh_mm_ss = "yyyy-MM-dd hh:mm:ss";
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public final static String yyyy_MM_dd_hh_mm = "yyyy-MM-dd hh:mm";
    public final static String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    public final static String yyyy_MM_dd_hh = "yyyy-MM-dd hh";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyy_MM = "yyyy-MM";
    public final static String yyyy = "yyyy";

    private final static int[] PARSE_FIELD_OF_CALENDAR = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,
        Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND};

    private DateUtil() {
        noInstanceError();
    }

    /*
     * -------------------------------------------------------------------------
     * now
     * -------------------------------------------------------------------------
     */

    public final static long now() {
        return System.currentTimeMillis();
    }

    public final static Date nowDate() {
        return new Date();
    }

    public final static Date nowSqlDate() {
        return new java.sql.Date(now());
    }

    public final static Time nowSqlTime() {
        return new java.sql.Time(now());
    }

    public final static Calendar nowCalendar() {
        return Calendar.getInstance();
    }

    /*
     * -------------------------------------------------------------------------
     * next and previous
     * -------------------------------------------------------------------------
     */

    public final static Calendar nextYear(Calendar value) {
        return plusYears(value, 1);
    }

    public final static Calendar nextMonth(Calendar value) {
        return plusMonths(value, 1);
    }

    public final static Calendar nextDay(Calendar value) {
        return plusDays(value, 1);
    }

    public final static Calendar nextHour(Calendar value) {
        return plusHours(value, 1);
    }

    public final static Calendar nextMinute(Calendar value) {
        return plusMinutes(value, 1);
    }

    public final static Calendar nextSecond(Calendar value) {
        return plusSeconds(value, 1);
    }

    public final static Calendar nextMillisecond(Calendar value) {
        return plusMilliSeconds(value, 1);
    }

    public final static Calendar previousYear(Calendar value) {
        return minusYears(value, 1);
    }

    public final static Calendar previousMonth(Calendar value) {
        return minusMonths(value, 1);
    }

    public final static Calendar previousDay(Calendar value) {
        return minusDays(value, 1);
    }

    public final static Calendar previousHour(Calendar value) {
        return minusHours(value, 1);
    }

    public final static Calendar previousMinute(Calendar value) {
        return minusMinutes(value, 1);
    }

    public final static Calendar previousSecond(Calendar value) {
        return minusSeconds(value, 1);
    }

    public final static Calendar previousMillisecond(Calendar value) {
        return minusMilliSeconds(value, 1);
    }

    /*
     * -------------------------------------------------------------------------
     * plus and minus
     * -------------------------------------------------------------------------
     */

    public final static Calendar plusYears(Calendar value, int amount) {
        return setMonth(value, getYear(value) + amount);
    }

    public final static Calendar plusMonths(Calendar value, int amount) {
        return setMonth(value, getMonth(value) + amount);
    }

    public final static Calendar plusDays(Calendar value, int amount) {
        return setDayOfMonth(value, getDayOfMonth(value) + amount);
    }

    public final static Calendar plusHours(Calendar value, int amount) {
        return setMonth(value, getHour(value) + amount);
    }

    public final static Calendar plusMinutes(Calendar value, int amount) {
        return setMonth(value, getMinute(value) + amount);
    }

    public final static Calendar plusSeconds(Calendar value, int amount) {
        return setDayOfMonth(value, getSecond(value) + amount);
    }

    public final static Calendar plusMilliSeconds(Calendar value, int amount) {
        return setDayOfMonth(value, getMillisecond(value) + amount);
    }

    public final static Calendar minusYears(Calendar value, int amount) {
        return setMonth(value, getYear(value) - amount);
    }

    public final static Calendar minusMonths(Calendar value, int amount) {
        return setMonth(value, getMonth(value) - amount);
    }

    public final static Calendar minusDays(Calendar value, int amount) {
        return setDayOfMonth(value, getDayOfMonth(value) - amount);
    }

    public final static Calendar minusHours(Calendar value, int amount) {
        return setMonth(value, getHour(value) - amount);
    }

    public final static Calendar minusMinutes(Calendar value, int amount) {
        return setMonth(value, getMinute(value) - amount);
    }

    public final static Calendar minusSeconds(Calendar value, int amount) {
        return setDayOfMonth(value, getSecond(value) - amount);
    }

    public final static Calendar minusMilliSeconds(Calendar value, int amount) {
        return setDayOfMonth(value, getMillisecond(value) - amount);
    }

    /*
     * -------------------------------------------------------------------------
     * getters and setters
     * -------------------------------------------------------------------------
     */

    public final static Calendar setYear(Calendar value, int amount) {
        return set(value, Calendar.YEAR, amount);
    }

    public final static Calendar setMonth(Calendar value, int amount) {
        return set(value, Calendar.MONTH, amount);
    }

    public final static Calendar setDayOfMonth(Calendar value, int amount) {
        return set(value, Calendar.DAY_OF_MONTH, amount);
    }

    public final static Calendar setHour(Calendar value, int amount) {
        return set(value, Calendar.HOUR_OF_DAY, amount);
    }

    public final static Calendar setMinute(Calendar value, int amount) {
        return set(value, Calendar.MINUTE, amount);
    }

    public final static Calendar setSecond(Calendar value, int amount) {
        return set(value, Calendar.SECOND, amount);
    }

    public final static Calendar setMillisecond(Calendar value, int amount) {
        return set(value, Calendar.MILLISECOND, amount);
    }

    public final static Calendar set(Calendar value, int field, int amount) {
        Calendar current = copy(value);
        current.set(field, field == Calendar.MONTH ? amount - 1 : amount);
        return current;
    }

    public final static int getYear(Calendar value) {
        return get(value, Calendar.YEAR);
    }

    public final static int getMonth(Calendar value) {
        return get(value, Calendar.MONTH);
    }

    public final static int getDayOfMonth(Calendar value) {
        return get(value, Calendar.DAY_OF_MONTH);
    }

    public final static int getHour(Calendar value) {
        return get(value, Calendar.HOUR_OF_DAY);
    }

    public final static int getMinute(Calendar value) {
        return get(value, Calendar.MINUTE);
    }

    public final static int getSecond(Calendar value) {
        return get(value, Calendar.SECOND);
    }

    public final static int getMillisecond(Calendar value) {
        return get(value, Calendar.MILLISECOND);
    }

    public final static int get(Calendar value, int field) {
        int val = value.get(field);
        return field == Calendar.MONTH ? val + 1 : val;
    }

    /*
     * -------------------------------------------------------------------------
     * copies
     * -------------------------------------------------------------------------
     */

    public final static Calendar copy(Calendar value) {
        return toCalendar(value.getTimeInMillis());
    }

    public final static Date copy(Date value) {
        return toDate(toCalendar(value));
    }

    /*
     * -------------------------------------------------------------------------
     * formatter
     * -------------------------------------------------------------------------
     */

    public final static String format() {
        return format(yyyy_MM_dd_HH_mm_ss);
    }

    public final static String format(String pattern) {
        return format(new Date(), pattern);
    }

    public final static String format(Date date, String pattern) {
        return format(date, new SimpleDateFormat(pattern));
    }

    public final static String format(Date date, DateFormat formatter) {
        return formatter.format(date);
    }

    public final static String format(Date date) {
        return format(date, yyyy_MM_dd_HH_mm_ss);
    }

    public final static String format(Calendar date) {
        return format(date.getTime());
    }

    public final static String format(Calendar date, String pattern) {
        return format(date.getTime(), pattern);
    }

    public final static String format(Calendar date, DateFormat formatter) {
        return format(date.getTime(), formatter);
    }

    /*
     * -------------------------------------------------------------------------
     * parsers
     * -------------------------------------------------------------------------
     */

    /**
     * 解析成 Calendar 日期
     *
     * @param dateString 要求符合格式 "yyyy-MM-dd HH:mm:ss SSS" 的一个或多个字段（超出部分将忽略）
     */
    public static Calendar parseToCalendar(String dateString) {
        dateString = dateString == null ? Const.EMPTY : dateString.trim();
        int strLen = dateString.length();
        int idx = 0;
        if (strLen > idx) {

            List<String> fieldsValue = new ArrayList();
            StringBuilder sb = new StringBuilder();
            char ch;
            boolean moreBlank = false;

            do {
                ch = dateString.charAt(idx++);
                if (ch > 47 && ch < 58) {
                    sb.append(ch);
                    moreBlank = false;
                } else if (!moreBlank) {
                    fieldsValue.add(sb.toString());
                    sb.setLength(0);
                    moreBlank = true;
                }
            } while (strLen > idx);

            if (sb.length() > 0) {
                fieldsValue.add(sb.toString());
            }

            int size = fieldsValue.size();
            if (size == 0) {
                throw new IllegalArgumentException("Must input date string.");
            }
            int length = PARSE_FIELD_OF_CALENDAR.length;

            length = size > length ? length : size;
            Calendar calendar = Calendar.getInstance();
            calendar.clear();

            for (int i = 0; i < length; i++) {
                int currField = PARSE_FIELD_OF_CALENDAR[i];
                if (currField == Calendar.MONTH) {
                    calendar.set(currField, Integer.parseInt(fieldsValue.get(i)) - 1);
                } else {
                    calendar.set(currField, Integer.parseInt(fieldsValue.get(i)));
                }
            }

            return calendar;
        }

        return null;
    }

    public static Date parse(String dateString, String patten) {
        return parse(dateString, new SimpleDateFormat(patten));
    }

    public static Date parse(String dateString, DateFormat patten) {
        try {
            return patten.parse(dateString);
        } catch (ParseException | NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /*
     * -------------------------------------------------------------------------
     * converters
     * -------------------------------------------------------------------------
     */

    public static Time toSqlTime(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Time) {
            return (Time) value;
        }
        try {
            return new Time(toCalendar(value).getTimeInMillis());
        } catch (Throwable t) {
            throw new IllegalArgumentException("can not converter value to java.sql.Time");
        }
    }

    public static Timestamp toTimestamp(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Timestamp) {
            return (Timestamp) value;
        }
        try {
            return new Timestamp(toCalendar(value).getTimeInMillis());
        } catch (Throwable t) {
            throw new IllegalArgumentException("can not converter value to java.sql.Timestamp");
        }
    }

    public static java.sql.Date toSqlDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof java.sql.Date) {
            return (java.sql.Date) value;
        }
        try {
            return new java.sql.Date(toCalendar(value).getTimeInMillis());
        } catch (Throwable t) {
            throw new IllegalArgumentException("can not converter value to java.sql.Date");
        }
    }

    /*
     * to java.util.Date ============================================
     */

    public static Date toDate(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        try {
            return toCalendar(value).getTime();
        } catch (Throwable t) {
            throw new IllegalArgumentException("can not converter value to java.util.Date");
        }
    }

    /**
     * 解析成 Date 日期
     *
     * @param arguments 用 int 表示的年、月、日、时、分、秒、毫秒等一个或多个字段按顺序传入
     */
    public static Date toDate(int... arguments) {
        return toDate(toCalendar(arguments));
    }

    /**
     * java.util.Calendar to java.util.Date
     *
     * @param value
     * @return
     */
    public final static Date toDate(Calendar value) {
        return value == null ? null : value.getTime();
    }

    /**
     * 解析成 Date 日期
     *
     * @param dateString 要求符合格式 "yyyy-MM-dd HH:mm:ss SSS" 的一个或多个字段（超出部分将忽略）
     */
    public static Date toDate(String dateString) {
        return toDate(parseToCalendar(dateString));
    }

    /**
     * 解析成 Date 日期
     *
     * @param arguments 用 String 表示的年、月、日、时、分、秒、毫秒等一个或多个字段按顺序传入
     */
    public static Date toDate(String... arguments) {
        return toDate(toCalendar(arguments));
    }

    /*
     * toCalendar ============================================
     */

    /**
     * 解析成 Calendar 日期
     *
     * @param arguments 用 String 表示的年、月、日、时、分、秒、毫秒等一个或多个字段按顺序传入
     */
    public static Calendar toCalendar(String... arguments) {
        int size = arguments.length;
        int length = PARSE_FIELD_OF_CALENDAR.length;

        size = size > length ? length : size;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        int i = 0;
        for (; i < size; i++) {
            int currField = PARSE_FIELD_OF_CALENDAR[i];
            if (currField == Calendar.MONTH) {
                calendar.set(currField, Integer.parseInt(arguments[i]) - 1);
            } else {
                calendar.set(currField, Integer.parseInt(arguments[i]));
            }
        }
        for (; i < length; i++) {
            int currField = PARSE_FIELD_OF_CALENDAR[i];
            if (currField == Calendar.DAY_OF_MONTH) {
                calendar.set(currField, 1);
            } else {
                calendar.set(currField, 0);
            }
        }
        return calendar;
    }

    public final static Calendar toCalendar(Number value) {
        return value instanceof Long ? toCalendar(value.longValue()) : toCalendar(value.intValue());
    }

    public final static Calendar toCalendar(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return calendar;
    }

    public final static Calendar toCalendar(Date date) {
        return toCalendar(date.getTime());
    }

    public final static Calendar toCalendar(LocalDate date) {
        return toCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0);
    }

    public final static Calendar toCalendar(LocalDateTime date) {
        return toCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute(), date.getSecond());
    }

    public final static Calendar toCalendar(int... values) {
        final int len = values.length, max = 6;
        int i = 0;
        Calendar calendar = Calendar.getInstance();
        switch (len) {
            case 0:
                return calendar;
            case 1:
                calendar.set(Calendar.YEAR, values[i++]);
                break;
            case 2:
                calendar.set(Calendar.YEAR, values[i++]);
                calendar.set(Calendar.MONTH, values[i++] - 1);
                break;
            case 3:
                calendar.set(values[i++], values[i++] - 1, values[i++]);
                break;
            case 4:
                int minutes = calendar.get(Calendar.MINUTE);
                calendar.set(values[i++], values[i++] - 1, values[i++], values[i++], 0);
                calendar.set(Calendar.MINUTE, minutes);
                break;
            case 5:
                calendar.set(values[i++], values[i++] - 1, values[i++], values[i++], values[i++]);
                break;
            case 6:
                calendar.set(values[i++], values[i++] - 1, values[i++], values[i++], values[i++], values[i++]);
            default:
                if (len > max) {
                    calendar.set(Calendar.MILLISECOND, values[i++]);
                }
                break;
        }
        return calendar;
    }

    public static Calendar toCalendar(CharSequence value) {
        String temp = value.toString();
        if (DetectUtil.isNumeric(temp)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(LongUtil.toLong(temp));
            return calendar;
        }
        return parseToCalendar(value.toString());
    }

    public static Calendar toCalendar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return ((Calendar) value);
        }
        if (value instanceof Date) {
            return toCalendar((Date) value);
        }
        if (value instanceof Number) {
            return toCalendar((Number) value);
        }
        if (value instanceof LocalDate) {
            return toCalendar((LocalDate) value);
        }
        if (value instanceof LocalDateTime) {
            return toCalendar((LocalDateTime) value);
        }
        if (value instanceof CharSequence) {
            return toCalendar((CharSequence) value);
        }
        if (value instanceof int[]) {
            return toCalendar((int[]) value);
        }
        if (value instanceof String[]) {
            return toCalendar((String[]) value);
        }
        throw new IllegalArgumentException("can not converter value to java.util.Date");
    }
}
