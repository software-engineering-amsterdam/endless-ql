package ql.visitors;

import java.util.LinkedHashMap;

import javax.swing.JPanel;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.gui.ComputedQuestionPanel;
import ql.gui.GUI;
import ql.gui.IfThenElsePanel;
import ql.gui.IfThenPanel;
import ql.gui.QuestionPanel;
import ql.visitors.checker.checkers.ExpressionVisitorIdentifier;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private JPanel parentPanel;
    //TODO: temporary fields for console output, remove
    public LinkedHashMap<JPanel, Boolean> panelsCollection = new LinkedHashMap<>();
    public LinkedHashMap<Identifier, String> variableCollection = new LinkedHashMap<>();

    public ASTtoGUI(GUI gui) {
        this.parentPanel = gui.panel;
        panelsCollection.put(this.parentPanel, this.parentPanel.isVisible());
    }
    
    @Override
    public void visit(Block block) {
        
        final JPanel blockParentPanel = parentPanel;
        
        block.getStatements().forEach(stmt -> {
            parentPanel = blockParentPanel;
            stmt.accept(this);
        });
        
        parentPanel = blockParentPanel;
    }
    
    @Override
    public void visit(IfThen stmt) {
        
        final IfThenPanel thenPanel  = new IfThenPanel(stmt.getCondition());
        panelsCollection.put(thenPanel, thenPanel.isVisible());
        
        parentPanel.add(thenPanel);
        parentPanel = thenPanel;
        
        stmt.getCondition().accept(new ExpressionVisitorIdentifier()).forEach(identifier -> {
            identifier.addObserver(thenPanel);
        });
        stmt.getThenStatement().accept(this);
        
        thenPanel.revalidate();
    }
    @Override
    public void visit(IfThenElse stmt) {
        
        final IfThenElsePanel thenElsePanel = new IfThenElsePanel(stmt.getCondition());
        panelsCollection.put(thenElsePanel, thenElsePanel.isVisible());
        
        parentPanel.add(thenElsePanel);
        parentPanel = thenElsePanel;
        
        stmt.getCondition().accept(new ExpressionVisitorIdentifier()).forEach(identifier -> {
            identifier.addObserver(thenElsePanel);
        });
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
        
        thenElsePanel.revalidate();
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        
        final QuestionPanel question = new QuestionPanel(stmt);
        
        parentPanel.add(question);
        parentPanel.revalidate();
        
        panelsCollection.put(question, question.isVisible());
        variableCollection.put(stmt.getIdentifier(), stmt.getIdentifier().getName());
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        
        final ComputedQuestionPanel computedQuestion = new ComputedQuestionPanel(stmt);
        
        parentPanel.add(computedQuestion);
        parentPanel.revalidate();
        
        panelsCollection.put(computedQuestion, computedQuestion.isVisible());
        variableCollection.put(stmt.getIdentifier(), stmt.getIdentifier().getName());
    }
}
