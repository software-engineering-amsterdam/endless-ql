package org.uva.sea.languages.ql.parser.antlr;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.util.BitSet;

public class ErrorHandler implements ANTLRErrorListener {

    private final Messages messages = new Messages();

    public final void syntaxError(final Recognizer<?, ?> recognizer, final Object o, final int i, final int i1, final String s, final RecognitionException e) {
        this.messages.addMessage(s, MessageTypes.ERROR);
    }

    public void reportAmbiguity(final Parser parser, final DFA dfa, final int i, final int i1, final boolean b, final BitSet bitSet, final ATNConfigSet atnConfigSet) {

    }

    public void reportAttemptingFullContext(final Parser parser, final DFA dfa, final int i, final int i1, final BitSet bitSet, final ATNConfigSet atnConfigSet) {

    }

    public void reportContextSensitivity(final Parser parser, final DFA dfa, final int i, final int i1, final int i2, final ATNConfigSet atnConfigSet) {

    }

    public final Messages getMessages() {
        return this.messages;
    }
}
