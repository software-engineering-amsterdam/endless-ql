package org.uva.sea.languages;

import org.uva.sea.languages.ql.interpreter.dataObject.ASTResult;
import org.uva.sea.languages.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.languages.qls.interpreter.ApplyQLSStyle;
import org.uva.sea.languages.qls.interpreter.ASTGenerator;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.io.IOException;

public class QlsInterpreter extends QlQuestionEvaluator {

    private ApplyQLSStyle.Linker qlQlsLinker = new ApplyQLSStyle.Linker();

    private ASTGenerator qlsInterpreter = new ASTGenerator();

    private String qlsFileLocation;

    /**
     * Constructor
     * @param qlFileLocation
     * @param qlsFileLocation
     */
    public QlsInterpreter(String qlFileLocation, String qlsFileLocation) {
        super(qlFileLocation);
        this.qlsFileLocation = qlsFileLocation;
    }

    /**
     * Generate InterpreterResult
     * @param ql The ql location
     * @param qls The qls location
     * @param symbolTable Symbol table for QL
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    @Override
    public InterpreterResult getQuestions() throws StaticAnalysisError, IOException {
        InterpreterResult qlInterpreterResult = super.getQuestions();
        ASTResult<Stylesheet> astResult = qlsInterpreter.interpreterScriptFile(this.qlsFileLocation);

        return qlQlsLinker.apply(qlInterpreterResult, astResult.getAST());
    }

    /**
     * Generate InterpreterResult
     * @return InterpreterResult
     * @throws StaticAnalysisError
     * @throws InterruptedException
     */
    public InterpreterResult getQuestions() throws StaticAnalysisError, IOException {
        return qlInterpreter.generate(this.qlFileLocation, this.symbolTable);
    }
}
