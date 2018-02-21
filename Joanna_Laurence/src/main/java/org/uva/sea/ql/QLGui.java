package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.evaluate.Evaluator;
import org.uva.sea.ql.evaluate.EvaluatorBoolean;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QLGui {

    Map<String, Evaluator> evaluators = new HashMap<>();

    /**
     * Generate the GUI
     * @param guiSpecification Specification of the GUI
     */
    public void start(String guiSpecification) {
        try {
            QLCompiler compiler = new QLCompiler();
            Form rootNode = compiler.compileScriptFile(toCharStream(guiSpecification));
            if(rootNode == null)
                return;

            QLEvaluator evaluate = new QLEvaluator(this.evaluators);
            List<Question> questions = evaluate.getQuestions(rootNode);
            System.out.println("Total questions: " + questions.size());

        } catch (IOException e) {
            System.err.println("The gui specification cannot be found: " + guiSpecification);
            e.printStackTrace();
        }
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

    /**
     * Add a type evaluator
     * @param evaluatorName
     * @param evaluator
     */
    public void addEvaluator(String evaluatorName, Evaluator evaluator) {
        this.evaluators.put(evaluatorName, evaluator);
    }
}
