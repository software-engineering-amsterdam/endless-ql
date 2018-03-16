import classes.Question;
import gui.FormBuilder;
import parsing.TreeBuilder;
import parsing.visitors.InitVisitor;

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
     * @param inputStream - input stream of the given form file
     */

    private void parseAndBuild(InputStream inputStream){
        try{

            InitVisitor builder = new InitVisitor(new TreeBuilder().build(inputStream));
            HashMap<String, Question> memory = builder.getQuestions();

            //Test output
            Iterator it = memory.entrySet().iterator();
            while (it.hasNext()) {
                  Map.Entry pair = (Map.Entry)it.next();
                  System.out.println(pair.getKey() + " : " + pair.getValue() + " = " + ((Question) pair.getValue()).getValue());
                  it.remove();
            }

            //Pass the relevant questions to the UI builder
            FormBuilder formBuilder = new FormBuilder(builder, memory);
            formBuilder.initComponents();

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
