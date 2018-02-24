package ql.helpers;

import ql.ast.expression.Identifier;

public class Dependency {

    private Identifier x;
    private Identifier y;

    public Dependency(Identifier x, Identifier y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Identifier getX()
    {
        return x;
    }
    
    public Identifier getY()
    {
        return y;
    }
    
    public boolean beginsWith(Identifier x)
    {
        return this.x == x;
    }
    
    public boolean endsWith(Identifier y)
    {
        return this.y == y;
    }
    
    public boolean equals(Dependency dependency)
    {
        return  x == dependency.getX() &&
                y == dependency.getY();
    }
    
    public String toString()
    {
        return x+" at " + x.getLocation()+" depends on "+y+" at "+y.getLocation();
    }
    
    public void print()
    {
        System.out.println(toString());
    }
}
