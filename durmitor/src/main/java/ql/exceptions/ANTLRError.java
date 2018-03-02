package ql.exceptions;

import ql.helpers.Location;

public class ANTLRError extends QLException {
    
    public ANTLRError(String message, int line, int column) {
        
        String first    = String.valueOf(message.charAt(0)).toUpperCase();
        message         = first.concat(message.substring(1));
        location        = new Location(line,column);
    }
}
