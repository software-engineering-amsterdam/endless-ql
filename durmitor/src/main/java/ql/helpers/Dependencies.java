package ql.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ql.ast.expression.Identifier;

@SuppressWarnings("serial")
public class Dependencies extends ArrayList<Dependency> {

    @Override
    public boolean add(Dependency dependency)
    {
        if(this.contains(dependency)) return true;
        
        this.add(this.size(), dependency);
        
        return true;
    }
    
    public boolean contains(Dependency dependency)
    {
        for(Dependency d : this)
        {
            if(d.equals(dependency)) return true;
        }
        
        return false;
    }
    
    public Dependencies getByX(Identifier x)
    {
        Dependencies matches = new Dependencies();
        
        for(Dependency d : this)
        {
            if(d.beginsWith(x)) matches.add(d);
        }
        
        return matches;
    }
    
    public Dependencies getByY(Identifier y)
    {
        Dependencies matches = new Dependencies();
        
        for(Dependency d : this)
        {
            if(d.endsWith(y)) matches.add(d);
        }
        
        return matches;
    }
    
    public List<Identifier> getYsByX(Identifier x)
    {
        List<Identifier> ys = getByX(x).getRange();
        
        return ys;
    }
    
    public List<Identifier> getDomain()
    {
        List<Identifier> domains = new ArrayList<Identifier>();
        
        for(Dependency d : this)
        {
            if(!domains.contains(d.getX()))
            {
                domains.add(d.getX());
            }
        }
        
        return domains;
    }
    
    public List<Identifier> getRange()
    {
        List<Identifier> domains = new ArrayList<Identifier>();
        
        for(Dependency d : this)
        {
            if(!domains.contains(d.getY()))
            {
                domains.add(d.getY());
            }
        }
        
        return domains;
    }
    
    public List<LinkedList<Identifier>> getCyclicDependencies() 
    {
        List<LinkedList<Identifier>> cyclicDependencies = new ArrayList<LinkedList<Identifier>>();
        
        for(Dependency d : getTransitiveClosure())
        {
            if(d.getX().equals(d.getY()))
            {
                cyclicDependencies.add(getCircle(d));
            }
        }
        
        return cyclicDependencies;
    }
    
    private LinkedList<Identifier> getCircle(Dependency d)
    {
        boolean nonCyclic               = true;
        LinkedList<Identifier> circle   = new LinkedList<>(Arrays.asList(d.getX()));
        
        while(nonCyclic)
        {
            for(int i = 0; i < circle.size(); i++)
            {
                Dependencies ys = getByX(circle.get(i));
                
                if(ys.getByY(d.getX()).isEmpty())
                {
                    circle.addAll(i+1, ys.getRange());
                }
                else
                {
                    nonCyclic   = false;
                    circle      = new LinkedList<Identifier>(circle.subList(0, i+1));
                    circle.add(d.getY());
                    break;
                }
            }
            
            nonCyclic = false;
        }
        
        return circle;
    }
    
    private Dependencies getTransitiveClosure()
    {
        Dependencies closure = new Dependencies();
        
        closure.addAll(this);
        
        closure.closeTransitively();
        
        return closure;
    }
    
    private void closeTransitively()
    {
        Dependencies zs = new Dependencies();
        
        for(Dependency x : this)
        {
            for(Identifier y : getYsByX(x.getY()))
            {
                Dependency z = new Dependency(x.getX(), y);
                
                if(!contains(z)) zs.add(z);
            }
        }
        
        if(!zs.isEmpty())
        {
            this.addAll(zs);
            
            closeTransitively();
        }
    }
    
    public void print()
    {
        for(Dependency d : this) d.print();
    }
}
