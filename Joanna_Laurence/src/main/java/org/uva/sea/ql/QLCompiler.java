package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.exceptions.StaticAnalysisError;
import org.uva.sea.ql.parser.antlr.ErrorHandler;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.staticAnalysis.Messages;
import org.uva.sea.ql.staticAnalysis.QLTypeCheck;
import org.uva.sea.ql.staticAnalysis.QLVariableInfo;

public class QLCompiler {

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return The AST node that can be used by the interpreter
     */
    public Form compileScriptFile(CharStream source) throws StaticAnalysisError {

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
        Messages varInfoErrors = varChecker.addVariableInformation(form.result);
        if(varInfoErrors.errorsPresent()) {
            throw new StaticAnalysisError(varInfoErrors);
        }

        QLTypeCheck qlTypeCheck = new QLTypeCheck();
        Messages TypeCheckErrors = qlTypeCheck.doTypeCheck(form.result);
        if(TypeCheckErrors.errorsPresent()) {
            throw new StaticAnalysisError(TypeCheckErrors);
        }

        return form.result;
    }
}



