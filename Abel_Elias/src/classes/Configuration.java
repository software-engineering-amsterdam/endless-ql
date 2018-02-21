package classes;

public class Config {
    private String currencyCode = EUR;

    String getCurrencyCode(){
        return currencyCode;
    }

    void setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
}
