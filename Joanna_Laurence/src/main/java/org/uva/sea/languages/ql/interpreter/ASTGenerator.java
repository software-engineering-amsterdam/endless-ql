package org.uva.sea.languages.ql.interpreter;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.languages.astGenerator.BaseASTGenerator;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.languages.ql.interpreter.dataObject.ASTResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.parser.antlr.ErrorHandler;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.*;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import org.uva.sea.languages.ql.antlr.QLLexer;
import org.uva.sea.languages.ql.antlr.QLParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ASTGenerator extends BaseASTGenerator<Form> {

    public ASTGenerator() {
        super(Arrays.asList(new IStaticAnalysis[]{
                new LinkAndCheckVariableUsage.Checker(),
                new TypeCheck.Checker(),
                new CheckDuplicateLabels.Checker(),
                new CheckIncorrectDuplicateQuestions.Checker(),
                new CircularQuestionDependencies.Checker(),
                new CircularExpressionDependencies.Checker()
        }));
    }

    /**
     * Create AST for specification
     *
     * @param source
     * @return
     */
    protected Form createAST(CharStream source) {
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLParser.FormContext form = parser.form();

        return parseErrorListener.isError() ? null : form.result;
    }
}



