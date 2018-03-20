package ql.gui.alternative;

import javax.swing.JPanel;

import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Bool;
import ql.visitors.interfaces.StatementVisitor;

public class ASTtoGUI implements StatementVisitor {
    
    private JPanel parentPanel;

    public ASTtoGUI() {
    		parentPanel = GUI.getPanel();
	}
    
    public ASTtoGUI(JPanel panel) {
        this.parentPanel	= panel;
    }
    
    @Override
    public void visit(Block block) {
    	
	    	JPanel stmtPanel			= GUI.getStatementPanel(block);
	    	ASTtoGUI blockVisitor	= new ASTtoGUI(stmtPanel);
	    	
    		for(Statement stmt: block.getStatements()) stmt.accept(blockVisitor);
            
    		if(stmtPanel.getParent() == null) parentPanel.add(stmtPanel);
    		
    		parentPanel.revalidate();
    }
    
    @Override
    public void visit(IfThen stmt) {
    	
    		Literal<?> value		= stmt.getCondition().evaluate();
    		BoolLiteral result	= value.isBoolean()? Bool.parseBool(value) : Bool.parseBool(new UndefinedLiteral());
    		JPanel stmtPanel		= GUI.getStatementPanel(stmt);
    		JPanel thenPanel		= GUI.getStatementPanel(stmt.getThenStatement());
    		
    		thenPanel.removeAll();
    		if(thenPanel.getParent() == null) stmtPanel.add(thenPanel);
    		
    		if(result.getValue())
    		{
    			stmt.getThenStatement().accept(new ASTtoGUI(thenPanel));
    		}
            
    		if(stmtPanel.getParent() == null) parentPanel.add(stmtPanel);
            
        parentPanel.revalidate();
    }

    @Override
    public void visit(IfThenElse stmt) {
        
		Literal<?> value		= stmt.getCondition().evaluate();
		BoolLiteral result	= value.isBoolean()? Bool.parseBool(value) : Bool.parseBool(new UndefinedLiteral());
		JPanel stmtPanel		= GUI.getStatementPanel(stmt);
		JPanel thenPanel		= GUI.getStatementPanel(stmt.getThenStatement());
		JPanel elsePanel		= GUI.getStatementPanel(stmt.getElseStatement());
		
		thenPanel.removeAll();
		if(thenPanel.getParent() == null) stmtPanel.add(thenPanel);
		
		elsePanel.removeAll();
		if(elsePanel.getParent() == null) stmtPanel.add(elsePanel);
		
		if(result.getValue())
		{
			stmt.getThenStatement().accept(new ASTtoGUI(thenPanel));
		}
		else
		{
			stmt.getElseStatement().accept(new ASTtoGUI(elsePanel));
		}
        
		if(stmtPanel.getParent() == null) parentPanel.add(stmtPanel);
		
        parentPanel.revalidate();
    }
    
    @Override
    public void visit(AnswerableQuestion question) {
        
    		QuestionPanel questionPanel	= new QuestionPanel(question);
    		JPanel stmtPanel				= GUI.getStatementPanel(question);
    		
    		stmtPanel.removeAll();
    		stmtPanel.add(questionPanel);
    		
    		if(stmtPanel.getParent() == null) parentPanel.add(stmtPanel);
    		
    		parentPanel.revalidate();
    		
    		System.out.println(question.getIdentifier());
    		System.out.println();
    		new AnswerableQuestionController(question, questionPanel.getWidget());
    }
    
    @Override
    public void visit(ComputedQuestion question) {
        
    		Literal<?> result = question.getComputation().evaluate();
    		JPanel stmtPanel  = GUI.getStatementPanel(question);
    		
    		stmtPanel.removeAll();
    		question.getIdentifier().setValue(result);
    		
    		System.out.println(String.format("CQ119: %s --> %s", question.getIdentifier(), result));
    		if(!result.isUndefined()) stmtPanel.add(new QuestionPanel(question));
    		
    		if(stmtPanel.getParent() == null) parentPanel.add(stmtPanel);
    		
    		System.out.println(question.getIdentifier());
    		System.out.println();
    		parentPanel.revalidate();
    }
}
