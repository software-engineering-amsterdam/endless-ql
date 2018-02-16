package ql.checker;

import java.util.ArrayList;

import ql.ast.expression.Identifier;
import ql.ast.form.Form;
import ql.visitors.IdCollector;

public class TypeChecker {

    private ArrayList<Identifier> ids;
    private Form form;
    
    public TypeChecker(Form form) {
        this.ids    = new ArrayList<Identifier>();
        this.form   = form;
    }
    
    public ArrayList<Identifier> getIdentifiers() {
        return ids;
    }
    
    public ArrayList<Identifier> collectIdentifiers() {
        
        IdCollector ic = new IdCollector(ids);
        
        ic.visit(form.getBlock());
        
        return ids;
    }
    
    public ArrayList<Identifier> getDuplicateIds() {
        
        ArrayList<Identifier> copy = new ArrayList<Identifier>(ids);
        ArrayList<Identifier> dups = new ArrayList<Identifier>();
        
        // Take an identifier; original
        for(int o = 0; o < copy.size(); o++ ) {
            Identifier orig = copy.get(o);
            
            // Take one of the following identifiers
            for(int d = copy.size() - 1; d > o; d-- ) {
                Identifier dup = copy.get(d);
                
                // Collect the identifiers with the same name as the original
                if(orig.getName().equals(dup.getName())) {
                    if(!dups.contains(orig)) dups.add(orig);
                    dups.add(dup);
                    copy.remove(d);
                }
            }
        }
        
        return dups;
    }
}
