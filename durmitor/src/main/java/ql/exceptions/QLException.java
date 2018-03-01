package ql.exceptions;

import ql.helpers.Location;

public abstract class QLException {

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

    public String toString() {
        return "["+location+"] " + message;
    }
}
