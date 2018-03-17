package qlviz.typecheker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import qlviz.model.Form;
import qlviz.model.question.Question;

public class DuplicateLabelChecker implements  Analyzer {
	private Form form;


    public void initialize(Form form) {
		this.form = form;
	}

	@Override
	public List<AnalysisResult> analyze() {
		List<Question> questionLabels = QuestionCollector.collect(this.form);
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
