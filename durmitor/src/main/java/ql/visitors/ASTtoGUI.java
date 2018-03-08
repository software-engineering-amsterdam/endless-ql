package ql.visitors;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.type.Bool;
import ql.gui.GUI;
import ql.gui.GUIQuestion;
import ql.gui.fields.actionlisteners.AbstractActionListener;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private JPanel parentPanel;

    public ASTtoGUI(GUI gui) {
        this.parentPanel = gui.panel;
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
        
        System.out.println("parentPanel ifthen    : "+parentPanel.hashCode());
        boolean render          = ((BoolLiteral) new Bool().parse(stmt.getCondition().evaluate())).getValue();
        final JPanel thenPanel  = createContainerPanel();
        
        parentPanel.add(thenPanel);
        
        parentPanel = thenPanel;
        stmt.getThenStatement().accept(this);
        AbstractActionListener.setVisibility(thenPanel, render);
        thenPanel.revalidate();
        
        parentPanel.revalidate();
    }
    @Override
    public void visit(IfThenElse stmt) {
        
        boolean render          = ((BoolLiteral) new Bool().parse(stmt.getCondition().evaluate())).getValue();
        final JPanel thenPanel  = createContainerPanel();
        final JPanel elsePanel  = createContainerPanel();
        
        parentPanel.add(thenPanel);
        parentPanel.add(elsePanel);
        
        parentPanel = thenPanel;
        stmt.getThenStatement().accept(this);
        AbstractActionListener.setVisibility(thenPanel, render);
        thenPanel.revalidate();
        
        parentPanel = elsePanel;
        stmt.getElseStatement().accept(this);
        AbstractActionListener.setVisibility(elsePanel, !render);
        elsePanel.revalidate();
        
        parentPanel.revalidate();
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        
        parentPanel.add(new GUIQuestion(stmt));
        parentPanel.revalidate();
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        
        boolean render              = !stmt.getComputation().evaluate().isUndefined();
        final JPanel questionPanel  = createContainerPanel();
        
        parentPanel.add(questionPanel);
        questionPanel.add(new GUIQuestion(stmt));
        AbstractActionListener.setVisibility(questionPanel, render);
        questionPanel.revalidate();
        
        parentPanel.revalidate();
    }
    
    private JPanel createContainerPanel() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(
                new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
        return containerPanel;
    }
}
