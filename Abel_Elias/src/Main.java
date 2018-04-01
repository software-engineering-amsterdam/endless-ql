import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QLS.classes.Stylesheet;
import QLS.parsing.gen.QLSParser;
import QLS.parsing.visitors.StylesheetVisitor;
import gui.GUIBuilder;
import QL.parsing.TreeBuilder;
import QL.parsing.checkers.Checks;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.FormVisitor;
import gui.GUIBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    
    /**
     * parseAndBuildQL() method
     *
     * @param inputStream fileInput (Ql)
     */
    private void parseAndBuildQL(InputStream inputStream) {
        try {
            QLParser.FormContext form = new TreeBuilder().build(inputStream);
            Checks.checkForm(form);
            FormVisitor coreVisitor = new FormVisitor().visitForm(form);
            //Pass the relevant questions to the UI builder
            GUIBuilder GUIBuilder = new GUIBuilder(coreVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parseAndBuildQLS() method
     *
     * */
    private void parseAndBuildQLS() {
        try {
            // QL
            FileInputStream qlInputStream = new FileInputStream("C:\\dev\\uva\\endless-ql\\Abel_Elias\\resources\\QL\\formQl.ql");
            QLParser.FormContext form = new TreeBuilder().build(qlInputStream);
            FormVisitor coreVisitor = new FormVisitor().visitForm(form);

            // QLS
//            FileInputStream qlsInputStream = new FileInputStream("C:\\dev\\uva\\endless-ql\\Abel_Elias\\resources\\QLS\\exampleForm5.qls");
//            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(qlsInputStream);
//            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(coreVisitor.getQuestions());
//            stylesheetVisitor.visitStylesheet(stylesheetContext);
//            new GUIBuilder(coreVisitor, stylesheetVisitor);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method
     *
     * @param args given arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                //new Main().parseAndBuildQLS();
                new Main().parseAndBuildQL(System.in);
            } else if (args.length == 1) {
                    FileInputStream fileInputStream = new FileInputStream(args[0]);
                    new Main().parseAndBuildQL(fileInputStream);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
