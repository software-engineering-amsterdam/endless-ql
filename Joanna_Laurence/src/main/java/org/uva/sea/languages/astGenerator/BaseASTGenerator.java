package org.uva.sea.languages.astGenerator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.languages.ql.interpreter.dataObject.ASTResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.IStaticAnalysis;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public abstract class BaseASTGenerator<T> {

    private List<IStaticAnalysis<T>> staticAnalyses;

    public BaseASTGenerator(List<IStaticAnalysis<T>> staticAnalyses) {
        this.staticAnalyses = staticAnalyses;
    }

    /**
     * Create AST for specification
     *
     * @param source
     * @return
     */
    protected abstract T createAST(CharStream source);

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return The AST node that can be used by the getQuestions
     */
    public ASTResult interpreterScriptFile(String source) throws StaticAnalysisError, IOException {
        T AST = createAST(this.toCharStream(source));
        if (AST == null)
            return new ASTResult<T>(null, new Messages(MessageTypes.UNKNOWN));

        Messages messages = executeStaticAnalysis(AST);
        return new ASTResult(AST, messages);
    }


    /**
     * Convert file name to resource
     *
     * @param fileName The location of the file
     * @return CharStream
     * @throws IOException
     */
    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(new FileInputStream(fileName));
    }
}



