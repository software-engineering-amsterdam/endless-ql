package qlviz.Common;

public enum QuestionText {
	BUY("buy"),
	SELL("sell"),
	LOAN("loan");
	
	private String question;

	private QuestionText(String question) {
		this.question = question;
	}
	

}
