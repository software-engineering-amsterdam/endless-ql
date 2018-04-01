package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.WarningHandler;

public class DuplicatedLabelChecker implements Statement.Visitor<Void> {

	private final HashMap<String, List<String>> labels;
	private final WarningHandler warnings;

	public DuplicatedLabelChecker() {
		this(false);
	}

	public DuplicatedLabelChecker(boolean printWarnings) {
		this.warnings = new WarningHandler(this.getClass().getSimpleName(), printWarnings);
		this.labels = new HashMap<>();
	}

	public void check(List<Statement> statements) {
		for (Statement statement : statements) {
			statement.accept(this);
		}
	}

	// This method was added for testing purposes.
	public String getErrorAtIndex(int index) {
		return this.warnings.getReport(index);
	}

	public int getNumberOfWarnings() {
		return this.warnings.getNumberOfWarnings();
	}

	public List<String> getWarnings() {
		return this.warnings.getReports();
	}

	private void checkLabel(QuestionStatement statement) {
			if (labels.containsKey(statement.getLabel())) {
				// Get the list of question names
				List<String> questionNames = this.labels.get(statement.getLabel());

				// If the list is empty add the question name to the list.
				if (questionNames.size() == 0) {
					questionNames.add(statement.getName());
				} else {
					// Store out a warning.
					this.warnings.addDuplicatedLabelWarning(statement, questionNames);
					questionNames.add(statement.getName());
				}
		    } else {
		    	// Create a new list
		    	List<String> questionNames = new ArrayList<>();
		    	// Add the question name to the list
		    	questionNames.add(statement.getName());
		    	// Update the map
		    	this.labels.put(statement.getLabel(), questionNames);
		    }
	}

	@Override
	public Void visit(FormStatement statement) {
		statement.visitBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement statement) {
		// Visit every statement in the block.
		for (Statement blockStatement : statement.getStatements()) {
			blockStatement.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(QuestionStatement statement) {
		checkLabel(statement);
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement statement) {
		checkLabel(statement);
		return null;
	}

	@Override
	public Void visit(IfStatement statement) {
		statement.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement statement) {
		statement.visitIfBlockStatement(this);
		statement.visitElseBlockStatement(this);
		return null;
	}
}
