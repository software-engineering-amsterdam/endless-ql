package questions;


public class Radio extends Question{
	
private boolean answer;	

	public Radio(String question, String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answer = false;
	}

	public void givenAnswer(boolean answer) 
	{
		this.answer = answer;
	}

	public boolean answerValue()
	{
		return answer;
	}
	
}
