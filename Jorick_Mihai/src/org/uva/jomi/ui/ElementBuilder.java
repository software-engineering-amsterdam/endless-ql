package org.uva.jomi.ui;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ui.controllers.ComputedQuestionController;
import org.uva.jomi.ui.controllers.Controller;
import org.uva.jomi.ui.controllers.QuestionController;
import org.uva.jomi.ui.interpreter.QuestionTraverser;
import org.uva.jomi.ui.models.Question;
import org.uva.jomi.ui.views.core.Panel;

public class ElementBuilder {
		
	public List<Controller> generateQuestions(List<Statement> statements) {
		
		QuestionTraverser traverser = new QuestionTraverser();
		
		List<Controller> elements = new ArrayList<Controller>();
		for(Question question: traverser.traverse(statements)) {
			if(question.getExpression() != null) {
				elements.add(new ComputedQuestionController(question));
			} else {
				elements.add(new QuestionController(question));	
			}
		}
		return elements;		
	}

	public List<Panel> build(List<Statement> statements) {
		List<Panel> elements = new ArrayList<Panel>();
		
		for (Controller controller : this.generateQuestions(statements)) {
			elements.add(controller.build());
		}

		return elements;
	}
}
