<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/questions/Multiple_Answer.java
package org.uva.forcepushql.questions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Multiple_Answer extends Question{

private Map<String,Boolean> answers;	
	
	public Multiple_Answer(String question, String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answers = new HashMap<String,Boolean>();
	}

	public void addAnswerOption(String option)
	{
		answers.put(option,false);
	}

	public void putAnswers(String option, boolean answer)
	{
		answers.remove(option);
		answers.put(option, answer);
	}
	
	public Set<Map.Entry<String,Boolean>> answersValues()
	{
		return answers.entrySet();
	}
	
}
=======
package questions;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Multiple_Answer extends Question{

private Map<String,Boolean> answers;	
	
	public Multiple_Answer(String question, String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answers = new HashMap<String,Boolean>();
	}

	public void addAnswerOption(String option)
	{
		answers.put(option,false);
	}

	public void putAnswers(String option, boolean answer)
	{
		answers.remove(option);
		answers.put(option, answer);
	}
	
	public Set<Map.Entry<String,Boolean>> answersValues()
	{
		return answers.entrySet();
	}
	
}
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/questions/Multiple_Answer.java
