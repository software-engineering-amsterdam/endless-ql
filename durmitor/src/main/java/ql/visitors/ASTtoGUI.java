package ql.visitors;

import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.gui.GUI;
import ql.gui.IfThenElsePanel;
import ql.gui.IfThenPanel;
import ql.gui.QuestionPanel;
import ql.gui.fields.actionlisteners.AbstractActionListener;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private JPanel parentPanel;
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
        
        final JPanel thenPanel  = new IfThenPanel(stmt.getCondition(), "then");
        panelsCollection.put(thenPanel, thenPanel.isVisible());
        
        parentPanel.add(thenPanel);
        parentPanel = thenPanel;
        stmt.getThenStatement().accept(this);
        parentPanel.revalidate();
    }
    @Override
    public void visit(IfThenElse stmt) {
        
        final IfThenElsePanel thenElsePanel = new IfThenElsePanel(stmt.getCondition(), "ThenElse");
        panelsCollection.put(thenElsePanel, thenElsePanel.isVisible());
        
        parentPanel.add(thenElsePanel);
        parentPanel = thenElsePanel;
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
        parentPanel.revalidate();
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        
        QuestionPanel question = new QuestionPanel(stmt);
        parentPanel.add(question);
        panelsCollection.put(question, true);
        variableCollection.put(stmt.getIdentifier(), stmt.getIdentifier().getName());
        parentPanel.revalidate();
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        
        boolean render              = !stmt.getComputation().evaluate().isUndefined();
        final JPanel questionPanel  = createContainerPanel("computed");
        
        parentPanel.add(questionPanel);
        questionPanel.add(new QuestionPanel(stmt));
        AbstractActionListener.setVisibility(questionPanel, true);
        //AbstractActionListener.setVisibility(questionPanel, render);
        panelsCollection.put(questionPanel, render);
        variableCollection.put(stmt.getIdentifier(), stmt.getIdentifier().getName());
        questionPanel.revalidate();
        
        parentPanel.revalidate();
    }
    
    private JPanel createContainerPanel(String name) {
        JPanel containerPanel = new JPanel();
        containerPanel.setName(name);
        containerPanel.setLayout(
                new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
        return containerPanel;
    }
}
