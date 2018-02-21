package classes;

public class Configuration {
    private String currencyCode = "EUR";

    public String getCurrencyCode(){
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode){
        this.currencyCode = currencyCode;
    }
}
