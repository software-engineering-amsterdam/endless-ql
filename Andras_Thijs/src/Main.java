import AST.FormReader;
import GUI.FormTemplate;
import Nodes.QLForm;
import Nodes.Question;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main (String[] args){

        FormReader formReader = new FormReader();

        try {
            QLForm form = formReader.parseFile("resources/test_grammar.txt");
            List<Question> questions = form.getQuestions();
            System.out.println("Form name: " + form.getName());
            Iterator<Question> iterator = questions.iterator();
            while (iterator.hasNext()) {
                Question question = iterator.next();
                System.out.println("Nodes.Question: " + question.getName() + " Label: " + question.getLabel() + " Nodes.Type: " + question.getType() );
            }

            FormTemplate formGUI = new FormTemplate(form);
            formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
