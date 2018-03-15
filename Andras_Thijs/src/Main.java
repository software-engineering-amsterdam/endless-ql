import AST.FormReader;
import Nodes.QLForm;
import QLExceptions.*;

import java.io.IOException;

public class Main {
    public static void main (String[] args){
        FormReader formReader = new FormReader();

        try {
            QLForm form = formReader.parseFile("resources/test_grammar.txt");
            form.setParents();
            //FormTemplate formGUI = new FormTemplate(form);
            //formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Enable this again when we call getExpressionValue
        /* catch (SyntaxException e) {
            // There's a syntax error!
            e.printStackTrace();
        } catch (TypeException e) {
            // There's a Type error!
            e.printStackTrace();
        }*/
    }
}
