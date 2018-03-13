package ql.helpers;

import java.util.Calendar;
import java.util.Date;

import ql.exceptions.InvalidDate;
import ql.exceptions.QLException;

public class DateParser {
    
    private static String inputDate;
    private static int day              = -1;
    private static int month            = -1;
    private static int year             = -1;
    private static String[] suffixes    = new String[] {"st","nd","rd","th"};
    private static String[] separators  = new String[] {"-","/","."," "};
    
    public static Date parse(String date) {
        
        Date parsed         = new java.util.Date();
        boolean valid       = true;
        inputDate           = date;
        String separator    = getSeparator(date);
        
        try {
            parsed = fromYMD(date, separator);
        } catch(QLException fromYMD) {
            valid = false;
        }
        
        if(valid) return parsed;
        
        try {
            parsed = fromYDM(date, separator);
        } catch(QLException fromYDM) {
            valid = false;
        }
        
        if(valid) return parsed;
        
        try {
            parsed = fromMDY(date, separator);
        } catch(QLException fromMDY) {
            valid = false;
        }
        
        if(valid) return parsed;
        
        return fromDMY(date, separator);
    }
    
    public static Date fromYMD(String date, String separator) {
        
        inputDate = date;
        
        String[] parts = date.split(separator);
        
        if(parts.length == 3)
        {
            year    = toYear(parts[0]);
            month   = toMonth(parts[1]);
            day     = toDay(parts[2]);
        }
        
        return createDate();
    }
    
    public static Date fromYDM(String date, String separator) {
        
        inputDate = date;
        
        String[] parts = date.split(separator);
        
        if(parts.length == 3)
        {
            year    = toYear(parts[0]);
            day     = toDay(parts[1]);
            month   = toMonth(parts[2]);
        }
        
        return createDate();
    }
    
    
    public static Date fromDMY(String date, String separator) {
        
        inputDate = date;
        
        String[] parts = date.split(separator);
        
        if(parts.length == 3)
        {
            day     = toDay(parts[0]);
            month   = toMonth(parts[1]);
            year    = toYear(parts[2]);
        }
        
        return createDate();
    }
    
    
    public static Date fromMDY(String date, String separator) {
        
        inputDate = date;
        
        String[] parts = date.split(separator);
        
        if(parts.length == 3)
        {
            month   = toMonth(parts[0]);
            day     = toDay(parts[1]);
            year    = toYear(parts[2]);
        }
        
        return createDate();
    }
    
    
    private static Date createDate() {
        
        Calendar calendar = new Calendar.Builder().setDate(year, month, day).build();
        
        if(isValidDate(calendar)) return calendar.getTime();
        
        throw new InvalidDate(inputDate);
    }
    
    private static boolean isValidDate(Calendar calendar) {
        
        if(calendar.get(Calendar.DAY_OF_MONTH) == day && calendar.get(Calendar.MONTH) == month && calendar.get(Calendar.YEAR) == year) 
        {
            return true;
        }
        
        return false;
    }
    
    
    private static int toDay(String day) {
        
        String suffix = getSuffix(day);
        
        day = day.replace(suffix, "");
        
        return toNumber(day);
    }
    

    private static int toMonth(String month) {
        return Month.parse(month);
    }
    

    private static int toYear(String year) {
        return toNumber(year);
    }
    

    private static String getSuffix(String day) {
        
        for(String suffix:suffixes) if(day.trim().toLowerCase().endsWith(suffix)) return suffix;
        
        return "";
    }
    
    private static String getSeparator(String date) {
        
        for(String s : separators)
        {
            if(date.contains(s))
            {
                return s;
            }
        }
        
        return " ";
    }
    

    private static int toNumber(String string) {
        
        int number;
        
        try
        {
            number = Integer.parseInt(string.trim());
        }
        catch(NumberFormatException nfe)
        {
            number = -1;
        }
        
        return number;
    }
}
