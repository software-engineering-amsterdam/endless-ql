package org.uva.jomi.ui.storage;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.uva.jomi.ui.models.Question;

public class TextStorage extends Storage {
	
	@Override
	public void store(List<Question> questions) {
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("answers.txt"), "utf-8"));
		    	
		    for(Question question: questions) {
		    		writer.write(question.getQuestion() + ": " + question.getValue().getValue() + "\n");
			}  

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		
	}

}
