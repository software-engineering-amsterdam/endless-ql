package qlviz.typecheker;

import java.util.ArrayList;
import java.util.List;

import qlviz.model.Form;
import qlviz.model.question.BooleanQuestion;
import qlviz.model.question.DateQuestion;
import qlviz.model.question.DecimalQuestion;
import qlviz.model.question.IntegerQuestion;
import qlviz.model.question.MoneyQuestion;
import qlviz.model.question.QuestionVisitor;
import qlviz.model.question.StringQuestion;

public class QuestionChecker implements QuestionVisitor {
	private static final String DUPLICATE_QUESTIONS = "Duplicate Questions";
	Form form;
	List<String> questionNames;
	List<String> errors;
	
	public QuestionChecker(Form form) {
		this.form = form;
		questionNames = new ArrayList<>();
	}

	@Override
	public Object visit(BooleanQuestion booleanQuestion) {
		return isDuplicate(booleanQuestion.getName());
	}

	@Override
	public Object visit(DateQuestion dateQuestion) {
		return isDuplicate(dateQuestion.getName());
	}

	@Override
	public Object visit(DecimalQuestion decimalQuestion) {
		return isDuplicate(decimalQuestion.getName());
	}

	@Override
	public Object visit(IntegerQuestion integerQuestion) {
		return isDuplicate(integerQuestion.getName());
	}

	@Override
	public Object visit(MoneyQuestion moneyQuestion) {
		return isDuplicate(moneyQuestion.getName());
	}

	@Override
	public Object visit(StringQuestion stringQuestion) {
		return isDuplicate(stringQuestion.getName());
	}

	private List<String> isDuplicate(String questionName) {
		if(!questionNames.contains(questionName)) {
			questionNames.add(questionName);
	}
		else {
			errors.add(DUPLICATE_QUESTIONS);
		}
		return errors;
		
	}

	

}
