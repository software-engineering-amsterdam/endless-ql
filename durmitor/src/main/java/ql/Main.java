package ql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ql.ast.AstForm;

public class Main {

    // private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        // logger.info("Start");

        String filePath;
        QL ql;
        AstForm root;

        if (args.length == 0) {
            filePath = "resources/default.tax";
        } else {
            filePath = args[0];
        }

        ql = new QL(filePath);
        try {
            root = (AstForm) ql.getAst();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Visit and TypeCheck the AST

        // Visit and build GUI from AST

        // Add Action/DocumentListeners to GUI.
    }
}
