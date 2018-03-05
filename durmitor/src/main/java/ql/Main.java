package ql;

import ql.ast.form.Form;
import ql.checker.TypeChecker;
import ql.gui.GUI;
import ql.helpers.MessageBag;
import ql.visitors.ASTtoGUI;

public class Main {


    public static void main(String[] args) {


        String filePath     = (args.length == 0)? "resources/default.ql" : args[0];
        MessageBag errors   = new MessageBag();
        QL ql               = new QL(filePath, errors);
        Form form           = null;
        
        try {
            
            form = ql.getForm();
            
            if(errors.isEmpty())
            {
                // Visit and TypeCheck the AST
                TypeChecker checker = new TypeChecker(form);
                checker.checkForm();
                checker.getWarnings().print();
                
                if(checker.hasErrors())
                {
                    checker.getErrors().print();
                }
                else
                {
                    // Visit and build GUI from AST
                    GUI gui = new GUI();
                    form.getBlock().accept(new ASTtoGUI(gui));
                    
                    // Add Action/DocumentListeners to GUI.
                }
            } else {
                errors.print();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            errors.print();
        }
    }
}
