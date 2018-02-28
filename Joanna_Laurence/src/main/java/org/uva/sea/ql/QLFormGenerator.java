package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.evaluate.ExpressionEvaluator;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.value.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QLFormGenerator {

    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    /**
     * Generates questions with values
     * @param guiSpecification Specification of the GUI
     * @param symbolTable The current state of the program
     * @return List of questions that should be displayed
     */
    public List<QuestionData> generate(String guiSpecification, SymbolTable symbolTable) {
        List<Question> questions = this.getQuestions(guiSpecification, symbolTable);

        List<QuestionData> questionDataList = new ArrayList<>();
        for( Question question : questions) {
            Value value = this.expressionEvaluator.evaluate(question.getValue(), symbolTable);
            questionDataList.add(new QuestionData(question, value));
        }

        return questionDataList;
    }

    /**
     * Generate the GUI
     * @param guiSpecification Specification of the GUI
     */
    private List<Question> getQuestions(String guiSpecification, SymbolTable symbolTable) {
        try {
            QLCompiler compiler = new QLCompiler();
            Form rootNode = compiler.compileScriptFile(toCharStream(guiSpecification));
            if(rootNode == null)
                return new ArrayList<>();

            FormEvaluator evaluate = new FormEvaluator();
            return evaluate.evaluate(rootNode, symbolTable);
        } catch (IOException e) {
            System.err.println("The gui specification cannot be found: " + guiSpecification);
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * Convert file name to resource
     * @param fileName The location of the file
     * @return CharStream
     * @throws IOException
     */
    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }
}
