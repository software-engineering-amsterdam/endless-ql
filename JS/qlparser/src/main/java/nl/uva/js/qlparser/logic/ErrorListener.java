package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.exceptions.ParseException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;

import java.util.BitSet;

class ErrorListener extends DefaultErrorStrategy implements ANTLRErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new ParseException(msg);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        throw new ParseException(text);
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        throw new ParseException(text);
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        throw new ParseException(text);
    }

    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        throw new ParseException(e.getMessage());
    }

    @Override
    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        throw new ParseException(e.getMessage());
    }

    @Override
    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        throw new ParseException(e.getMessage());
    }

    @Override
    protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
        throw new ParseException(e.getMessage());
    }

    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        Token t = recognizer.getCurrentToken();
        String tokenName = getTokenErrorDisplay(t);
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "extraneous input "+tokenName+" expecting "+
                     expecting.toString(recognizer.getVocabulary());
        throw new ParseException(msg);
    }

    @Override
    protected void reportMissingToken(Parser recognizer) {
        Token t = recognizer.getCurrentToken();
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "missing "+expecting.toString(recognizer.getVocabulary())+
                     " at "+getTokenErrorDisplay(t);
        throw new ParseException(msg);
    }
}