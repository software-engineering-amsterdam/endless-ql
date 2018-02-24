package ql.visitors;

import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.gui.GUI;
import ql.gui.GUIQuestion;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private GUI gui;

    public ASTtoGUI(GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }
    @Override
    public void visit(IfThen stmt) {
    }
    @Override
    public void visit(IfThenElse stmt) {
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        new GUIQuestion(stmt,gui.panel);
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        new GUIQuestion(stmt,gui.panel);
    }
}
