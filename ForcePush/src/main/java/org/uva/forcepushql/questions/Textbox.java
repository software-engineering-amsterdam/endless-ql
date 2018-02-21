<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/questions/Textbox.java
package org.uva.forcepushql.questions;

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
=======
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
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/questions/Textbox.java
