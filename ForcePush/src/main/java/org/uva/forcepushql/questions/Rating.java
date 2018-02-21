<<<<<<< HEAD:ForcePush/src/main/java/org/uva/forcepushql/questions/Rating.java
package org.uva.forcepushql.questions;

public class Rating extends Question{

private int answer;
	
	public Rating(String question,String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answer = 0;
	}

	public void givenAnswer(int answer) 
	{
		this.answer = answer;
	}

	public int answerValue()
	{
		return answer;
	}

}
=======
package questions;

public class Rating extends Question{

private int answer;
	
	public Rating(String question,String answerType, String answerName) 
	{
		super(question, answerType, answerName);
		answer = 0;
	}

	public void givenAnswer(int answer) 
	{
		this.answer = answer;
	}

	public int answerValue()
	{
		return answer;
	}

}
>>>>>>> 4e9549a66df631f508566cf71b749537c31c0ae7:ForcePush/src/questions/Rating.java
