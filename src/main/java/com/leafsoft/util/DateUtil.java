package com.leafsoft.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtil {
	private static final Logger LOGGER = Logger.getLogger(DateUtil.class.getName());

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd"); // No I18N
	private static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat(DateAndTime.DBDATEFORMAT); // No I18N

	public static String dateManipulateWithNow (int days)  // days = +ve to add // days = -ve to substract 
	{
		Calendar now = Calendar.getInstance();
		try {
			now.add(Calendar.DATE,days);

		} catch(Exception e) {
			LOGGER.log(Level.WARNING, "dateManipulateWithNow()::"+e.getMessage(), e);
		}
		int month = now.get(Calendar.MONTH) + 1;	
		String mont = month+"";
		if(month < 10) {
			mont = "0"+month;
		}
		return now.get(Calendar.YEAR) + "-" + mont + "-" + now.get(Calendar.DATE);	
	}
	
	public static String daysDiff(String dateStart, String dateStop, String format) 
	{
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date d1 = df.parse(dateStart);
			Date d2 = df.parse(dateStop);

			long diff = d2.getTime() - d1.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			return Long.valueOf(diffDays).toString();
		} catch(Exception e) {
                        LOGGER.log(Level.WARNING, "daysDiff()::"+e.getMessage(), e);
                }
                return "0";
	}
	public static String dateManipulate (String dateStart, int days)  // days = +ve to add // days = -ve to substract 
        {
                Calendar c = Calendar.getInstance();
                try {
			Date d1 = DATEFORMAT.parse(dateStart);
			c.setTime(d1);
                        c.add(Calendar.DATE,days);

                } catch(Exception e) {
                        LOGGER.log(Level.WARNING, "dateManipulate()::"+e.getMessage(), e);
                }
		int month = c.get(Calendar.MONTH) + 1;
                String mont = month+"";
                if(month < 10) {
                        mont = "0"+month;
                }
                int date = c.get(Calendar.DATE);
                String dateStr = date+"";
                if(date < 10) {
                    dateStr = "0"+date;
                }
                return c.get(Calendar.YEAR) + "-" + mont + "-" + dateStr;
        }
	public static String dateTimeManipulateWithNow (int days)  // days = +ve to add // days = -ve to substract 
        {
		return dateManipulateWithNow(days) + " 00:00:00";
        }

	// method to convert 2010-01-20 00:00:00 to 20 Jan, 2010
	public static String convertMonthPartFormat (String dateStart)	
	{
		try {
			Date d1 = DATETIMEFORMAT.parse(dateStart);
			SimpleDateFormat myformat = new SimpleDateFormat("dd MMM, yyyy"); // No I18N
			return myformat.format(d1);

		} catch(Exception e) {
			LOGGER.log(Level.WARNING, "convertMonthPartFormat()::"+e.getMessage(), e);
		}
		return dateStart;
	}
	// method to convert date to 20 Janaury 2010
        public static String convertMonthFullFormat (String dateStart,String format)
        {
                try {
                        SimpleDateFormat dt = new SimpleDateFormat(format);
                        Date d1 = dt.parse(dateStart);
                        SimpleDateFormat myformat = new SimpleDateFormat("dd MMMM yyyy"); // No I18N
                        return myformat.format(d1);

                } catch(Exception e) {
                        LOGGER.log(Level.WARNING, "convertMonthFullFormat()::"+e.getMessage(), e);
                }
                return dateStart;
        }
        
        // method to convert date to Janaury, 2010
        public static String convertMonthYearFormat (String dateStart,String format)
        {
                try {
                        SimpleDateFormat dt = new SimpleDateFormat(format);
                        Date d1 = dt.parse(dateStart);
                        SimpleDateFormat myformat = new SimpleDateFormat("MMMM, yyyy"); // No I18N
                        return myformat.format(d1);

                } catch(Exception e) {
                        LOGGER.log(Level.WARNING, "convertMonthFullFormat()::"+e.getMessage(), e);
                }
                return dateStart;
        }
        
        // method to convert 20-01-2010 00:00:00 to 20-Jan-10
        public static String convertMonthThisFormat (String date)
        {
                try {
                        Date d1 = DATETIMEFORMAT.parse(date);
                        SimpleDateFormat myformat = new SimpleDateFormat("dd-MMM-yy"); // No I18N
                        return myformat.format(d1);

                } catch(Exception e) {
                        LOGGER.log(Level.WARNING, "convertMonthThisFormat()::"+e.getMessage(), e);
                }
                return date;
        }
        public static String getPaypalFormat(Date date) {
        	try {
                SimpleDateFormat myformat = new SimpleDateFormat("MMddyyyy"); // No I18N
                return myformat.format(date);
	        } catch(Exception e) {
	                LOGGER.log(Level.WARNING, "getPaypalFormat()::"+e.getMessage(), e);
	        }
	        return date.toString();
        }
        public static Date getPrevDueDate(int payperiod, long time) {
        	Calendar now = Calendar.getInstance();
        	now.setTimeInMillis(time);
        	if(payperiod==1) {
        		now.add(Calendar.MONTH, -1);
        	} else if(payperiod==2) {
        		now.add(Calendar.MONTH, -3);
        	} else if(payperiod==3) {
        		now.add(Calendar.MONTH, -6);
        	} else if(payperiod==4) {
        		now.add(Calendar.YEAR, -1);
        	}
        	return now.getTime();
        }
        public static long getTotalDays(int payperiod, long time) {
        	Calendar now = Calendar.getInstance();
        	now.setTimeInMillis(time);
        	Calendar later = Calendar.getInstance();
        	if(payperiod==1) {
        		now.add(Calendar.MONTH, -1);
        		return now.getActualMaximum(Calendar.DAY_OF_MONTH);
        	} else if(payperiod==2) {
        		now.add(Calendar.MONTH, -3);
        		later.setTimeInMillis(now.getTimeInMillis());
        	} else if(payperiod==3) {
        		now.add(Calendar.MONTH, -6);
        		later.setTimeInMillis(now.getTimeInMillis());
        	} else if(payperiod==4) {
        		now.add(Calendar.YEAR, -1);
        		later.setTimeInMillis(now.getTimeInMillis());
        	}
        	now.setTimeInMillis(time);
        	return daysBetween(later, now);
        }
       
    	/** Using Calendar - THE CORRECT WAY**/
    	//assert: startDate must be before endDate
    	public static long daysBetween(Calendar startDate, Calendar endDate) {
	    	  Calendar date = (Calendar) startDate.clone();
	    	  long daysBetween = 0;
	    	  while (date.before(endDate)) {
	    	    date.add(Calendar.DAY_OF_MONTH, 1);
	    	    daysBetween++;
	    	  }
	    	  return daysBetween;
    	}
    	
    	//Days between only two dates without time
    	public static long daysBetweenForDate(Calendar startDate, Calendar endDate) {
	    	  Calendar date = (Calendar) startDate.clone();
	    	  date.set(Calendar.HOUR_OF_DAY, 0);
	    	  date.set(Calendar.MINUTE, 0);
	    	  date.set(Calendar.SECOND, 0);
	    	  date.set(Calendar.MILLISECOND, 0);
	    	  
	    	  endDate.set(Calendar.HOUR_OF_DAY, 0);
	    	  endDate.set(Calendar.MINUTE, 0);
	    	  endDate.set(Calendar.SECOND, 0);
	    	  endDate.set(Calendar.MILLISECOND, 0);

	    	  long daysBetween = 0;
	    	  while (date.before(endDate)) {
	    	    date.add(Calendar.DAY_OF_MONTH, 1);
	    	    daysBetween++;
	    	  }
	    	  return daysBetween;
  	   }
    	
        public static String calcDHMS(long timeDiff) {
            long millisPerMinute = 1000 * 60;
            long millisPerHour = millisPerMinute * 60;
            long millisPerDay = millisPerHour * 24;
            long days = timeDiff/millisPerDay;
            long hours = (timeDiff%millisPerDay)/millisPerHour;
            long minutes = (timeDiff%millisPerHour)/millisPerMinute;
            long seconds = (timeDiff%millisPerMinute)/1000;
            StringBuffer buf = new StringBuffer();
            if (days > 0)
            {
                    buf.append(days + " days");//NO I18N
                    buf.append(' ');//NO I18N
            }
            if (hours > 0)
            {
                    buf.append(hours + " hrs");//NO I18N
                    buf.append(' ');//NO I18N
            }
            if (minutes > 0)
            {
                    buf.append(minutes + " mins");//NO I18N
                    buf.append(' ');//NO I18N
            }
            if (seconds > 0)
            {
                    buf.append(seconds + " secs");//NO I18N
            }
            if(buf.length() == 0)
            {
                    buf.append('-');//NO I18N
            }
            String msg = buf.toString();
            return msg;
    }
        
    public static Timestamp stringToTimeStamp(String date) {
    	if(date != null) {
    		try {
	    		Date d = DATEFORMAT.parse(date);
	        	return new Timestamp(d.getTime());
    		} catch(Exception e) {
	            LOGGER.log(Level.WARNING, "stringToTimeStamp()::"+e.getMessage(), e);
	        }
    	} return null;
    }    
        
    public static Object[] stringToTimeStamp(String[] dates) {
    	if(dates != null) {
	        Object[] arr = new Object[dates.length];
	        try {
		        for(int i=0; i < dates.length; i++) {
		        	Date d = DATEFORMAT.parse(dates[i]);
		        	arr[i] = new Timestamp(d.getTime());
		        } 
	        } catch(Exception e) {
	            LOGGER.log(Level.WARNING, "stringToTimeStamp()::"+e.getMessage(), e);
	        }
	        return arr;
    	} return null;
    }
    
    public static String getYYYYMMDDFormat(Date date) {
    	try {
            return DATEFORMAT.format(date);
        } catch(Exception e) {
                LOGGER.log(Level.WARNING, "getPaypalFormat()::"+e.getMessage(), e);
        }
        return date.toString();
    }

}
