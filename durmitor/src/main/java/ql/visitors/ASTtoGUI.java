package ql.visitors;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
        final JPanel panel = addContainerPanel();
        parentPanel.add(panel);
        parentPanel.revalidate();
        parentPanel = panel;
        block.getStatements().forEach(stmt -> { 
            stmt.accept(this); 
        });
    }
    @Override
    public void visit(IfThen stmt) {
        final JPanel panel = addContainerPanel();
        parentPanel.add(panel);
        parentPanel.revalidate();
        parentPanel = panel;
        ql.ast.expression.literal.BoolLiteral res = (ql.ast.expression.literal.BoolLiteral) new Bool().parse(stmt.getCondition().evaluate());
        AbstractActionListener.enablePanel(panel, res.getValue());
        stmt.getThenStatement().accept(this);
    }
    @Override
    public void visit(IfThenElse stmt) {
    }
    @Override
    public void visit(AnswerableQuestion stmt) {
        parentPanel.add(new GUIQuestion(stmt));
        parentPanel.revalidate();
    }
    @Override
    public void visit(ComputedQuestion stmt) {
        parentPanel.add(new GUIQuestion(stmt));
        parentPanel.revalidate();
    }
    
    private JPanel addContainerPanel() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(
                new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
        return containerPanel;
    }
}
