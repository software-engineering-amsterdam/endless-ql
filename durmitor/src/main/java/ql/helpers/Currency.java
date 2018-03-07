package ql.helpers;

public enum Currency {

    EUR(2),
    USD(2),
    ANG(2),
    RON(2);
    
    private int decimalPlaces;
    
    public final static Currency defaultCurrency = Currency.EUR;
    
    private Currency(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
    
    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public static boolean exists(String name) {
        
        try {
            valueOf(name);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
}
