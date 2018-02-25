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
        return this.x.getName().equals(x.getName());
    }
    
    public boolean endsWith(Identifier y)
    {
        return this.y.getName().equals(y.getName());
    }
    
    public boolean equals(Dependency dependency)
    {
        return  x.getName().equals(dependency.getX().getName()) &&
                y.getName().equals(dependency.getY().getName());
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
