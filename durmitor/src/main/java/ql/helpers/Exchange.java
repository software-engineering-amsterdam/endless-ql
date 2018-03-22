package ql.helpers;

import java.util.HashMap;
import java.util.Map;

public class Exchange {

    private static final Map<String,Double> exchangeRates = populate();
    
    public static boolean hasRateFor(Currency source, Currency target) {
        
        if(source.equals(target)) return true;
        
        String key = source.name() + "_" + target.name();
        
        return exchangeRates.containsKey(key);
    }
    
    public static double getExchangeRate(Currency source, Currency target) {
        
        if(source.equals(target)) return 1.0;
        
        String key = source.name() + "_" + target.name();
        
        return exchangeRates.getOrDefault(key, -1.0);
    }
    
    private static HashMap<String, Double> populate() {
        
        HashMap<String, Double> rates = new HashMap<String, Double>();
        
        rates.put("EUR_USD", 1.23185);
        rates.put("EUR_ANG", 2.19784827);
        rates.put("EUR_RON", 4.65939178);
        rates.put("USD_EUR", 0.811787149);
        rates.put("ANG_EUR", 0.454990462);
        rates.put("RON_EUR", 0.214620287);
        
        return rates;
    }
}
