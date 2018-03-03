package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.dataObject.ASTResult;
import org.uva.sea.ql.dataObject.InterpreterResult;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.evaluate.ExpressionEvaluator;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.evaluate.valueTypes.Value;
import org.uva.sea.ql.exceptions.StaticAnalysisError;
import org.uva.sea.ql.parser.elements.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QLSpecificationEvaluator {

    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    /**
     * Generates questions with values
     *
     * @param guiSpecification Specification of the GUI
     * @param symbolTable      The current state of the program
     * @return List of questions that should be displayed
     */
    public InterpreterResult generate(String guiSpecification, SymbolTable symbolTable) throws IOException, StaticAnalysisError {

        ASTResult astResult = this.generateAST(guiSpecification);
        if (astResult.getAST() == null)
            return new InterpreterResult(new ArrayList<>(), astResult.getWarnings());

        FormEvaluator evaluate = new FormEvaluator();
        List<Question> questions = evaluate.evaluate(astResult.getAST(), symbolTable);
        List<QuestionData> questionDataList = interpreterQuestions(questions, symbolTable);

        return new InterpreterResult(questionDataList, astResult.getWarnings());
    }

    /**
     * Interpreter questions, and return results
     * @param symbolTable Symbol table
     * @param questions questions that are converted
     * @return Interpreted questions
     */
    private List<QuestionData> interpreterQuestions(List<Question> questions, SymbolTable symbolTable) {
        List<QuestionData> questionDataList = new ArrayList<>();
        for (Question question : questions) {
            Value value = getQuestionValue(symbolTable, question);
            questionDataList.add(new QuestionData(question, value));
        }
        return questionDataList;
    }

    /**
     * Compute valueTypes or get the valueTypes from the symbol table
     *
     * @param symbolTable Symbol table
     * @param question Questions
     * @return Value of the question
     */
    private Value getQuestionValue(SymbolTable symbolTable, Question question) {
        if (question.getValue() != null)
            return this.expressionEvaluator.evaluate(question.getValue(), symbolTable);

        return symbolTable.getValue(question.getVariable().getVariableName());
    }

    /**
     * Generate the AST
     *
     * @param guiSpecification Specification of the GUI
     */
    private ASTResult generateAST(String guiSpecification) throws IOException, StaticAnalysisError {
        ASTGenerator astGenerator = new ASTGenerator();
        return astGenerator.interpreterScriptFile(toCharStream(guiSpecification));
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
