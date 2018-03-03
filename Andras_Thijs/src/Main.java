import AST.FormReader;
import GUI.FormTemplate;
import Nodes.Condition;
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
            Iterator<Question> questionIterator = questions.iterator();
            while (questionIterator.hasNext()) {
                Question question = questionIterator.next();
                System.out.println("Question: " + question.getName() + " Label: " + question.getLabel() + " Type: " + question.getType() );
            }
            Iterator<Condition> conditionIterator = form.getConditions().iterator();
            while (conditionIterator.hasNext()) {
                Condition condition = conditionIterator.next();
                System.out.println("if(" + condition.getExpression() + ") {\n" + condition.getQuestions().get(0).getLabel());
            }

            //FormTemplate formGUI = new FormTemplate(form);
            //formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
