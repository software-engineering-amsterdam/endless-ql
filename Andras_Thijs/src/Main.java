import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main (String[] args){
        FormReader formReader = new FormReader();

        try {
            QLForm form = formReader.parseFile("./src/test_grammar.txt");
            List<Question> questions = form.getQuestions();
            System.out.println("Form name: " + form.getName());
            Iterator<Question> iterator = questions.iterator();
            while (iterator.hasNext()) {
                Question question = iterator.next();
                System.out.println("Question: " + question.getName() + " Label: " + question.getLabel() + " Type: " + question.getType() );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        FormTemplate form = new FormTemplate();
        form.initGUI();
    }
}
