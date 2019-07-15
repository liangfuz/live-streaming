package com.easy.live.streaming.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>
 * <h2>SysDate工具类用于从数据库取得系统时间。</h2>
 * </p>
 * 
 * <p>
 * SysDate工具类可以将取得的系统时间缓存起来，不用每次都去数据库取得系统时间， 大大减少了数据库访问次数（实际上只访问了一次），提高效率。
 * </p>
 * 
 * <p>
 * 从Oracle数据库取得时间时，使用配置类SysConfig.getSqlDate()函数返回的SQL， 类似于：<br>
 * 
 * <pre>
 * select sysdate from dual;
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * SysDate工具类还支持日期到字符串，或者字符串到日期的格式转换。如果需要支持其他的 日期运算（如：截取日期部分，使时间为00:00:00），请将相关方法抽象成通用工具方法， 加入到该工具类中。
 * </p>
 * 
 */
public final class SysDate
{
    
    private static SysDate sysDate = new SysDate();
    
    private static String sMaxDate = "2038-12-31";
    
    private static Date maxDate;
    
    // 数据库时间与WEB服务器时间差
    private static long diff = 0;
    
    private static int dateLength = 12;
    
    // 日期格式: yyyyMMdd
    private static DateFormat dateFormatSimple;
    
    // 日期格式: yyyyMM
    private static DateFormat dateFormatYearMonth;
    
    // 日期格式
    private static DateFormat dateFormatEn;
    
    // 日期时间格式
    private static DateFormat dateTimeFormatEn;
    
    /**
     * 去空
     * 
     * @param object
     * @return
     */
    public static String trim(Object object)
    {
        if (object == null || object.equals("null"))
        {
            return "";
        }
        else
        {
            return object.toString();
        }
    }
    
    // 不允许实例化SysDate对象
    private SysDate()
    {
    }
    
    /** 获取最大日期时间 */
    public static Date getMaxDate()
    {
        if (maxDate == null)
        {
            maxDate = SysDate.getDate(sMaxDate);
        }
        return maxDate;
    }
    
    /** 获取最大日期时间 */
    public static Date addMonth(Date date, int month)
    {
        GregorianCalendar cal = (GregorianCalendar)GregorianCalendar.getInstance();
        cal.setTime(date);
        // cal.set (date.getYear(), date.getMonth(),);
        // cal.roll(Calendar.MONTH,-month);
        cal.roll(Calendar.MONTH, month);
        return cal.getTime();
    }
    
    /** 获取系统日期时间 */
    public static Date getSysDate()
    {
        Date date = Calendar.getInstance().getTime();
        return new Timestamp(date.getTime() - diff);
    }
    
    // 将指定日期对象格式化成指定格式的日期字符串
    private static String getDate(Date date, DateFormat formator)
    {
        return formator.format(date);
    }
    
    // 将指定的日期字符串按照指定的格式解析成日期对象
    private static Date getDate(String date, DateFormat formator)
    {
        if (date == null || date.length() <= 0)
        {
            return null;
        }
        Date d = null;
        try
        {
            d = formator.parse(date);
        }
        catch (Exception e)
        {
            try
            {
                if (date.length() <= dateLength)
                {
                    d = SysDate.getDateFormat().parse(date);
                }
                else
                {
                    d = SysDate.getDateTimeFormat().parse(date);
                }
            }
            catch (ParseException e1)
            {
                // String format1 = SysConfig.getFormatDate();
                // String format2 = SysConfig.getFormatDateTime();
                // throw new ExceptionSys("日期格式错误，正确格式应该是：" , e);
            }
        }
        if (d != null)
        {
            return getTimestamp(d);
        }
        return null;
    }
    
    /**
     * 根据输入日期格式字符串转换为DateFormat
     * 
     * @param format
     * @return
     */
    private static DateFormat getDateFormat(String format)
    {
        if (format == null)
            format = "YYYY-MM-DD";
        DateFormat dateType = new SimpleDateFormat(format);
        return dateType;
    }
    
    /**
     * 将日期字符串转为指定日期类型
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date getDate(String date, String format)
    {
        if (date == null)
            return null;
        DateFormat dateType = getDateFormat(format);
        return getDate(date, dateType);
    }
    
    /**
     * 将日期类型转换为指定格式的日期字符串
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDate(Date date, String format)
    {
        if (date == null)
            return null;
        DateFormat dateType = getDateFormat(format);
        return getDate(date, dateType);
    }
    
    private static DateFormat getFormatYearMonth()
    {
        if (dateFormatYearMonth == null)
        {
            String format = "yyyyMM";
            dateFormatYearMonth = new SimpleDateFormat(format);
        }
        return dateFormatYearMonth;
    }
    
    /** 获取当前系统日期，并转换成字符串，格式：yyyyMM */
    public static String getDateYearMonth()
    {
        return getDate(getSysDate(), getFormatYearMonth());
    }
    
    /** 获取当前系统日期，并转换成字符串，格式：yyyyMM */
    public static Long getDateMonth()
    {
        return new Long(getDate(getSysDate(), "MM"));
    }
    
    /** 获取指定日期的字符串，格式：yyyyMM */
    public static String getDateYearMonth(Date date)
    {
        return getDate(date, getFormatYearMonth());
    }
    
    private static DateFormat getFormatSimple()
    {
        if (dateFormatSimple == null)
        {
            String format = "yyyyMMdd";
            dateFormatSimple = new SimpleDateFormat(format);
        }
        return dateFormatSimple;
    }
    
    /** 获取当前系统日期，并转换成字符串，格式：yyyyMMdd */
    public static String getDateSimple()
    {
        return getDate(getSysDate(), getFormatSimple());
    }
    
    /** 获取指定日期的字符串，格式：yyyyMMdd */
    public static String getDateSimple(Date date)
    {
        return getDate(date, getFormatSimple());
    }
    
    /**
     * 取得日期格式，由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
     */
    public static DateFormat getDateFormat()
    {
        if (dateFormatEn == null)
        {
            String format = "yyyy-MM-dd";
            dateFormatEn = new SimpleDateFormat(format);
        }
        return dateFormatEn;
    }
    
    /**
     * 取得日期时间格式，由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
     */
    public static DateFormat getDateTimeFormat()
    {
        if (dateTimeFormatEn == null)
        {
            String format = "yyyy-MM-dd HH:mm:ss";
            dateTimeFormatEn = new SimpleDateFormat(format);
        }
        return dateTimeFormatEn;
    }
    
    /**
     * 获取当前系统日期，并转换成字符串，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
     */
    public static String getDate()
    {
        return getDate(getSysDate(), getDateFormat());
    }
    
    /**
     * 获取当前系统日期时间，并转换成字符串，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime()
    {
        return getDate(getSysDate(), getDateTimeFormat());
    }
    
    /**
     * 将指定日期对象转换成字符串，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
     */
    public static String getDate(Date date)
    {
        return getDate(date, getDateFormat());
    }
    
    /**
     * 将指定日期对象转换成字符串，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(Date date)
    {
        return getDate(date, getDateTimeFormat());
    }
    
    /**
     * 将字符串转换成日期对象，格式由SysConfig.getFormatDate()配置， 如：yyyy-MM-dd
     */
    public static Date getDate(String date)
    {
        return getDate(date, getDateFormat());
    }
    
    /**
     * 将字符串转换成日期对象，格式由SysConfig.getFormatDateTime()配置， 如：yyyy-MM-dd HH:mm:ss
     */
    public static Date getDateTime(String date)
    {
        return getDate(date, getDateTimeFormat());
    }
    
    /** 取得指定日期所在月份的第一天，如果未指定日期，则默认为当前日期 */
    public static Date getMonthDateFirst(Date date)
    {
        Date nowDate = date;
        if (nowDate == null)
        {
            nowDate = SysDate.getSysDate();
            if (nowDate == null)
            {
                return null;
            }
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DATE, 1);
        
        return calendar.getTime();
    }
    
    /** 取得指定日期所在月份的最后一天，如果未指定日期，则默认为当前日期 */
    public static Date getMonthDateLast(Date date)
    {
        Date nowDate = date;
        if (nowDate == null)
        {
            nowDate = SysDate.getSysDate();
            if (nowDate == null)
            {
                return null;
            }
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDay);
        
        return calendar.getTime();
    }
    
    private static Date getTimestamp(Date date)
    {
        if (date == null)
        {
            return null;
        }
        if (date instanceof Timestamp)
        {
            return date;
        }
        return new Timestamp(date.getTime());
    }
    
    public static SysDate getInstance()
    {
        return sysDate;
    }
}
