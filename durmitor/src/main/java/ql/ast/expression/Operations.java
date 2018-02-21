package ql.ast.expression;

import java.util.ArrayList;

public class Operations extends ArrayList<Operation> {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public boolean contains(Operation query) {
        
        for(Operation op : this) if(op.equals(query)) return true;
        
        return false;
    }
}
