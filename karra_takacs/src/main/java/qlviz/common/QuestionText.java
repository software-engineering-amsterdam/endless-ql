package qlviz.common;

public enum QuestionText {
	BUY("buy"),
	SELL("sell"),
	LOAN("loan");
	
	private String question;

	private QuestionText(String question) {
		this.question = question;
	}
	

}
