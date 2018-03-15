package org.uva.sea.languages.ql.interpreter;

import org.uva.sea.languages.ql.interpreter.dataObject.ASTResult;
import org.uva.sea.languages.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.ExpressionEvaluator;
import org.uva.sea.languages.ql.interpreter.evaluate.FormEvaluator;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.languages.ql.parser.elements.Form;
import org.uva.sea.languages.ql.parser.elements.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    /**
     * Generates questions with values
     *
     * @param qlFile Specification of the GUI
     * @param symbolTable      The current state of the program
     * @return List of questions that should be displayed
     */
    public InterpreterResult generate(String qlFile, SymbolTable symbolTable) throws IOException, StaticAnalysisError {

        ASTResult<Form> astResult = this.generateAST(qlFile);
        if (astResult.getAST() == null)
            return new InterpreterResult(new ArrayList<>(), astResult.getWarnings());

        FormEvaluator evaluator = new FormEvaluator();
        List<Question> questions = evaluator.evaluate(astResult.getAST(), symbolTable);
        List<QuestionData> questionDataList = interpreterQuestions(questions, symbolTable);

        return new InterpreterResult(questionDataList, astResult.getWarnings());
    }

    /**
     * ApplyQLSStyle questions, and return results
     *
     * @param symbolTable Symbol table
     * @param questions   questions that are converted
     * @return Interpreted questions
     */
    private List<QuestionData> interpreterQuestions(List<Question> questions, SymbolTable symbolTable) {
        List<QuestionData> questionDataList = new ArrayList<>();
        for (Question question : questions) {
            Value value = getQuestionValue(question, symbolTable);
            questionDataList.add(new QuestionData(question, value));
        }
        return questionDataList;
    }

    /**
     * Compute valueTypes or get the valueTypes from the symbol table
     *
     * @param symbolTable Symbol table
     * @param question    Questions
     * @return Value of the questionData
     */
    private Value getQuestionValue(Question question, SymbolTable symbolTable) {
        if (question.getValue() != null)
            return this.expressionEvaluator.evaluate(question.getValue(), symbolTable);

        return symbolTable.getValue(question.getVariable().getVariableName());
    }

    /**
     * Generate the AST
     *
     * @param guiSpecification Specification of the GUI
     */
    private ASTResult<Form> generateAST(String guiSpecification) throws IOException, StaticAnalysisError {
        ASTGenerator astGenerator = new ASTGenerator();
        return astGenerator.interpreterScriptFile(guiSpecification);
    }

}
