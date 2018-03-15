import AST.FormReader;
import Nodes.QLForm;
import QLExceptions.*;

import java.io.IOException;

public class Main {
    public static void main (String[] args){
        FormReader formReader = new FormReader();

        try {
            QLForm form = formReader.parseFile("resources/test_order_of_operations.txt");
            form.setParents();

            // TODO: TESTING PURPOSES, REMOVE SOON.
            for(Nodes.Question q : form.getQuestions())
                if(q.expression != null) {
                    q.getExpressionValue();
                    System.out.println(q.getResult().getFloat());
                }

            for(Nodes.Condition c : form.getConditions())
                if(c.expression != null) {
                    c.getExpressionValue();
                    System.out.println(c.getResult());
                }
            //\TODO

            //FormTemplate formGUI = new FormTemplate(form);
            //formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Fix this
        catch (SyntaxException e) {
            System.out.println(e.getNode());
            // There's a syntax error!
            e.printStackTrace();
        } catch (TypeException e) {
            System.out.println(e.getNode());
            System.out.println(e.getExpected());
            System.out.println(e.getReceived());
            // There's a Type error!
            e.printStackTrace();
        }
    }
}
