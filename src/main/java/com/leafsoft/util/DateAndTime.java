package com.leafsoft.util;

import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateAndTime 
{
    public static final String TIMESTAMPFORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String APPENDTIME = " HH:mm:ss";
    public static final String APPENDTIMEVALUE = " 12:00:00";
    
    public static final String UIAPPENDTIME = " 00:00 am";
    public static final String TEXTDATEMONYEAR = "MMM-yy";//No I18N
    public static final String MONTHYEARFULL = "MMM yyyy";//No I18N
    
    public static final String ORIGDATEFORMAT = "yyyy-MM-dd";//No I18N
    public static final String DBDATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CRMSTARTDATEFORMAT = "MMddyyyy";//No I18N
    public static final String DBDATEWITHTZFORMAT = "yyyy-MM-dd HH:mm:ss z";
    
    public static final String UIDATETIMEFORMAT = "dd/MM/yyyy hh:mm:ss a";
    public static final String UIDATEFORMAT = "dd/MM/yyyy";
    public static final String UITIMEFORMAT = "hh:mm a";
    public static final String UIDATEWITHTZFORMAT = "dd/MM/yyyy z";
    public static final String UIDATETIMEWITHTZFORMAT = "dd/MM/yyyy HH:mm:ss z";
    public static final String UIREADABLEDATETIMEFORMAT = "MMM dd,yyyy HH:mm:ss";
    public static final String UIDDATEFORMAT="dd-MMM-yyyy";//No I18N
    
    public static final String UIREADABLEAMPMFORMAT = "MMM dd,yyyy hh:mm a";
    
    public static final String GMT="GMT";
    public static final String DATEFORMAT = "dd/MM/yyyy";
    public static final String DATEFORMATMONTYEAR = "yyyy-MM";//No I18N
    
    
    public static final long MILLISECTOSEC = 1000;
    public static final long MILLISECTOMIN = 60000;
    public static final long MILLISECTOHR = 3600000;
    
    public static final long SECTOMILLISEC = 1000;
    public static final long MINTOMILLISEC = 60000;
    public static final long HRTOMILLISEC = 3600000;
    
    public static final short SECOND = 0;
    public static final short MINUTE = 1;
    public static final short HOUR = 2;
    public static final short DAY = 3;
    private static final Logger LOGGER = Logger.getLogger(DateAndTime.class.getName());

    public DateAndTime() 
    {        
    }
    
    public static long toMilliSec(long duration,short period) 
    {
        if(period == SECOND) 
	{
            return duration*SECTOMILLISEC;
        }
        if(period == MINUTE) 
	{
            return duration*MINTOMILLISEC;
        }
        if(period == HOUR) 
	{
            return duration*HRTOMILLISEC;
        }
        return duration;
        
    }
    
    public static Timestamp getNextDate(Timestamp date) 
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return new Timestamp(cal.getTimeInMillis());
    }
    /**
     * This method returns the date in the form of a string.
     */
    
    public static String dateString() 
    {
        long dtl = System.currentTimeMillis() ;
        String theDate = longdateToString(dtl,DATEFORMAT);
        return theDate;
    }
    
    /**
     * This method returns the date in the form of a string corresponding to the
     * given long value.
     */
    
    public static String dateString(long i) 
    {
        //long dtl = dateInLong();
        long dtl = System.currentTimeMillis() ;
        dtl = ( i * 86400000 ) + dtl ;
        String theDate = longdateToString(dtl,DATEFORMAT);
        return theDate;
    }
    
    /**
     * This method returns the time in the form of a string.
     */
    
    public static String timeString() 
    {
        Calendar calendar = new GregorianCalendar();
        String theTime=calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND)+"";
        return theTime;
    }
    
    /**
     * This method returns the time along with milliseconds in the form of a
     * string.
     */
    
    public static String timeStringWithMillies() 
    {
        Calendar calendar = new GregorianCalendar();
        String theTime=calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND)+" "+calendar.get(Calendar.MILLISECOND);
        return theTime;
    }
    
    /**
     * This method returns the current time in terms of miliseconds since
     * 01-Jan-1970.
     */
    
    public static long dateInLong() 
    {
        return  System.currentTimeMillis();
    }
    
    /**
     * This method returns the current date and time
     * in the given String format.
     */
    
    public static String dateString(String formatStr) 
    {
        return longdateToString(System.currentTimeMillis(),formatStr);
    }
    
    /**
     *
     */
    
    /*
     * This method returns the currect date and time
     * in the given format based on timezone.
     */
    
    public static String dateString(String format, String timeZone) 
    {
        if(format == null || format.equals(""))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat f1 = new SimpleDateFormat(format);
        if(timeZone == null || timeZone.equals(""))
        {
            timeZone = System.getProperty("user.timezone");//NO I18N
        }
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        Calendar cal = Calendar.getInstance();
        f1.setTimeZone(tz);
        String curDate = f1.format(cal.getTime());
        return curDate;
    }

    
    public static long dateInLong(String st) 
    {
        long dateInLong = 0;
        try 
	{
            dateInLong = System.currentTimeMillis();
        }
        catch(Exception e) 
	{
	    e.printStackTrace();
        }
        
        return dateInLong;
    }
    
    public static void main(String[] args) throws Exception
    {
        //Date d1 = newconvertTimeZone("01/11/2005 10:00 AM", "America/Los_Angeles", "dd/MM/yyyy hh:mm a", "yyyy-MM-dd HH:mm:ss", "GMT");
        ////LOGGER.log(Level.INFO,"d1 "+d1);

        //LOGGER.log(Level.INFO,"***********************");
        
        Date d2 = convertTimeZone("01/11/2005 10:00 AM", "GMT-08:00", "dd/MM/yyyy hh:mm a", "yyyy-MM-dd HH:mm:ss", "GMT");
        //LOGGER.log(Level.INFO,"d2 "+d2);
    
    }
    
    /**
     * This method returns the date corresponding to a given string in a
     * specified format.
     */
    public static long dateInLong(String st,String format) 
    {
        long dateInLong = 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try 
	{
            java.util.Date mailDate = formatter.parse(st);
            dateInLong = mailDate.getTime();
        }
        catch(Exception e) 
	{
            e.printStackTrace();
        }
        
        return dateInLong;
    }
    
    public static String getDay(String dateString,String format,String destFormat)
    {
        long dateLng = dateInLong(dateString,format);
        return longdateToString(dateLng, destFormat);
    }
    
    /**
     * This method returns the date corresponding to a given string in a
     * specified format.
     */
    
    public static long nextDateInLong(String st,String format) 
    {
        long dateInLong = 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try 
	{
            java.util.Date mailDate = formatter.parse(st);
            dateInLong = mailDate.getTime();
            dateInLong+=86400000;
        }
        catch(Exception e) 
	{
	    e.printStackTrace();
        }
        
        return dateInLong;
    }
    
    /**
     * This returns the date as a string corresponding to a given date of type
     * long.
     */
    
    public static String longdateToString(long ld)
    { 
        long ldate=ld;
        String dateFormat=null;
        
        try
	{
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss  zzz");//NO I18N
            java.util.Date theCreatedDate = new java.util.Date(ldate);
            dateFormat=formatter.format(theCreatedDate);            
        }
        catch(Exception e) 
	{
            e.printStackTrace();
        }
        return dateFormat;
    }
    
    /**
     * This returns the date as a string in a specified format corresponding to a
     * given date of type long.
     */
    
    public static String longdateToString(long ld, String fmt) 
    {
        long ldate=ld;
        String dateFormat=null;
        
        try 
	{
            SimpleDateFormat formatter = new SimpleDateFormat(fmt);
            java.util.Date theCreatedDate = new java.util.Date(ldate);
            dateFormat=formatter.format(theCreatedDate);
            
        }
        catch(Exception e) 
	{
            e.printStackTrace();
        }
        return dateFormat;
    }
    
    /**
     * This returns the date as a string in a specified format corresponding to a
     * given date of type long.
     */
    public static String longdateToString(java.util.Date date, String fmt) 
    {
        String dateFormat=null;        
        try 
	{
            SimpleDateFormat formatter = new SimpleDateFormat(fmt);
            dateFormat=formatter.format(date);
        }
        catch(Exception e) 
	{
            e.printStackTrace();
        }
        return dateFormat;
    }
    
    public static int getDifferenceInHour(java.util.Date startDate, java.util.Date endDate) 
    {
        long startDateLng = startDate.getTime();
        long endDateLng = endDate.getTime();
        
        long diff = endDateLng-startDateLng;
        long diffHr = diff/MILLISECTOHR;
        return new Long(diffHr).intValue();
    }
    
    public static long getDifferenceInMin(java.util.Date startDate, java.util.Date endDate) 
    {
        long startDateLng = startDate.getTime();
        long endDateLng = endDate.getTime();
        
        long diff = endDateLng-startDateLng;
        long diffMin = diff/MILLISECTOMIN;
        return diffMin;
    }
    
    /**
     * This method returns the difference between the current date and another
     * given date in terms of milliseconds.
     */
    public static long getDifferenceInDate(String reminderDate) 
    {
        long difference = 0;
        try 
	{
            long today = dateInLong();
            long reminder = dateInLong(reminderDate,DATEFORMAT);
            difference = reminder - today;
        }
        catch(Exception e) 
	{
            e.printStackTrace();
        }
        
        return difference;
    }
    
    public static String getCurrentDate_Time(String format, String timeZone) 
    {
        if(format == null || format.equals(""))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat f1 = new SimpleDateFormat(format);
        if(timeZone == null || timeZone.equals(""))
        {
            timeZone = System.getProperty("user.timezone");//NO I18N
        }
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        Calendar cal = Calendar.getInstance();
        f1.setTimeZone(tz);
        String curDate = f1.format(cal.getTime());
        return curDate;
    }
    
    public static String getDate_Time(String dateInGMT,String format) 
    {
        SimpleDateFormat f1 = new SimpleDateFormat(format);
        TimeZone tarTz = TimeZone.getTimeZone("GMT");
        f1.setTimeZone(tarTz);
        
        Date dte = null;
        
        try 
	{
            dte = f1.parse(dateInGMT);
        }
        catch(Exception exx) 
	{
            exx.printStackTrace();
            return null;
        }
        
        SimpleDateFormat sdf = (SimpleDateFormat)DateFormat.getDateTimeInstance();
        sdf.applyPattern(format);
        return(sdf.format(dte));
    }
    
    public static String getDate_TimeInGMT(String dateStr,String format) 
    {
        if(format == null)
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat f1 = new SimpleDateFormat(format);
        //TimeZone tarTz = TimeZone.getDefault();
        TimeZone tarTz = TimeZone.getTimeZone("GMT");
        Calendar cal = Calendar.getInstance();
        f1.setTimeZone(tarTz);
        
        Date dte = null;
        
        try 
	{
            dte = f1.parse(dateStr);
        }
        catch(Exception exx) 
	{
            exx.printStackTrace();
            return null;
        }
        cal.setTime(dte);
        return f1.format(cal.getTime());
    }
    public static String increment_Decrement_Dates(String dateString, long no_of_days) 
    {
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//NO I18N
        Date dte = null;
        try 
	{
            dte = f1.parse(dateString);
        }
        catch(Exception exx) 
	{
            exx.printStackTrace();
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dte);
        cal.add(cal.DATE, (int)no_of_days);
        Date dxt = cal.getTime();
        return f1.format(dxt);
    }
    
    public static String increment_Decrement_Dates(String dateString, long no_of_days,String format) 
    {
        SimpleDateFormat f1 = new SimpleDateFormat(format);
        Date dte = null;
        try 
	{
            dte = f1.parse(dateString);
        }
        catch(Exception exx) 
	{
            exx.printStackTrace();
            return null;
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dte);
        cal.add(cal.DATE, (int)no_of_days);
        Date dxt = cal.getTime();
        return f1.format(dxt);
    }
    
    public static java.util.Date getDateFromString(String dateStr,String formatStr) 
    {
        java.util.Date dt = null;
        try 
	{
            SimpleDateFormat simpledateformat = new SimpleDateFormat(formatStr);
            dt = simpledateformat.parse(dateStr);
        }
        catch(Exception exception) 
	{
            exception.printStackTrace();
        }
        return dt;
    }
    
    public String getTimeOffset(String timeZone) 
    {
        String timeStr = null;
        String val = getCurrentDate_Time("z",timeZone);
        int signIndex = val.indexOf("-");
        if(signIndex == -1) 
	{
            signIndex = val.indexOf("+");
        }
        if(signIndex != -1) 
	{
            timeStr = val.substring(signIndex);
            int colonIndex = timeStr.indexOf(":");
            if(colonIndex != -1)
            {
                timeStr = timeStr.substring(0,colonIndex)+timeStr.substring(colonIndex+1);
            }
        }
        return timeStr;
    }
    
    public String getCurrentDate_TimeForMail(String format,String timeZone) 
    {
        String date = getCurrentDate_Time(format,timeZone);
        String time = getTimeOffset(timeZone);
        if(time != null)
        {
            date += " "+time;
        }
        else
        {
            date = getCurrentDate_Time(format+" zzz",timeZone);
        }
        return date;
    }
    
    public String getDateString(Properties p) 
    {
        String date=(String)p.get("time");
        String str = null;
        if(date != null) 
	{
            String fmt=p.getProperty("format");
            if(fmt==null)
            {
                str = date.substring(0,date.indexOf(' '));
            }
            else
            {
                str = DateAndTime.longdateToString(Long.parseLong(date),fmt);
            }
        }
        return str;
    }
    
    public static java.sql.Timestamp getCurrentDateTime() 
    {
        java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
        return date;
    }
    
    public static java.sql.Timestamp getCurrentDate() 
    {
        String todayStr = dateString();
        long todayLong = dateInLong(todayStr,DATEFORMAT);
        return new java.sql.Timestamp(todayLong);
    }
    
    public static String convertDateFormat(String dateStr,String frmFormat,String toFormat) 
    {
        return longdateToString(dateInLong(dateStr,frmFormat),toFormat);
    }
    
    public static java.sql.Timestamp getDate() 
    {
        String todayStr = dateString();
        long todayLong = dateInLong(todayStr,DATEFORMAT);
        return new java.sql.Timestamp(todayLong);
    }
    
    public static java.sql.Timestamp getTimestamp(String dateStr,String format) 
    {
        long date = dateInLong(dateStr,format);
        return new java.sql.Timestamp(date);
    }
    
    /**
     * takes any date as the input and returns the all dates in that week
     *
     * @param today any date in the format specified by the parameter 'format'
     * @format represent format of the week dates
     * @weekfirstday represents the first day of the week
     */
    public static Properties getWeekDates(String today,String format,int weekFirstDay) 
    {
        try
	{
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(today);
            //long dateLng = date.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            
            cal.setFirstDayOfWeek(weekFirstDay);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            int keyDay = -(day-weekFirstDay);
            
            Properties props = new Properties();
            for(int i=keyDay,count=0;count<=7;i++,count++) 
	    {
                Calendar working = (Calendar)cal.clone();
                working.add(Calendar.DAY_OF_YEAR, i);
                props.put(new Integer(count+1),longdateToString(working.getTimeInMillis(), format));
            }
            return props;
        }
	catch(Exception e)
	{
	    e.printStackTrace();
	}
        return null;
    }
    
    public static Properties getWeekStartAndEndDate(String today,String format) 
    {
        try
	{
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(today);
            //long dateLng = date.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            
            int firstDay = cal.getFirstDayOfWeek();
            int lastDay = firstDay+6;
            int day = cal.get(Calendar.DAY_OF_WEEK);
            
            Calendar working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, -(day-firstDay));
            java.sql.Timestamp startDate = new java.sql.Timestamp(working.getTimeInMillis());
            
            working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, +(7-day));
            working.add(Calendar.HOUR,23);
            working.add(Calendar.MINUTE,59);
            working.add(Calendar.SECOND,59);
            java.sql.Timestamp endDate = new java.sql.Timestamp(working.getTimeInMillis());
            
            Properties props = new Properties();
            props.put("start",startDate);
            props.put("end",endDate);
            return props;
        }
	catch(Exception e)
	{
	    e.printStackTrace();
	}
        return null;
    }

    public static Properties getWeekStartAndEndDate(int fDay, String today,String format, boolean work, int wrks, int wrke)
    {
        try
	{
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date date = dateFormat.parse(today);
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(fDay);
            cal.setTime(date);
            int firstDay = cal.getFirstDayOfWeek();
            
            int day = cal.get(Calendar.DAY_OF_WEEK);
            
            Calendar working = (Calendar)cal.clone();
            
            if(day < firstDay)
            {
                working.add(Calendar.DAY_OF_YEAR, -((day-firstDay)+7));
            }
            else
            {
                working.add(Calendar.DAY_OF_YEAR, -(day-firstDay));
            }

            if(work)
            {
                int si =0;
                if(fDay > wrks)
                {
                    si = 7-fDay + wrks;  
                }
                else if (fDay < wrks)
                {
                    si = wrks-fDay;
                }
                working.add(Calendar.DAY_OF_YEAR, +(si));
                java.sql.Timestamp startDate = new java.sql.Timestamp(working.getTimeInMillis());
                
                working = (Calendar) cal.clone();
                if(day < firstDay)
                {
                    working.add(Calendar.DAY_OF_YEAR, -((day-firstDay)+7));
                }
                else
                {
                    working.add(Calendar.DAY_OF_YEAR, -(day-firstDay));
                }

                int ei =0;
                if(fDay > wrke)
                {
                    ei = 7-fDay + wrke;  
                }
                else if (fDay < wrke)
                {
                    ei = wrke-fDay;
                }

                working.add(Calendar.DAY_OF_YEAR, +(ei));
                java.sql.Timestamp endDate = new java.sql.Timestamp(working.getTimeInMillis());

                Properties props = new Properties();
                props.put("start",startDate);
                props.put("end",endDate);
                int totalWorkDays =1;
                if(wrks < wrke)
                {
                totalWorkDays = wrke - wrks+1;
                }
                if(wrks > wrke)
                {
                totalWorkDays = (7-wrks) + wrke + 1;
                }
                props.put("totalDays",totalWorkDays+"");
                return props;
                
            }
            else
            {
                java.sql.Timestamp startDate = new java.sql.Timestamp(working.getTimeInMillis());
                working.add(Calendar.DAY_OF_YEAR, 6);
                int totalDays = 7;
                java.sql.Timestamp endDate = new java.sql.Timestamp(working.getTimeInMillis());
                Properties props = new Properties();
                props.put("start",startDate);
                props.put("end",endDate);
                props.put("totalDays",totalDays+"");
                return props;
            }
        }
	catch(Exception e)
	{
	    e.printStackTrace();
	}
        return null;
    }
    
    
    public static Properties getWeekStartAndEndDate(Calendar cal,String format) 
    {
        try
	{
            int firstDay = cal.getFirstDayOfWeek();
            int day = cal.get(Calendar.DAY_OF_WEEK);
            Calendar working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, -(day-firstDay));
            
            java.sql.Timestamp startDate = new java.sql.Timestamp(working.getTimeInMillis());
            working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, +(7-day));
            java.sql.Timestamp endDate = new java.sql.Timestamp(working.getTimeInMillis());

            Properties props = new Properties();
            props.put("start",startDate);
            props.put("end",endDate);
            return props;
        }
	catch(Exception e)
	{
   	    e.printStackTrace();
	}
        return null;
    }
    
    public static Properties getWeekStartAndEndDate(Calendar cal,String format,String tz) 
    {
        try
	{
            int firstDay = cal.getFirstDayOfWeek();
            int day = cal.get(Calendar.DAY_OF_WEEK);
            
            
            Calendar working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, -(day-firstDay));
            java.sql.Timestamp startDate = new java.sql.Timestamp(working.getTimeInMillis());
            
            
            working = (Calendar)cal.clone();
            working.add(Calendar.DAY_OF_YEAR, +(7-day));
            java.sql.Timestamp endDate = new java.sql.Timestamp(working.getTimeInMillis());
            
            
            Properties props = new Properties();
            props.put("start",startDate);
            props.put("end",endDate);
            
            return props;
        }
	catch(Exception e)
	{
	    e.printStackTrace();
	}
        return null;
    }

    public static Properties getMonthStartAndEndDate(int month,int year) 
    {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.MONTH,month-1);
        cal.set(cal.YEAR,year);
        int endday = cal.getActualMaximum(cal.DAY_OF_MONTH);
        int startday = cal.getActualMinimum(cal.DAY_OF_MONTH);
        String startstr = startday+"/"+month+"/"+year+" 00:00:00";
        //java.sql.Timestamp startDate = new java.sql.Timestamp(dateInLong(startstr,"dd/mm/yyyy"));
        java.sql.Timestamp startDate = getTimestamp(startstr,TIMESTAMPFORMAT);
        String endstr = endday+"/"+month+"/"+year+" 23:59:59";
        java.sql.Timestamp endDate = getTimestamp(endstr,TIMESTAMPFORMAT);
        // java.sql.Timestamp endDate = new java.sql.Timestamp(dateInLong(endstr,"dd/mm/yyyy"));
        Properties props = new Properties();        
        props.put("start",startDate);
        props.put("end",endDate);
        return props;
    }
    
    public static String convertDate(java.util.Date date,String fromTimeZone,String toTimeZone,String format) 
    {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        TimeZone fromTZObj = null;
        if(fromTimeZone == null)
        {
            fromTZObj = TimeZone.getDefault();
        }
        else
        {
            fromTZObj = TimeZone.getTimeZone(fromTimeZone);
        }
        Calendar fromCal = new GregorianCalendar(fromTZObj);
        fromCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        
        TimeZone toTZObj = null;
        if(toTimeZone == null)
        {
            toTZObj = TimeZone.getDefault();
        }
        else
        {
            toTZObj = TimeZone.getTimeZone(toTimeZone);
        }
        Calendar toCal = new GregorianCalendar(toTZObj);
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        DateFormat formatter = DateFormat.getDateTimeInstance( DateFormat.SHORT,DateFormat.SHORT);
        formatter.setTimeZone(toTZObj);
        String result = formatter.format(toCal.getTime());
		//LOGGER.log(Level.INFO," RESULT : " + result);
        result = getDay(result,"MM/dd/yy hh:mm:ss a",format);
        return result;
    }
    
    public TimeZone getTimezoneOffset(String zoneStr) 
    {
        
        
        if(zoneStr.equals("GMT") || zoneStr.equals("0")) 
	{
            return TimeZone.getTimeZone("GMT");
        }
        TimeZone ret = null;
        String id = TimeZone.getTimeZone("GMT"+zoneStr).getID();
        String tmpid = TimeZone.getTimeZone(zoneStr).getID();
        if(id.equals("GMT"))
	{
            ret = TimeZone.getTimeZone(zoneStr);
            if(!ret.getID().equals("GMT")) 
	    {
                return ret;
            }
            if(zoneStr.length() > 5) 
	    {
                zoneStr = zoneStr.substring(0,5);
                id = TimeZone.getTimeZone("GMT"+zoneStr).getID();
            }
        }
        int gmtIndex = id.indexOf("GMT");
        String subStr = id.substring(gmtIndex+3);
        int colon = subStr.indexOf(":");
        String sign = null;
        int indexOfSign = -1;
        int zoneOffset = 0;
        String prePart = null;
        String postPart = null;
        if(colon != -1) 
	{
            sign = subStr.substring(0,1);
            prePart = subStr.substring(1,colon);
            postPart = subStr.substring(colon+1,colon+3);
        }
        int timeOffset = -1;
        try
	{
            timeOffset = ((Integer.parseInt(prePart)*60)+(Integer.parseInt(postPart)))*60*1000;
        }
	catch(NumberFormatException nfe)
	{
            nfe.printStackTrace();
            return TimeZone.getTimeZone("GMT");
        }
        
        if(sign.equals("-"))
        {
            timeOffset = timeOffset * -1;
        }
        String[] strArr = TimeZone.getAvailableIDs(timeOffset);
        if(strArr != null && strArr.length>0) 
	{
            ret = new SimpleTimeZone(timeOffset,strArr[0]);
            return ret;
        }
        return null;
    }
    
    public static java.util.Date convertTaskTimeZone(String dateStr,String fromTimezone,String fromFormat,String toFormat,String toTimezone) throws java.text.ParseException
    {
        return convertTimeZone(dateStr+APPENDTIMEVALUE,fromTimezone,fromFormat+APPENDTIME,toFormat,toTimezone);
    }
    
    public static java.util.Date convertTimeZone(String dateStr,String fromTimezone,String fromFormat,String toFormat,String toTimezone) throws java.text.ParseException
    {
        Calendar cal = Calendar.getInstance();
        long dateLng = dateInLong(dateStr,fromFormat);
        cal.setTimeInMillis(dateLng);
        TimeZone fromTZObj = null;
        if(fromTimezone == null)
        {
            fromTZObj = TimeZone.getDefault();
        }
        else
        {
            fromTZObj = TimeZone.getTimeZone(fromTimezone);
        }
        Calendar fromCal = new GregorianCalendar(fromTZObj);
        fromCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        
        TimeZone toTZObj = null;
        if(toTimezone == null)
        {
            toTZObj = TimeZone.getDefault();
        }
        else
        {
            toTZObj = TimeZone.getTimeZone(toTimezone);
        }
        Calendar toCal = new GregorianCalendar(toTZObj);
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        Date dd = toCal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(toFormat);
        format.setTimeZone(toTZObj);
        String ff = format.format(dd);
        java.util.Date retDate = new java.sql.Timestamp(dateInLong(ff,toFormat));
        return retDate;
    }
    
    public static long convertTimeZoneToLong(String dateStr,String fromTimezone,String fromFormat,String toFormat,String toTimezone) throws java.text.ParseException
    {
        Calendar cal = Calendar.getInstance();
        long dateLng = dateInLong(dateStr,fromFormat);
        cal.setTimeInMillis(dateLng);
        TimeZone fromTZObj = null;
        if(fromTimezone == null)
        {
            fromTZObj = TimeZone.getDefault();
        }
        else
        {
            fromTZObj = TimeZone.getTimeZone(fromTimezone);
        }
        Calendar fromCal = new GregorianCalendar(fromTZObj);
        fromCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        
        TimeZone toTZObj = null;
        if(toTimezone == null)
        {
            toTZObj = TimeZone.getDefault();
        }
        else
        {
            toTZObj = TimeZone.getTimeZone(toTimezone);
        }
        Calendar toCal = new GregorianCalendar(toTZObj);
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        Date dd = toCal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(toFormat);
        format.setTimeZone(toTZObj);
        String ff = format.format(dd);
        long retDate = dateInLong(ff,toFormat);
        return retDate;
    }

    public static java.util.Date convertTimeZone(java.util.Date dateObj, String fromTimezone, String toFormat, String toTimezone) throws java.text.ParseException
    {
        Calendar cal = Calendar.getInstance();
        long dateLng = dateObj.getTime();
        cal.setTimeInMillis(dateLng);
        TimeZone fromTZObj = null;
        if(fromTimezone == null)
        {
            fromTZObj = TimeZone.getDefault();
        }
        else
        {
            fromTZObj = TimeZone.getTimeZone(fromTimezone);
        }
        Calendar fromCal = new GregorianCalendar(fromTZObj);
        fromCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        
        TimeZone toTZObj = null;
        if(toTimezone == null)
        {
            toTZObj = TimeZone.getDefault();
        }
        else
        {
            toTZObj = TimeZone.getTimeZone(toTimezone);
        }
        Calendar toCal = new GregorianCalendar(toTZObj);
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        Date dd = toCal.getTime();
	//LOGGER.log(Level.INFO,"Got the date 1111111111111 "+dd);

        SimpleDateFormat format = new SimpleDateFormat(toFormat);
        format.setTimeZone(toTZObj);
        String ff = format.format(dd);
        java.util.Date retDate = new java.sql.Timestamp(dateInLong(ff, toFormat));
	
	//LOGGER.log(Level.INFO,"Got the return date "+retDate);
        return retDate;
    }

    public static java.util.Date getCurrentDate(String timezone)
    {
        try
	{            
            TimeZone tz = TimeZone.getTimeZone(timezone);
            SimpleDateFormat dfGMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");//NO I18N
            dfGMT.setTimeZone( tz );
            String str = dfGMT.format( new Date() );
            Date sentDateInGMT  = new java.sql.Timestamp(DateAndTime.dateInLong(str,"yyyy-MM-dd HH:mm:ss"));
            return sentDateInGMT;
        }
	catch(Exception e)
	{
  	    e.printStackTrace();
	}
        return null;
    }

    public static java.util.Date getCurrentDate(String timezone, String format)
    {
        try
	{            
            TimeZone tz = TimeZone.getTimeZone(timezone);
            SimpleDateFormat dfGMT = new SimpleDateFormat(format);
            dfGMT.setTimeZone( tz );
            String str = dfGMT.format( new Date() );
            Date sentDateInGMT  = new java.sql.Timestamp(DateAndTime.dateInLong(str, format));
            return sentDateInGMT;
        }
	catch(Exception e)
	{
  	    e.printStackTrace();
	}
        return null;
    }
    
    public static java.sql.Timestamp convertDate(Date fromDate, String fromTZ, String toTZ)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        TimeZone fromTZObj = null;
        if(fromTZ == null)
        {
            fromTZObj = TimeZone.getDefault();
        }
        else
        {
            fromTZObj = TimeZone.getTimeZone(fromTZ);
        }
        Calendar fromCal = new GregorianCalendar(fromTZObj);
        fromCal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
        
        TimeZone toTZObj = null;
        if(toTZ == null)
        {
            toTZObj = TimeZone.getDefault();
        }
        else
        {
            toTZObj = TimeZone.getTimeZone(toTZ);
        }
        Calendar toCal = new GregorianCalendar(toTZObj);
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        Date dd = toCal.getTime();
        SimpleDateFormat format = new SimpleDateFormat(UIDATETIMEFORMAT);
        format.setTimeZone(toTZObj);
        String ff = format.format(dd);
        java.sql.Timestamp retDate = new java.sql.Timestamp(dateInLong(ff,UIDATETIMEFORMAT));
        return retDate;
    }
    
    public static Calendar stringToCalendar(String dateStr) 
    {
        Calendar retcal = null;
        try 
	{
	    SimpleDateFormat dbdf = new SimpleDateFormat(DBDATEFORMAT);
	    SimpleDateFormat ydf = new SimpleDateFormat("yyyy");//No I18N
	    SimpleDateFormat mondf = new SimpleDateFormat("MM");//No I18N
	    SimpleDateFormat ddf = new SimpleDateFormat("dd");//No I18N
	    SimpleDateFormat hdf = new SimpleDateFormat("HH");//No I18N
	    SimpleDateFormat mindf = new SimpleDateFormat("mm");//No I18N
	    SimpleDateFormat sdf = new SimpleDateFormat("ss");//No I18N
	    Date date = dbdf.parse(dateStr);
  	    String ystr = ydf.format(date);
	    String monstr = mondf.format(date);
	    String dstr = ddf.format(date);
	    String hstr = hdf.format(date);
	    String minstr = mindf.format(date);
	    String sstr = sdf.format(date);
	    int y = Integer.parseInt(ystr);
	    int mon = (Integer.parseInt(monstr) -1);
	    int d = Integer.parseInt(dstr);
	    int h = Integer.parseInt(hstr);
	    int min = Integer.parseInt(minstr);
	    int s = Integer.parseInt(sstr);
	    retcal = new GregorianCalendar(y,mon,d,h,min,s);
        }
        catch (Exception e)
	{}
        return retcal;
    }
    public static String calendarToString(Calendar calObj) 
    {
        String retstr = null;
        try 
	{
            Date date = calObj.getTime();
            SimpleDateFormat dbdf = new SimpleDateFormat(DBDATEFORMAT);
            retstr = dbdf.format(date);
        }catch (Exception e)
       	{
            
        }
        return retstr;
    }

    public static String calendarToString(Calendar calObj, String format) 
    {
        String retstr = null;
        try 
	{
            Date date = calObj.getTime();
            SimpleDateFormat dbdf = new SimpleDateFormat(format);
            retstr = dbdf.format(date);
        }
	catch (Exception e) 
	{
            
        }
        return retstr;
    }
    
    public String[] parseDate(String dateStr)
    {
        // format is dd/MM/yyyy hh:mm a
        int begin = 0;
        int end = dateStr.indexOf("/",begin);
        String date = dateStr.substring(begin,end);
        
        begin = end+1;
        end = dateStr.indexOf("/",begin);
        String month = dateStr.substring(begin,end);
        
        begin = end+1;
        end = dateStr.indexOf(" ",begin);
        String year = dateStr.substring(begin,end);
        
        begin = end+1;
        String time = dateStr.substring(begin);

        ////LOGGER.log(Level.INFO,"date:"+date+",month:"+month+",year:"+year+" time:"+time);
        return new String[]{date,month,year,time};
    }

    public static String getHostTimezone()
    {
		TimeZone tz = TimeZone.getDefault();
        String hostTimezone = tz.getID();
		return hostTimezone;
    }
    public static String RecurringStartDate(String payterm)
    {
	    return RecurringStartDate(payterm,0);

    }
	public static String RecurringStartDate(String payterm,int daysToSubtract) 
	{
		return RecurringStartDate(payterm,daysToSubtract,"yyyy-MM-dd");//No I18N		
	}
        public static String RecurringStartDate(String payterm,int daysToSubtract,String format) 
	{
		int incrt = 1;
		if (payterm.equals("QTER")) {
			incrt = 3;
		}	
		if (payterm.equals("SMYR")) {
			incrt = 6;
		}
		if (payterm.equals("YEAR")) {
			incrt = 12;
		}	
		if(payterm.equals("BIYR")) {
			incrt = 24;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, incrt);
		calendar.add(Calendar.DATE, -1*daysToSubtract);
		SimpleDateFormat f1 = new SimpleDateFormat(format);
		String startDate = f1.format(calendar.getTime());
		return startDate;
	}

	public static String RecurringNPDate(String payterm,String date)  {
		String retdate = NewRecurringNPDate(payterm,date,false);
		return retdate;
	}


	public static String NewRecurringNPDate(String payterm,String date,boolean reverse)
	{
		int incrt = 1;
		if (payterm.equals("QTER")) {
			incrt = 3;
		}
		if (payterm.equals("SMYR")) {
			incrt = 6;
		}
		if (payterm.equals("YEAR")) {
			incrt = 12;
		}
		if (payterm.equals("BIYR")) {
			incrt = 24;
		}


		String daSplit1[] = date.split(" ");
		String daSplit[] = daSplit1[0].split("-");
		Calendar calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(daSplit[0]),Integer.parseInt(daSplit[1])-1,Integer.parseInt(daSplit[2]));
		if(reverse)
			calendar.add(Calendar.MONTH, -incrt);
		else
			calendar.add(Calendar.MONTH, incrt);

		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");//No I18N
		String nDate = f1.format(calendar.getTime());
		return nDate;
	}

    public static int yearsDifference(Date a, Date b) {
	    Calendar calendarA = GregorianCalendar.getInstance();
	    Calendar calendarB = GregorianCalendar.getInstance();
	    int multiplier;
	    if (b.getTime() - a.getTime() > 0) {
		    multiplier = -1;
		    calendarA.setTime(b);
		    calendarB.setTime(a);
	    } else {
		    multiplier = 1;
		    calendarA.setTime(a);
		    calendarB.setTime(b);
	    }
	    int years = calendarA.get(Calendar.YEAR) - calendarB.get(Calendar.YEAR);
	    int months = calendarA.get(Calendar.MONTH) - calendarB.get(Calendar.MONTH);
	    int days = calendarA.get(Calendar.DAY_OF_MONTH) - calendarB.get(Calendar.DAY_OF_MONTH);
	    if (years > 0 && (months < 0 || (months == 0 && days < 0))) {
		    years -= 1;
	    }
	    return years * multiplier;
    }

	public static java.sql.Timestamp convertToDateTime(String strDate) throws Exception
	{
		SimpleDateFormat fmt;
		if (strDate.length() == 10)
		{
			fmt = new SimpleDateFormat("yyyy-MM-dd"); //NO I18N
		}
		else
		{
			fmt = new SimpleDateFormat(DBDATEFORMAT);
		}
		java.util.Date d = fmt.parse(strDate);
		java.sql.Timestamp date = new java.sql.Timestamp(d.getTime());
		return date;
	}
	public static long getDateDiff (Date date1, Date date2){
		long dif = (date1 != null && date2 != null) ? (date1.getTime() - date2.getTime()) / (1000*60*60*24) : 0;
		return dif;
	}

	public static Date convertStringToDate(String dateStr, String ddformat, String format) {
		try{
		DateFormat ddformater = new SimpleDateFormat(ddformat);
		DateFormat formater = new SimpleDateFormat(format);
		Date date = (Date)ddformater.parse(dateStr);
		dateStr = formater.format(date);
		date = (Date) formater.parse(dateStr);
		return date;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
        
        public static String convertStringFromStringDate(String stdate,String exformat,String newformat)
        {
            String tdate="";
            try
            {
                 Date tDate = DateAndTime.convertStringToDate(stdate,exformat,newformat);//No I18N
                 DateFormat df = new SimpleDateFormat(newformat);
                 tdate = df.format(tDate);                                 
            }  
            catch(Exception e){
		 e.printStackTrace();			
             }
            return tdate;
        }
        public static String getCurrentYear() {
	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy");//No I18N
	return df.format(date);
        }
        public static long getTransactionTime(String inquiry_date)
        {  
          long  transactiontime=0; 
          try
            {           
            SimpleDateFormat fmt = new SimpleDateFormat(DBDATEFORMAT);
            Date sDate = fmt.parse(inquiry_date);
            transactiontime= sDate.getTime();
            }
          catch(Exception e)
           {
              e.printStackTrace();
           }      
           return transactiontime;
        }
        
        public static String getUserDatePattern(Locale userLocale) {
   		 
   		 SimpleDateFormat format = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, userLocale);
   		 int i, iLen;
   		 String sTemp;
   		 sTemp = format.toPattern();

   		 int d, m, dLen, mLen;
   		 mLen = sTemp.length();
   		 if(sTemp.indexOf("MM") == -1) {
   			 m = sTemp.lastIndexOf('M') + 1;
   			 sTemp = sTemp.substring(0, m) + "M" + sTemp.substring(m, mLen);//No I18N
   		 }

   		 dLen = sTemp.length();            
   		 if(sTemp.indexOf("dd") == -1) {
   			 d = sTemp.lastIndexOf('d') + 1;
   			 sTemp = sTemp.substring(0, d) + "d" + sTemp.substring(d, dLen);//No I18N
   		 }            

   		 if(sTemp.indexOf("yyyy") == -1) {
   			 if(sTemp.indexOf("yyy") != -1) {
   				 iLen = sTemp.length();
   				 i = sTemp.lastIndexOf('y') + 1;
   				 sTemp = sTemp.substring(0,i) + "y" + sTemp.substring(i,iLen);//No I18N
   			 } else if(sTemp.indexOf("yy")!=-1) {
   				 iLen = sTemp.length();
   				 i = sTemp.lastIndexOf('y') + 1;
   				 sTemp = sTemp.substring(0,i) + "yy" + sTemp.substring(i,iLen);//No I18N
   			 } else if(sTemp.indexOf('y')!=-1) {
   				 iLen = sTemp.length();
   				 i = sTemp.lastIndexOf('y') + 1;
   				 sTemp = sTemp.substring(0,i) + "yyy" + sTemp.substring(i,iLen);//No I18N
   			 }
   		 }

   		 format.applyPattern( sTemp );
   		 String datePatternStr = format.toPattern();
   		 
   		 return datePatternStr;
   	 }
        
        public static Properties getMonthStartEndDate(String date) 
      {
        String[] dates=date.split("-");        
        int year=Integer.parseInt(dates[0]);
        int month=Integer.parseInt(dates[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.YEAR,year);
        int endday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startday = cal.getActualMinimum(Calendar.DAY_OF_MONTH);  
        String start="0"+startday;
        String end=""+endday;                            
        String mnth=""+month;
        if(endday < 10)
        {
         end="0"+endday;  
        }
        if(month <10)
        {
         mnth="0"+month;  
        }                             
        String startstr = year+"-"+mnth+"-"+start;
        String endstr = year+"-"+mnth+"-"+end;
        Properties props = new Properties();        
        props.put("start",startstr);
        props.put("end",endstr);
        return props;
    }
        
     public static int getNumberOfDays(String date)
     {
         String[] ymd = date.split("-");
         int year = Integer.parseInt(ymd[0]);
         int month = Integer.parseInt(ymd[1]);
         int day = 1;
          
         Calendar calendar = Calendar.getInstance();
         calendar.set(year, month-1, day);        
         int numDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
         return numDays;
     } 

     public static String getPastDate(int days) {
            String pastDate = "";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//No I18N
            Date currentDate = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE,days);
            Date datePastDays = cal.getTime();
            pastDate = dateFormat.format(datePastDays);
            return pastDate;
     }
public static Properties getStartAndEndDates(){
    	 Properties dateProp = new Properties();
    	 try{
    	 String dbDate = DateAndTime.getCurrentDate_Time("yyyy-MM-dd",System.getProperty("user.timezone"));// NO I18N
    	 Calendar cal = Calendar.getInstance();
    	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// NO I18N
    	 Date startDate = df.parse(dbDate);
    	 cal.setTime(startDate);
    	 cal.add(Calendar.DATE, -7);
    	 String dateBefore7Days = df.format(cal.getTime());
    	 Properties thisWeek = DateAndTime.getWeekStartAndEndDate(dbDate,"yyyy-MM-dd");// NO I18N
    	 Properties lastWeek = DateAndTime.getWeekStartAndEndDate(dateBefore7Days,"yyyy-MM-dd");// NO I18N
    	 cal.setTime(startDate);
    	 cal.add(Calendar.DATE, 7);
    	 String dateAfter7Days = df.format(cal.getTime());
    	 Properties nextWeek = DateAndTime.getWeekStartAndEndDate(dateAfter7Days,"yyyy-MM-dd");// NO I18N
    	 df = new SimpleDateFormat("MM,yyyy");// NO I18N
    	 int thisMon = Integer.parseInt(df.format(startDate).substring(0, 2));
    	 int thisMonyear = Integer.parseInt(df.format(startDate).substring(3, 7));
    	 Properties thisMonth = DateAndTime.getMonthStartAndEndDate(thisMon,thisMonyear);
    	 cal.setTime(startDate);
    	 cal.add(Calendar.MONTH, -1);
    	 int lastMon =  Integer.parseInt(df.format(cal.getTime()).substring(0, 2));
    	 int lastMonYear = Integer.parseInt(df.format(cal.getTime()).substring(3, 7));
    	 Properties lastMonth = DateAndTime.getMonthStartAndEndDate(lastMon,lastMonYear);
    	 cal.setTime(startDate);
    	 cal.add(Calendar.MONTH,1);
    	 int nextMon =  Integer.parseInt(df.format(cal.getTime()).substring(0, 2));
    	 int nextMonYear = Integer.parseInt(df.format(cal.getTime()).substring(3, 7));
       	 Properties nextMonth = DateAndTime.getMonthStartAndEndDate(nextMon,nextMonYear);
    	 String weekStart = (String) thisWeek.get("start").toString();
    	 String weekEnd = (String) thisWeek.get("end").toString();
    	 String lastWeekStart = (String) lastWeek.get("start").toString();
    	 String lastWeekEnd = (String) lastWeek.get("end").toString();
    	 String nextWeekStart = (String) nextWeek.get("start").toString();
    	 String nextWeekEnd = (String) nextWeek.get("end").toString();
    	 String monthStart = (String) thisMonth.get("start").toString();
    	 String monthEnd = (String) thisMonth.get("end").toString();   	 
    	 String lastMonthStart = (String) lastMonth.get("start").toString();
    	 String lastMonthEnd = (String) lastMonth.get("end").toString();
    	 String nextMonthStart = (String) nextMonth.get("start").toString();
    	 String nextMonthEnd = (String) nextMonth.get("end").toString();
    	 dateProp.put("This_Week_Start",weekStart.substring(0, 19));
    	 dateProp.put("This_Week_End",weekEnd.substring(0, 19));
    	 dateProp.put("Last_Week_Start",lastWeekStart.substring(0, 19));
    	 dateProp.put("Last_Week_End",lastWeekEnd.substring(0, 19));
    	 dateProp.put("Next_Week_Start",nextWeekStart.substring(0, 19));
    	 dateProp.put("Next_Week_End",nextWeekEnd.substring(0, 19));
    	 dateProp.put("This_Month_Start",monthStart.substring(0, 19));
    	 dateProp.put("This_Month_End",monthEnd.substring(0, 19));
    	 dateProp.put("Last_Month_Start",lastMonthStart.substring(0, 19));
    	 dateProp.put("Last_Month_End",lastMonthEnd.substring(0, 19));
    	 dateProp.put("Next_Month_Start",nextMonthStart.substring(0, 19));
    	 dateProp.put("Next_Month_End",nextMonthEnd.substring(0, 19));
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return dateProp;
     }

public static Properties getDatesForLastSevenDays(){
	Properties dateprop = new Properties();
	long noOfDays = -7;
	String curDate = DateAndTime.dateString("yyyy-MM-dd");//NO I18N
	String endDate = DateAndTime.increment_Decrement_Dates(curDate,1,"yyyy-MM-dd");//NO I18N
	String startDate = DateAndTime.increment_Decrement_Dates(endDate,noOfDays,"yyyy-MM-dd");//NO I18N
	dateprop.put("startDate",startDate);
	dateprop.put("endDate", endDate);
	return dateprop;
}

public static Properties getMonthStartEndDate()
{
	Properties thisMonth = new Properties();
	try{
	String dbDate = getCurrentDate_Time("yyyy-MM-dd",System.getProperty("user.timezone"));// NO I18N
	 //Calendar cal = Calendar.getInstance();
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// NO I18N
	 Date startDate = df.parse(dbDate);
	 df = new SimpleDateFormat("MM,yyyy");// NO I18N
	 int thisMon = Integer.parseInt(df.format(startDate).substring(0, 2));
	 int thisMonyear = Integer.parseInt(df.format(startDate).substring(3, 7));
	 thisMonth = getMonthStartAndEndDate(thisMon,thisMonyear);
	}
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 return thisMonth;
}
public static Properties getLastTwelveMonths()
{
	Properties dateprop = new Properties();
	try{
	String dbDate = DateAndTime.getCurrentDate_Time("yyyy-MM-dd",System.getProperty("user.timezone"));// NO I18N
	Calendar cal = Calendar.getInstance();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// NO I18N
	Date startDate = df.parse(dbDate);
	cal.setTime(startDate);
	int startDay = cal.getActualMaximum(cal.DAY_OF_MONTH);
	DateFormat dfrmt = new SimpleDateFormat("yyyy-MM");// NO I18N
	String month = dfrmt.format(cal.getTime());
	dateprop.put("end",month+"-"+startDay);
	dateprop.put("1",month);
	for(int i=0; i< 11; i++){
	 cal.add(Calendar.MONTH, -1);
	 month = dfrmt.format(cal.getTime());
	 dateprop.put(i+2+"",month);
	}
	int endDay = cal.getActualMinimum(cal.DAY_OF_MONTH);
	dateprop.put("start",month+"-"+endDay); 
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return dateprop;
}
public static int diffBetweenTwoDays(String sDate, String eDate) throws Exception
{
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// NO I18N
	Date startDate = df.parse(sDate);
	Date endDate = df.parse(eDate);
	return (int)((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
}
public static String getCurrentTimeStamp() {
	SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//No I18N
	Date now = new Date();
	String strDate = sdfDate.format(now);
	return strDate;
}

public static String getCurrentIDCTime(String format) {
	try {
		DateFormat dateFormat = (format != null && !format.equals("")) ? new SimpleDateFormat(format) : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//NO I18N
		Calendar cal = Calendar.getInstance();
		dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));//NO I18N
		return dateFormat.format(cal.getTime());
	} catch(Exception e) {
		LOGGER.log(Level.WARNING,"Exception while getCurrentIDCTime(), "+e.getMessage(),e);
	}
	return null;
}

}



