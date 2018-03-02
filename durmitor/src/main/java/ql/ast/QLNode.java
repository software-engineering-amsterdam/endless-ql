package ql.ast;

import java.util.Observable;

import ql.helpers.Location;

public class QLNode extends Observable {

    private Location location;
    
    public QLNode() {
        location = new Location();
    }
    
    public QLNode setLocation(Location location) {
        
        this.location = location;
        
        return this;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public QLNode updateLength() {
        
        this.location.setLength(toString().length());
        
        return this;
    }
}
