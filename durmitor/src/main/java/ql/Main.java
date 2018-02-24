package ql;

import ql.ast.form.Form;
import ql.checker.TypeChecker;
import ql.gui.GUI;
import ql.visitors.ASTtoGUI;

public class Main {


    public static void main(String[] args) {


        String filePath;
        QL ql;
        Form form = null;

        if (args.length == 0) {
            filePath = "resources/default.ql";
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
//        GUI gui = new GUI();
//        form.getBlock().accept(new ASTtoGUI(gui));
        // Add Action/DocumentListeners to GUI.
    }
}
