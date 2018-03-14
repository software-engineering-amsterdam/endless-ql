package org.uva.jomi.ql.ast.analysis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.WarningHandler;

public class DuplicatedLabelChecker extends WarningHandler implements Stmt.Visitor<Void> {

	private final HashMap<String, List<String>> labels;
	
	

	public DuplicatedLabelChecker(boolean printWarnings) {
		super("DuplicatedLabelChecker", printWarnings);
		this.labels = new HashMap<>();
	}
	
	public void check(List<Stmt> statements) {
		for (Stmt statement : statements) {
			statement.accept(this);
		}
	}
	
	public int getNumberOfWarnings() {
		return this.getNumberOfReports();
	}
	
	// This method was added for testing purposes.
	public String getErrorAtIndex(int index) {
		return this.getReport(index);
	}
	
	private void checkLabel(QuestionStmt stmt) {
			if (labels.containsKey(stmt.getLabel())) {
				// Get the list of question names
				List<String> questionNames = this.labels.get(stmt.getLabel());
	    	
				// If the list is empty add the question name to the list.
				if (questionNames.size() == 0) {
					questionNames.add(stmt.getName());
				} else {
					// Store out a warning.
					this.addWarning(stmt, questionNames);
					questionNames.add(stmt.getName());
				}
		    } else {
		    	// Create a new list
		    	List<String> questionNames = new ArrayList<>();
		    	// Add the question name to the list
		    	questionNames.add(stmt.getName());
		    	// Update the map
		    	this.labels.put(stmt.getLabel(), questionNames);
		    }	
	}

	@Override
	public Void visit(FormStmt stmt) {
		stmt.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt stmt) {
		// Visit every statement in the block.
		for (Stmt statement : stmt.getStatements()) {
			statement.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		checkLabel(stmt);
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		checkLabel(stmt);
		return null;
	}

	@Override
	public Void visit(IfStmt stmt) {
		stmt.visitIfBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		stmt.visitIfBlockStmt(this);
		stmt.visitElseBlockStmt(this);
		return null;
	}
}
