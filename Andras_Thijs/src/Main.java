import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main (String[] args){

        //FormDisplay form = new FormDisplay();
        //form.displayForm();




        FormReader formReader = new FormReader();


        CharStream testInput1 = CharStreams.fromString("");

        CharStream testInput2 = CharStreams.fromString("foo");

        CharStream testInput3 = CharStreams.fromString("form Box1HouseOwning {}");

        CharStream testInput4 = CharStreams.fromString("form Box1HouseOwning { foo }");


        try {
            QLForm form = formReader.parseFile("src\\test_grammar.txt");
            List<Question> questions = form.getQuestions();
            System.out.println("Form name: " + form.getName());
            Iterator<Question> iterator = questions.iterator();
            while (iterator.hasNext()) {
                Question question = iterator.next();
                System.out.println("Question: " + question.getName() + " Label: " + question.getLabel() + " Type: " + question.getString() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
