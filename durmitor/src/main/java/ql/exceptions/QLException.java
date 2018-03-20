package ql.exceptions;

import ql.helpers.Location;

public abstract class QLException extends RuntimeException {

    private static final long serialVersionUID = -4521749810520204195L;
    
    protected String message;
    protected Location location;

    public QLException() {
        message     = "";
        location    = new Location();
    }

    public String getMessage() {
        return message;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public String toString() {
        return "["+location+"] " + message;
    }
}
