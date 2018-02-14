package ql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ql.ast.AstForm;
import ql.ast.form.Form;

public class Main {

    // private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // logger.info("Start");

        String filePath;
        QL ql;
        Form form;

        if (args.length == 0) {
            filePath = "resources/default.tax";
        } else {
            filePath = args[0];
        }

        ql = new QL(filePath);
        try {
            form = (Form) ql.getForm();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Visit and TypeCheck the AST

        // Visit and build GUI from AST

        // Add Action/DocumentListeners to GUI.
    }
}
