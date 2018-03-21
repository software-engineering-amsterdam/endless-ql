package org.uva.jomi.ql.ast.analysis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.WarningHandler;

public class DuplicatedLabelChecker extends WarningHandler implements Statement.Visitor<Void> {

	private final HashMap<String, List<String>> labels;
	
	public DuplicatedLabelChecker() {
		this(false);
	}

	public DuplicatedLabelChecker(boolean printWarnings) {
		super("DuplicatedLabelChecker", printWarnings);
		this.labels = new HashMap<>();
	}

	public void check(List<Statement> statements) {
		for (Statement statement : statements) {
			statement.accept(this);
		}
	}

	// This method was added for testing purposes.
	public String getErrorAtIndex(int index) {
		return this.getReport(index);
	}

	private void checkLabel(QuestionStatement stmt) {
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
	public Void visit(FormStatement stmt) {
		stmt.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement stmt) {
		// Visit every statement in the block.
		for (Statement statement : stmt.getStatements()) {
			statement.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(QuestionStatement stmt) {
		checkLabel(stmt);
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement stmt) {
		checkLabel(stmt);
		return null;
	}

	@Override
	public Void visit(IfStatement stmt) {
		stmt.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement stmt) {
		stmt.visitIfBlockStatement(this);
		stmt.visitElseBlockStatement(this);
		return null;
	}
}
