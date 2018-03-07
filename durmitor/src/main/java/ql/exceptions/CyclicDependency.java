package ql.exceptions;

import java.util.LinkedList;

import ql.ast.expression.Identifier;

public class CyclicDependency extends QLException {

    public CyclicDependency(LinkedList<Identifier> cyclicDependency) {
        
        Identifier first    = cyclicDependency.removeFirst();
        location            = first.getLocation();
        message             = "Cyclic dependency found on ["+first.getName()+"] from ";
        message            += "[ "+first.getName() + " (" + first.getLocation() + ") ]";
        
        for(int i = 0; i < cyclicDependency.size(); i++)
        {
            Identifier link = cyclicDependency.get(i);
            
            message += " to [ "+link.getName() + " ("+link.getLocation()+") ]";
        }
    }
}
