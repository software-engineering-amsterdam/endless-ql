package ql.helpers;

public enum Month {

    JANUARY("jan",1),
    FEBRUARY("feb",2),
    MARCH("mar",3),
    APRIL("apr",4),
    MAY("may",5),
    JUNE("jun",6),
    JULY("jul",7),
    AUGUST("aug",8),
    SEPTEMBER("sep",9),
    OCTOBER("oct",10),
    NOVEMBER("nov",11),
    DECEMBER("dec",12);
    
    String shortName;
    int number;
    int calendarNumber;
    
    private Month(String shortName, int number) {
        this.shortName  = shortName;
        this.number     = number;
    }
    
    public String getShortName() {
        return shortName;
    }
    
    public int getNumber() {
        return number;
    }

    public int getCalendarNumber() {
        return number - 1;
    }
    
    public static int parse(String month) {
        
        int monthNumber;
        
        try {
            monthNumber = Integer.parseInt(month.trim());
        } catch(NumberFormatException nfe) {
            monthNumber = -1;
        }
        
        for(Month m : values())
        {
            if(m.name().equalsIgnoreCase(month))
            {
                return m.getCalendarNumber();
            }
            else if(m.getShortName().equalsIgnoreCase(month))
            {
                return m.getCalendarNumber();
            }
            else if(m.getNumber() == monthNumber)
            {
                    return m.getCalendarNumber();
            }
        }
        
        return -1;
    }
}
