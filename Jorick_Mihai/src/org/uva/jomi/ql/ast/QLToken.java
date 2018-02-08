package org.uva.jomi.ql.ast;

import org.antlr.v4.runtime.Token;

class QLToken {
	private String lexeme;
	private int line;
	private int column;
	
	public QLToken(Token token) {
		this.lexeme = token.getText();
		this.line = token.getLine();
		this.column = token.getCharPositionInLine();
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}