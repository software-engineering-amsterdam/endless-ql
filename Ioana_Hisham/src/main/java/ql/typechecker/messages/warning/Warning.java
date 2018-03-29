package ql.typechecker.messages.warning;

public class Warning {
    private final String warning;

    Warning(String warning){
        this.warning = warning;
    }

    public String message(){
        return warning;
    }
}
