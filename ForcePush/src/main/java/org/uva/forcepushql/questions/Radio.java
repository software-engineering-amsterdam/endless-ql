<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/questions/Radio.java
package org.uva.forcepushql.questions;


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
=======
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
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/questions/Radio.java
