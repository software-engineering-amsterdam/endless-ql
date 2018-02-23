package ql.visitors;

import java.util.List;

import javax.swing.JPanel;

import ql.ast.QLNode;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.gui.GUI;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private GUI gui;

    public ASTtoGUI(GUI gui) {
        this.gui = gui;
    }
    
    public void visit(QLNode node) {
        visit(node, gui.panel);
    }
    
    public void visit(QLNode node, JPanel parentPanel) {
        Form form = (Form) node;
        visitBlock(form.getBlock(), parentPanel);
    }
    
    private void visitBlock(Block block, JPanel parentPanel) {
        List<Statement> statements = block.getStatements();
        statements.forEach(statement -> {
            
        });
    }
    
    @Override
    public void visit(Block stmts) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void visit(IfThen stmt) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void visit(IfThenElse stmt) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        // TODO Auto-generated method stub
        
    }
}
