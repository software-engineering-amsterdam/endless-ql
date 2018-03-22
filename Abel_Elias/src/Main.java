import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QLS.StylesheetEvaluator;
import QLS.classes.Stylesheet;
import QLS.parsing.gen.QLSParser;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.FormBuilder;
import QL.parsing.TreeBuilder;
import QL.parsing.checkers.Checks;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.FormVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    // Temp method to create questions
    private HashMap<String, Question> getQuestionTemp() {
        LinkedHashMap<String, Question> questionHashMap = new LinkedHashMap<String, Question>();
        questionHashMap.put("1", new Question("Is this a question?", new BooleanValue(), false, true));
        questionHashMap.put("2", new Question("Is this a question?", new StringValue(), false, true));
        questionHashMap.put("3", new Question("Is this a question?", new IntegerValue(), false, true));
        questionHashMap.put("4", new Question("Is this a question?", new DateValue(), false, true));
        return questionHashMap;
    }

    private void printQuestionMap(HashMap<String, Question> memory){
        //Test output
        for (Map.Entry e : memory.entrySet()) {
            Question q = (Question) e.getValue();
            String id = (String) e.getKey();
            System.out.println(id + ":\t" + q) ;
        }
    }

    /**
     * parseAndBuildQL() method
     * @param inputStream fileInput (Ql)
     */
    private void parseAndBuildQL(InputStream inputStream){
        try{
            QLParser.FormContext form = new TreeBuilder().build(inputStream);
            Checks.checkForm(form);
            FormVisitor coreVisitor = new FormVisitor(form);
            //Pass the relevant questions to the UI builder
            printQuestionMap(coreVisitor.questionMap);
            FormBuilder formBuilder = new FormBuilder(coreVisitor);
            formBuilder.initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parseAndBuildQLS() method
     * @param inputStream fileInput (Qls)
     */
    private void parseAndBuildQLS(InputStream inputStream) {
        try{
            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(inputStream);
            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor();
            Stylesheet stylesheet = stylesheetVisitor.visitStylesheet(stylesheetContext);

            StylesheetEvaluator evaluator = new StylesheetEvaluator(stylesheet);

            System.out.println("Stylesheet constructed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            return fileName.substring(i+1);
        } else
        {
            return "";
        }
    }

    /**
     * Main method
     * @param args given arguments
     */
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                new Main().parseAndBuildQL(System.in);
            } else if (args.length == 1) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                if(getFileExtension(args[0]).equals("ql")) {
                    new Main().parseAndBuildQL(fileInputStream);
                } else if (getFileExtension(args[0]).equals("qls")) {
                    new Main().parseAndBuildQLS(fileInputStream);
                } else {
                    System.out.println("Invalid file type");
                }
            } else {
                System.out.println("Invalid arguments were given");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
