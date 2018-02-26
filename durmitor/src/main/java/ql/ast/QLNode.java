package ql.ast;

import ql.helpers.Location;

public class QLNode {

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
