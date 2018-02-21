package org.uva.forcepushql.questions;

public abstract class Question {

private boolean mandatory;
private String question;
private String answerType;
private String answerName;

	public Question(String question, String answerType, String answerName)
	{
		mandatory = false;
		this.question = question;
		this.answerType = answerType;
		this.answerName = answerName;
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
	
	
	public String writtenQuestion()
	{
		return question;
	}
	
	
	public String answerTypeValue()
	{
		return answerType;
	}
	
	public String answerNameValue()
	{
		return answerName;
	}
	
	
}
