package questions;

public abstract class Question {

private boolean mandatory;
private String question;
private String answer;

	public Question(String question)
	{
		mandatory = false;
		this.question = question;
		answer = "";
	}

	public void mandatory()
	{
		if(!mandatory)
			mandatory = true;
		
		else
			mandatory = false;
	
	}

	public boolean isMandatory()
	{
		return mandatory;
	}
	
	public void editQuestion(String newQuestion)
	{
		question = newQuestion;
	}
	
	public String writtenQuestion()
	{
		return question;
	}
	
	public void givenAnswer(String newAnswer)
	{
		answer = newAnswer;
	}
	
	public String answerValue()
	{
		return answer;
	}
}
