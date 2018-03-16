import classes.Form;
import classes.Question;
import parsing.TreeBuilder;
import parsing.checkers.Checks;
import parsing.gen.QLParser;
import parsing.visitors.FormVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    /**
     * parse and build the form file
     */

    private void printQuestionMap(HashMap<String, Question> memory){
        //Test output
        Iterator it = memory.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Question q = (Question) pair.getValue();
            System.out.println(q) ;
            it.remove();
        }
    }

    private void parseAndBuild(InputStream inputStream){
        try{
            QLParser.FormContext form = new TreeBuilder().build(inputStream);
            FormVisitor coreVisitor = new FormVisitor(form);
            Checks.checkForm(form);
            HashMap<String, Question> memory = coreVisitor.questionMap;

            printQuestionMap(memory);

            //Pass the relevant questions to the UI builder
//            FormBuilder formBuilder = new FormBuilder(builder, memory);
//            formBuilder.initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Main method
     * @param args given arguments
     */
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                new Main().parseAndBuild(System.in);
            } else if (args.length == 1) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                new Main().parseAndBuild(fileInputStream);
            } else {
                System.out.println("Invalid arguments were given");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
