package org.uva.sea.languages.qls.interpreter;

import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.qls.interpreter.evaluate.ApplyQLSStyle;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.CheckAllQuestionsInQLQLS;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.CheckNoDuplicateQuestions;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.IQLSStaticAnalysis;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evaluator {

    private ASTGenerator astGenerator = new ASTGenerator();

    private ApplyQLSStyle.Linker qlQlsLinker = new ApplyQLSStyle.Linker();

    private List<IQLSStaticAnalysis> staticAnalyses = Arrays.asList(new IQLSStaticAnalysis[]{
            new CheckAllQuestionsInQLQLS.Checker(),
            new CheckNoDuplicateQuestions.Checker()
    });

    /**
     * Generates questions with values
     *
     * @param qlsFile            Specification of the GUI
     * @param qlEvaluationResult The current state of the program
     * @return List of questions that should be displayed
     */
    public EvaluationResult evaluate(String qlsFile, EvaluationResult qlEvaluationResult) throws IOException, InterruptedException {
        Messages evaluationMessages = new Messages();

        ParseResult<Stylesheet> parseResult = this.parse(qlsFile);
        evaluationMessages.addMessageList(parseResult.getMessages());
        if (evaluationMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), parseResult.getMessages(), qlEvaluationResult.getAst());
        }

        evaluationMessages.addMessageList(performStaticAnalysis(qlEvaluationResult.getAst(), parseResult.getAST()));
        if (evaluationMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), evaluationMessages, qlEvaluationResult.getAst());
        }

        return qlQlsLinker.apply(qlEvaluationResult, parseResult.getAST());
    }

    /**
     * Does the static analysis on the parse result
     *
     */
    private Messages performStaticAnalysis(Form form, Stylesheet stylesheet) {
        Messages returnMessage = new Messages();
        for (IQLSStaticAnalysis staticAnalysis : this.staticAnalyses) {
            Messages analysisMessages = staticAnalysis.doCheck(form, stylesheet);
            returnMessage.addMessageList(analysisMessages);
        }
        return returnMessage;
    }


    /**
     * Generate the AST
     *
     * @param guiSpecification Specification of the GUI
     */
    private ParseResult<Stylesheet> parse(String guiSpecification) throws IOException {
        return astGenerator.createAST(CharStreams.fromStream(new FileInputStream(guiSpecification)));
    }
}
