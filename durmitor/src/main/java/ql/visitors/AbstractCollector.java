package ql.visitors;

import java.util.ArrayList;
import java.util.List;

import ql.ast.form.Form;

public abstract class AbstractCollector<T> {
    
    protected List<T> collection;
    
    public AbstractCollector() {
        collection = new ArrayList<T>();
    }
    
    public abstract List<T> collect(Form form);
}
