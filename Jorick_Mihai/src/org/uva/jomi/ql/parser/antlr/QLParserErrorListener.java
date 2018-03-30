package org.uva.jomi.ql.parser.antlr;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class QLParserErrorListener extends BaseErrorListener {
	private final List<String> errors;

	public QLParserErrorListener() {
		this.errors = new ArrayList<>();
	}
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
		String msg, RecognitionException e) {
		String error = String.format("[ParserError] %d:%d %s", line, charPositionInLine, msg);
		errors.add(error);
		System.err.println(error);
	}

	public List<String> getErrors() {
		return this.errors;
	}
}
