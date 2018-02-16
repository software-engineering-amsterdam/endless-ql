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
}
