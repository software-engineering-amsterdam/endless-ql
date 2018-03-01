package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.uva.sea.ql.parser.antlr.ErrorHandler;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.elements.Form;

public class QLCompiler {

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return The AST node that can be used by the interpreter
     */
    public Form compileScriptFile(CharStream source) throws Errors {

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

        QLTypeCheck qlTypeCheck = new QLTypeCheck();
        Errors TypeCheckErrors = qlTypeCheck.doTypeCheck(form.result);
        if(TypeCheckErrors.errorsPresent()) {
            throw TypeCheckErrors;
        }

        QLVariableInfo varChecker = new QLVariableInfo();
        Errors varInfoErrors = varChecker.addVariableInformation(form.result);
        if(varInfoErrors.errorsPresent()) {
            throw varInfoErrors;
        }

        return form.result;
    }
}



