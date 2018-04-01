package org.uva.jomi.ui.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.uva.jomi.ui.interpreter.SymbolTable;
import org.uva.jomi.ui.models.Question;

public class JSONStorage extends Storage {

	@Override
	public void store(List<Question> questions) {
		JSONArray jsonQuestions = new JSONArray();
		
		for(Question question: questions) {
			JSONObject jsonQuestion = new JSONObject();
			jsonQuestion.put("question", question.getQuestion());
			jsonQuestion.put("answer", question.getValue().getValue());
			
			jsonQuestions.add(jsonQuestion);
		}
 
		try (FileWriter file = new FileWriter("answers.json")) {
			file.write(jsonQuestions.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
