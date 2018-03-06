package nl.uva.se.sc.niro.parser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Interval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ql.QLParser;

import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ErrorListener implements ANTLRErrorListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private List<ParseErrorInfo> parseErrors = new LinkedList<>();

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {

		List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		parseErrors.add(new SyntaxErrorInfo(line, charPositionInLine, offendingSymbol.toString(), msg, stack, e));
	}

	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        QLParser parser = (QLParser) recognizer;
        Interval interval = Interval.of(startIndex, stopIndex);
        LOGGER.info("Ambiguous construct around ["+parser.getTokenStream().getText(interval) + "].");
	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        QLParser parser = (QLParser) recognizer;
        Interval interval = Interval.of(startIndex, stopIndex);
        LOGGER.info("Need full context on ["+parser.getTokenStream().getText(interval) + "] to make decision.");
	}

	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        QLParser parser = (QLParser) recognizer;
        Interval interval = Interval.of(startIndex, stopIndex);
        LOGGER.info("Context sensitive construct while dealing with ["+parser.getTokenStream().getText(interval) + "].");
	}

	public List<ParseErrorInfo> getParseErrors() {
		return parseErrors;
	}
}
