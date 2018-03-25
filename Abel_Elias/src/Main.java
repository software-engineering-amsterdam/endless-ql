import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
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
    
    /**
     * parseAndBuildQL() method
     *
     * @param inputStream fileInput (Ql)
     */
    private void parseAndBuildQL(InputStream inputStream) {
        try {
            QLParser.FormContext form = new TreeBuilder().build(inputStream);
            Checks.checkForm(form);
            FormVisitor coreVisitor = new FormVisitor(form);
            //Pass the relevant questions to the UI builder
            FormBuilder formBuilder = new FormBuilder(coreVisitor);
            formBuilder.initComponents();
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
            FileInputStream qlInputStream = new FileInputStream("/home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/resources/QL/exampleForm4.ql");
            QLParser.FormContext form = new TreeBuilder().build(qlInputStream);
            Checks.checkForm(form);
            FormVisitor coreVisitor = new FormVisitor(form);

            // QLS
            FileInputStream qlsInputStream = new FileInputStream("/home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/resources/QLS/exampleForm5.qls");
            QLSParser.StylesheetContext stylesheetContext = new TreeBuilder().buildQls(qlsInputStream);
            StylesheetVisitor stylesheetVisitor = new StylesheetVisitor();
            stylesheetVisitor.visitStylesheet(stylesheetContext);

            //Evaluate
//            TestPrinter testprinter = new TestPrinter();
//            testprinter.printQLSStyleSheet(stylesheet);

            FormBuilder formBuilder = new FormBuilder(coreVisitor, stylesheetVisitor);
            formBuilder.initComponents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            return fileName.substring(i + 1);
        } else {
            return "";
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
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
