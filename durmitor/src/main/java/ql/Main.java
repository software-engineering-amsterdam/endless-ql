package ql;

import java.util.Map;

import ql.ast.form.Form;
import ql.ast.type.Type;
import ql.checker.TypeChecker;
import ql.gui.GUI;
import ql.visitors.ASTtoGUI;
import ql.visitors.SymbolTable;

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
        Map<String, Type> symbolTable   = new SymbolTable().build(form);
        TypeChecker checker             = new TypeChecker();
        checker.checkIdentifiers(form);
        checker.checkReferences(form, symbolTable);
        checker.checkConditions(form, symbolTable);
        checker.checkOperands(form, symbolTable);
        checker.checkLabels(form);
        checker.printErrors();
        checker.printWarnings();
        
        // Visit and build GUI from AST
        GUI gui = new GUI();
        ASTtoGUI astToGUI = new ASTtoGUI(gui);
        astToGUI.visit(form);
        // Add Action/DocumentListeners to GUI.
    }
}
