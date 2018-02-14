/**
 * 
 */
package qlviz.interpreter;

import qlviz.QLBaseListener;
import qlviz.QLParser;
import qlviz.model.Form;
import qlviz.model.Question;
import qlviz.model.QuestionType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author deepa
 *
 */
public class FormListener extends QLBaseListener {

	private List<Question> questions = new LinkedList<Question>();

	@Override
	public void enterQuestion(QLParser.QuestionContext ctx) {
		super.enterQuestion(ctx);

		this.questions.add(new Question("", "", QuestionType.Integer));
	}

	public List<Question> getQuestions() {
		return questions;
	}
}
