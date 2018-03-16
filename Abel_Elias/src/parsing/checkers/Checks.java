package parsing.checkers;

import parsing.gen.QLParser;

public class Checks {
    public static void checkForm(QLParser.FormContext form){
        try{
            new VariableChecker(form);
            new TypeChecker(form);
        } catch(Error e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
