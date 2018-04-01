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
    private void parseAndBuildQLS(FileInputStream ql, FileInputStream qls) {
        try {
            // QL
            QLParser.FormContext form = new TreeBuilder().build(ql);
            FormVisitor coreVisitor = new FormVisitor().visitForm(form);

            // QLS
            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(qls);
            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(coreVisitor.getQuestions());
            stylesheetVisitor.visitStylesheet(stylesheetContext);

            new GUIBuilder(coreVisitor, stylesheetVisitor);
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
                new Main().parseAndBuildQL(System.in);
            } else if (args.length == 1) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                new Main().parseAndBuildQL(fileInputStream);
            } else if (args.length == 2) {
                FileInputStream ql = new FileInputStream(args[0]);
                FileInputStream qls = new FileInputStream(args[1]);
                new Main().parseAndBuildQLS(ql,qls);
            } else {
                System.out.println("Incorrect usage of tool, check out README for more info");
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
