package ql.ast;

import ql.helpers.Location;

public class QLNode {

    private Location location;
    
    public QLNode() {
        location = new Location();
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Location getLocation() {
        return location;
    }
}
