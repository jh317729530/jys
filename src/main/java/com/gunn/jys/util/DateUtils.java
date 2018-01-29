package com.gunn.jys.util;

import com.gunn.jys.constant.common.DatePattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {


    /*
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
//		DateFormat dateFormat = new SimpleDateFormat(pattern);
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 方法名: format</br>
     * 详述: 格式化日期</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2016年6月1日</br>
     *
     * @param date
     *            Date类型的日期
     * @param datePattern
     *            日期的格式 在类DatePattern里面有定义
     * @return
     */
    public static String format(Date date, DatePattern datePattern) {
//		DateFormat dateFormat = new SimpleDateFormat(datePattern.toString());
        return DateFormatUtils.format(date, datePattern.toString());
    }

    public static Date parse(String str, DatePattern pattern) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str, pattern.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * 方法名: add235959</br>
     * 详述: 把时间增加23h59m59s</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2017年3月9日</br>
     * @param date
     * @return
     */
    public static Date add235959(Date date) {
        if (null == date) {
            return null;
        }
        Date hourDate = org.apache.commons.lang3.time.DateUtils.addHours(date, 23);
        Date minDate = org.apache.commons.lang3.time.DateUtils.addMinutes(hourDate, 59);
        Date secondDate = org.apache.commons.lang3.time.DateUtils.addSeconds(minDate, 59);
        return secondDate;
    }

    /**
     * 方法名: firstDate</br>
     * 详述: 获取月份的第一天 </br>
     * 开发人员：yuanbao</br>
     * 创建时间：2017年3月13日</br>
     * @param date
     * @return
     */
    public static Date firstDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);

        return c.getTime();
    }

    /**
     * 方法名: endDate</br>
     * 详述: 获取月份的最后一天 </br>
     * 开发人员：yuanbao</br>
     * 创建时间：2017年3月13日</br>
     * @param date
     * @return
     */
    public static Date endDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 方法名: getMonthSpace</br>
     * 详述: 检查2个日期相差的月份 </br>
     * 开发人员：yuanbao</br>
     * 创建时间：2017年3月20日</br>
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(String date1, String date2, DatePattern pattern) {

        try {
            int result = 0;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern.toString());
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);

            return result == 0 ? 1 : Math.abs(result);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("日期解析异常", e);
        }

    }

    /**
     * 获取对应的时间以秒为单位
     * @return
     */
    public static Integer getTimeInSeconds() {
        Long second = Calendar.getInstance().getTimeInMillis() / 1000;
        return second.intValue();
    }


    public static Long getLongTimeInSecond() {
        Long second = Calendar.getInstance().getTimeInMillis() / 1000;
        return second;
    }

    /**
     * 把秒转换成时间
     * @param seconds
     * @return
     */
    public static Date toDate(Integer seconds) {
        return new Date(seconds * 1000);
    }

    /**
     * 获取当前的时间
     * @return
     */
    public static String getNow() {
        return format(new Date(), DatePattern.LONG_DASH);
    }


    /**
     * 获取今天的日期
     * @return
     */
    public static String getToday() {
        return format(new Date(), DatePattern.SHORT_DASH);
    }

    public static int getWeeks(int days) {
        int result = days % 7;
        if (0 == result) {
            return days / 7;
        } else {
            return days / 7 + 1;
        }
    }


    public static int getYear(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    public static int getDayOfMonth(Calendar calendar) {
        int month = calendar.get(Calendar.DAY_OF_MONTH);
        return month;
    }

    /**
     * 获取这个月的第几天
     * @param localDate
     * @return
     */
    public static Date getDateThisMonth(LocalDate localDate, int day) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        localDate = LocalDate.of(year, month, day);
        return localDateToDate(localDate);
    }
    /**
     * 获取下个月第几天
     * @param localDate
     * @return
     */
    public static Date getDateNextMonth(LocalDate localDate, int day) {
        int year = localDate.getYear();
        int month = localDate.getMonthValue() + 1;
        if(month > 12) {
            month = 1;
            year++;
        }
        localDate = LocalDate.of(year, month, day);
        return localDateToDate(localDate);
    }

    /**
     * localDate转Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * 获取明年某月的某天
     * @param localDate
     * @param month
     * @return
     */
    public static Date getNextYearMonthDay(LocalDate localDate, int month, int day) {
        int year = localDate.getYear();
        localDate = LocalDate.of(year + 1, month, day);
        return localDateToDate(localDate);
    }

    /**
     * 获取当前月份的第一天
     * @return
     */
    public static Date getFirstDateMonth() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        return localDateToDate(firstDay);
    }

    /**
     * 获取当前月份的最后一天
     * @return
     */
    public static Date getEndDateMonth() {
        LocalDate now = LocalDate.now();
        LocalDate endDay = now.with(TemporalAdjusters.firstDayOfMonth());
        return localDateToDate(endDay);
    }


    /**
     * 对满足条件的yyyy-MM-dd进行格式化
     * @param date
     * @return
     */
    public static String format(String date) {
        return format(parse(date, DatePattern.SHORT_DASH), DatePattern.SHORT_DASH);
    }

    public static String format(String date, DatePattern pattern) {
        return format(parse(date, pattern), pattern);
    }

    public static Date getDate(String date, String startTime) {
        String d = date + " " + startTime;
        Date dd = parse(d, DatePattern.MID_DASH);
        return dd;
    }


    /**
     * 当前时间是否在现在之前
     * @param date
     * @param startTime
     * @return
     */
    public static boolean isBeforeNow(String date, String startTime) {
        Date dd = getDate(date, startTime);
        return dd.before(new Date());
    }


    /**
     * 添加时间
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, Integer hour) {
        return org.apache.commons.lang3.time.DateUtils.addHours(date, hour);
    }

    /**
     * 添加分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, Integer minutes) {
        return org.apache.commons.lang3.time.DateUtils.addMinutes(date, minutes);
    }

    public static Date addDays(Date date, Integer day) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, day);
    }



    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    public static long dateBetweenSecond(Date endDate, Date startDate) {
        return (endDate.getTime() - startDate.getTime()) / 1000;
    }

    private static Pattern datePattern = Pattern.compile("^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$");
}
