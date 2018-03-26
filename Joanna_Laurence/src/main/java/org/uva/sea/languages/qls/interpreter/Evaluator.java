package org.uva.sea.languages.qls.interpreter;

import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.qls.interpreter.evaluate.ApplyQLSStyle.Linker;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.CheckAllQuestionsInQLQLS;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.CheckNoDuplicateQuestions;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.IQLSStaticAnalysis;
import org.uva.sea.languages.qls.interpreter.staticAnalysis.TypeCheck.Checker;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evaluator {

    private final ASTGenerator astGenerator = new ASTGenerator();

    private final Linker qlQlsLinker = new Linker();

    private final List<IQLSStaticAnalysis> staticAnalyses = Arrays.asList(new CheckAllQuestionsInQLQLS.Checker(),
            new CheckNoDuplicateQuestions.Checker(),
            new Checker());


    public EvaluationResult evaluate(String qlsFile, EvaluationResult qlEvaluationResult) throws IOException {
        ParseResult<Stylesheet> parseResult = this.parse(qlsFile);

        Messages parseMessages = parseResult.getMessages();
        if (parseMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), parseMessages, qlEvaluationResult.getAst());
        }

        Messages staticAnalysisMessages = this.performStaticAnalysis(qlEvaluationResult.getAst(), parseResult.getAst());
        if (staticAnalysisMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), staticAnalysisMessages, qlEvaluationResult.getAst());
        }

        return this.qlQlsLinker.apply(qlEvaluationResult, parseResult.getAst());
    }


    private Messages performStaticAnalysis(Form form, Stylesheet stylesheet) {
        Messages returnMessage = new Messages();
        for (IQLSStaticAnalysis staticAnalysis : this.staticAnalyses) {
            Messages analysisMessages = staticAnalysis.doCheck(form, stylesheet);
            returnMessage.addMessages(analysisMessages);
        }
        return returnMessage;
    }

    private ParseResult<Stylesheet> parse(String guiSpecification) throws IOException {
        return this.astGenerator.createAST(CharStreams.fromStream(new FileInputStream(guiSpecification)));
    }
}
