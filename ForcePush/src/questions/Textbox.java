package questions;

public class Textbox extends Question{

private String answer;
	
	public Textbox(String question, String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answer = "";
	}

	public void givenAnswer(String answer) 
	{
		this.answer = answer;
	}

	public String answerValue()
	{
		return answer;
	}

}
