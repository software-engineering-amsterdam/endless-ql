package org.uva.sea.languages.ql.interpreter;

import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.ExpressionEvaluator;
import org.uva.sea.languages.ql.interpreter.evaluate.FormEvaluator;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.*;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.CircularExpressionDependencies.Checker;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evaluator {

    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    private final ASTGenerator astGenerator = new ASTGenerator();

    private final List<IQLStaticAnalysis> staticAnalyses = Arrays.asList(
            new LinkAndCheckVariableUsage.Checker(),
            new TypeCheck.Checker(),
            new CheckDuplicateLabels.Checker(),
            new CheckIncorrectDuplicateQuestions.Checker(),
            new CircularQuestionDependencies.Checker(),
            new Checker());

    public final EvaluationResult evaluate(final String qlFile, final SymbolTable symbolTable) throws IOException {

        final Messages evaluationMessages = new Messages();

        final ParseResult<Form> parseResult = this.parse(qlFile);
        evaluationMessages.addMessages(parseResult.getMessages());
        if (evaluationMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), parseResult.getMessages(), parseResult.getAst());
        }

        evaluationMessages.addMessages(this.performStaticAnalysis(parseResult));
        if (evaluationMessages.hasMessagePresent(MessageTypes.ERROR)) {
            return new EvaluationResult(new ArrayList<>(), evaluationMessages, parseResult.getAst());
        }

        return this.evaluateQuestions(parseResult, symbolTable, evaluationMessages);
    }

    private EvaluationResult evaluateQuestions(final ParseResult<Form> parseResult, final SymbolTable symbolTable, final Messages evaluationMessages) {
        final FormEvaluator evaluator = new FormEvaluator();
        final List<Question> questions = evaluator.evaluate(parseResult.getAst(), symbolTable);
        final List<QuestionData> questionData = this.evaluateQuestionValues(questions, symbolTable);
        return new EvaluationResult(questionData, evaluationMessages, parseResult.getAst());
    }

    private Messages performStaticAnalysis(final ParseResult<Form> parseResult) {
        final Messages returnMessage = new Messages();
        for (final IQLStaticAnalysis staticAnalysis : this.staticAnalyses) {
            final Messages analysisMessages = staticAnalysis.doCheck(parseResult.getAst());
            returnMessage.addMessages(analysisMessages);
        }
        return returnMessage;
    }

    private List<QuestionData> evaluateQuestionValues(final Iterable<Question> questions, final SymbolTable symbolTable) {
        final List<QuestionData> questionDataList = new ArrayList<>();
        for (final Question question : questions) {
            final Value value = this.getQuestionValue(question, symbolTable);
            questionDataList.add(new QuestionData(question, value));
        }
        return questionDataList;
    }

    private Value getQuestionValue(final Question question, final SymbolTable symbolTable) {
        if (question.getValue() != null)
            return this.expressionEvaluator.evaluate(question.getValue(), symbolTable);

        return symbolTable.getValue(question.getVariable().getVariableName());
    }

    private ParseResult<Form> parse(final String guiSpecification) throws IOException {
        return this.astGenerator.createAST(CharStreams.fromStream(new FileInputStream(guiSpecification)));
    }
}
