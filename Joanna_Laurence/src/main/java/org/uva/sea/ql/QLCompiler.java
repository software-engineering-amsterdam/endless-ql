package org.uva.sea.ql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.uva.sea.ql.parser.antlr.ErrorHandler;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;;
import org.uva.sea.ql.parser.elements.Form;

import java.util.BitSet;

public class QLCompiler {

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return The AST node that can be used by the interpreter
     */
    public Form compileScriptFile(CharStream source) {

        //Get tokens
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tree
        QLParser parser = new QLParser(tokens);

        //Check the parsing result
        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLParser.FormContext form = parser.form();
        if(parseErrorListener.isError() || form.result == null)
            return null;

        QLVariableInfo varChecker = new QLVariableInfo();
        if(!varChecker.addVariableInformation(form.result)) {
            return null;
        }

        //Do the type check
        QLTypeCheck typeChecker = new QLTypeCheck();
        if(!typeChecker.doTypeCheck(form.result)) {
            return null;
        }

        //Show the parse tree
        //Trees.inspect(form, parser);

        return form.result;
    }
}



