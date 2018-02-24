package ql.helpers;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    public List<List<Identifier>> getCyclicDependencies() 
    {
        List<List<Identifier>> cyclicDependencies = new ArrayList<List<Identifier>>();
        
        for(Dependency d : getTransitiveClosure())
        {
            if(d.getX() == d.getY())
            {
                cyclicDependencies.add(getCircle(d.getX()));
            }
        }
        
        return cyclicDependencies;
    }
    
    private List<Identifier> getCircle(Identifier x)
    {
        boolean nonCyclic   = true;
        List<Identifier> circle = new ArrayList<Identifier>(Arrays.asList(x));
        
        while(nonCyclic)
        {
            for(int i = 0; i < circle.size(); i++)
            {
                List<Identifier> ys = getYsByX(circle.get(i));
                
                if(ys.contains(x))
                {
                    nonCyclic   = false;
                    circle      = circle.subList(0, i+1);
                    circle.add(x);
                    break;
                }
                else
                {
                    circle.addAll(i+1, ys);
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
        for(Dependency d : this) System.out.println(d);
        System.out.println();
    }
}
