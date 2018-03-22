package qlviz.typecheker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import qlviz.model.ConditionalBlock;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.question.Question;

public class DuplicateChecker {
	List<String> questionList;
	List<String> errors;
	Form form;

	public DuplicateChecker(Form form) {
		this.form = form;
		questionList = new ArrayList<>();
		errors = new ArrayList<>();
	}

	public boolean containsUniqueQuestions() {
		List<String> allQuestions = new ArrayList<>();
		allQuestions = getallQuestion();
		HashSet<String> uniqueQuestions = new HashSet<>(allQuestions); 
		return (uniqueQuestions.size()==allQuestions.size());

	}

	private List<String> getallQuestion() {
		for (QuestionBlock questionBlocks : form.getQuestions()) {
			getQuestions(questionBlocks);

			for (ConditionalBlock conditionalBlock : questionBlocks.getBlocks()) {
				for (QuestionBlock blocks : conditionalBlock.getQuestionBlocks()) {
					getQuestions(blocks);
				}
			}

		}
		return questionList;
	}

	private void getQuestions(QuestionBlock questionBlocks) {
		for (Question question : questionBlocks.getQuestions()) {
			questionList.add(question.getText());
		}
	}
}
