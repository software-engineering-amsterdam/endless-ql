package ql.exceptions;

public class InvalidDate extends QLException {

    private static final long serialVersionUID = 4902785539326779521L;

    public InvalidDate(String date) {
        
        message     = "Invalid date ["+date+"]";
//        location    = to.getLocation();
    }
}
