package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Visitable;

public abstract class ASTNode implements Visitable {

    private int line;
    private int column;


    /**
     * Get the node type
     * @return The type
     */
    public abstract Type getType();

    public int getLine(){
        return this.line;
    }

    public int getColumn(){
        return this.column;
    }

    public void setLocation(int line, int column){
        this.line = line;
        this.column = column;
    }
}
