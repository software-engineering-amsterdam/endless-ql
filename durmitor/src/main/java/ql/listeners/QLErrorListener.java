package ql.listeners;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import ql.exceptions.ANTLRError;
import ql.exceptions.QLException;

public class QLErrorListener extends BaseErrorListener {

    private List<QLException> errors;

    public QLErrorListener() {
        this.errors = new ArrayList<QLException>();
    }
    
    public QLErrorListener(List<QLException> errors) {
        this.errors = errors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add(new ANTLRError(msg, line, charPositionInLine));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        for (QLException e : errors) System.out.println(e);
    }
}
