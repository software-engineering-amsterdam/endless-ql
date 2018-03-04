package qlviz.typecheker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import qlviz.model.Form;
import qlviz.model.question.Question;

public class DuplicateQuestionChecker implements  Analyzer {
	Form form;
	List<String> questionNames;
	List<AnalysisResult> errors;


	public void initialize(Form form) {
		this.form = form;
		this.questionNames = new ArrayList<>();
		this.errors = new ArrayList<>();
	}

	@Override
	public List<AnalysisResult> analyze() {
		List<Question> questions = QuestionCollector.collect(this.form);
        HashMap<String, List<Question>> questionsByName = new HashMap<>();
        for (Question question : questions) {
            if (!questionsByName.containsKey(question.getName())) {
                List<Question> firstQuestion = new ArrayList<>();
                firstQuestion.add(question);
                questionsByName.put(question.getName(), firstQuestion);
            }
            else {
                questionsByName.get(question.getName()).add(question);
            }
        }
        List<AnalysisResult> analysisResults = new ArrayList<>();
        questionsByName.forEach((s, questionsOfSameName) -> {
            if (questionsOfSameName.size() > 1) {
                analysisResults.add(new DuplicateQuestionResult(questionsOfSameName));
            }
        });
        return analysisResults;
	}



}
