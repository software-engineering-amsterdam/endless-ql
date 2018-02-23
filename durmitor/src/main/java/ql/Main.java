package ql;

import ql.ast.form.Form;
import ql.checker.TypeChecker;

public class Main {

    // private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // logger.info("Start");

        String filePath;
        QL ql;
        Form form = null;

        if (args.length == 0) {
            filePath = "resources/default.tax";
        } else {
            filePath = args[0];
        }

        ql = new QL(filePath);
        try {
            form = (Form) ql.getForm();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Visit and TypeCheck the AST
        TypeChecker checker = new TypeChecker(form);
        checker.checkForm();
        checker.printErrors();
        checker.printWarnings();
        
        // Visit and build GUI from AST

        // Add Action/DocumentListeners to GUI.
    }
}
