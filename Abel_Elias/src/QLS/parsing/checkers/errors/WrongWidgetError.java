package QLS.parsing.checkers.errors;

public class WrongWidgetError extends Error {
    public WrongWidgetError(String widget, String question){
        super("The widget: " + widget + " cannot be applied to question: " + question);
    }
}
