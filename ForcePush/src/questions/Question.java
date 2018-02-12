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
	mandatory = true;
	
}
	
}
