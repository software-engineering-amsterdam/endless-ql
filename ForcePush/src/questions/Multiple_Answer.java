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
