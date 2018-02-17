package ql;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ql.ast.form.Form;
import ql.ast.type.Type;
import ql.checker.TypeChecker;
import ql.visitors.SymbolTable;

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
        Map<String, Type> symbolTable   = new SymbolTable().build(form);
        TypeChecker checker             = new TypeChecker();
        checker.checkIdentifiers(form);
        checker.checkReferences(form, symbolTable);
        checker.checkConditions(form, symbolTable);
        checker.checkLabels(form);
        checker.printErrors();
        checker.printWarnings();
        
        // Visit and build GUI from AST

        // Add Action/DocumentListeners to GUI.
    }
}
