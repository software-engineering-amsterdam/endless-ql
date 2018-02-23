package ql.helpers.symboltable;

import java.util.ArrayList;
import java.util.List;

import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.visitors.interfaces.StatementVisitor;

@SuppressWarnings("serial")
public class SymbolTable extends ArrayList<Symbol> implements StatementVisitor {

    public SymbolTable(Form form) {
        visit(form.getBlock());
    }
    
    public Type getType(String name) {
        
        int index = getFirstIndexOf(name);
        
        return (index < 0)? new Undefined() : this.get(index).getType();
    }
    
    public List<String> getNames() {
        
        List<String> names = new ArrayList<String>();
        
        for(Symbol symbol : this) 
        {
            if(!names.contains(symbol.getIdentifier().getName()))
            {
                names.add(symbol.getIdentifier().getName());
            }
        }
        
        return names;
    }
    
    public boolean add(Symbol symbol) {
        
        if(!this.contains(symbol)) 
        {
            this.add(this.size(),symbol);
        }
            
        return true;
    }
    
    public boolean contains(Symbol query) {
        
        for(Symbol symbol : this)
        {
            if(symbol.equals(query)) return true;
        }
        
        return false;
    }
    
    public boolean contains(String name) {
        return getFirstIndexOf(name) > -1;
    }
    
    public int getNrOccurences(String name) {
        
        int count = 0;
        
        for(Symbol symbol : this) 
        {
            if(symbol.getIdentifier().getName().equals(name)) count++;
        }
        
        return count;
    }
    
    public int getFirstIndexOf(String name) {
        
        for(int i = 0; i < this.size(); i++)
        {
            if(this.get(i).getIdentifier().getName().equals(name)) return i;
        }
        
        return -1;
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }
    
    @Override
    public void visit(IfThen stmt) {
        stmt.getThenStatement().accept(this);
    }
    
    @Override
    public void visit(IfThenElse stmt) {
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }
    
    @Override
    public void visit(AnswerableQuestion stmt) {
        this.add(new Symbol(stmt.getIdentifier(), stmt.getType()));
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        this.add(new Symbol(stmt.getIdentifier(), stmt.getType()));
    }
}
