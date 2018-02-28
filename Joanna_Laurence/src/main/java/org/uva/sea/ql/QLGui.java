package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;

import java.io.IOException;
import java.util.List;

public class QLGui {

    List<Question> questions;

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

            FormEvaluator evaluate = new FormEvaluator();
            questions = evaluate.evaluate(rootNode, new SymbolTable());
            System.out.println("Total questions: " + questions.size());

        } catch (IOException e) {
            System.err.println("The gui specification cannot be found: " + guiSpecification);
            e.printStackTrace();
        }
    }

    public List<Question> getQuestions() {
        return questions;
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
