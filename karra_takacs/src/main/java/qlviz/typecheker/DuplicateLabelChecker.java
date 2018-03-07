package qlviz.typecheker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import qlviz.model.Form;
import qlviz.model.question.Question;

public class DuplicateLabelChecker implements  Analyzer {
	Form form;
	List<String> questionNames;
	List<String> questionLabels;
	List<AnalysisResult> errors;


	public void initialize(Form form) {
		this.form = form;
		this.questionNames = new ArrayList<>();
		this.errors = new ArrayList<>();
	}

	@Override
	public List<AnalysisResult> analyze() {
		List<Question> questions = QuestionCollector.collect(this.form);
		List<Question> questionLabels = QuestionCollector.collect(this.form);
        HashMap<String, List<Question>> questionsByName = new HashMap<>();
        HashMap<String, List<Question>> questionsByLabel = new HashMap<>();
        
        for (Question question : questionLabels) {
            if (!questionsByLabel.containsKey(question.getName())) {
                List<Question> firstQuestionLabel = new ArrayList<>();
                firstQuestionLabel.add(question);
                questionsByLabel.put(question.getName(), firstQuestionLabel);
            }
            else {
            	questionsByLabel.get(question.getName()).add(question);
            }
        }
        List<AnalysisResult> analysisResults = new ArrayList<>();
       
        questionsByLabel.forEach((s, questionsOfSameLabel) -> {
            if (questionsOfSameLabel.size() > 1) {
                analysisResults.add(new DuplicateLabelResult(questionsOfSameLabel));
            }
        });
		
        return analysisResults;
	}



}
