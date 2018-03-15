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

            for(Nodes.Question q : form.getQuestions())
                if(q.expression != null)
                    q.getExpressionValue();

            for(Nodes.Condition c : form.getConditions())
                if(c.expression != null)
                    c.getExpressionValue();

            //FormTemplate formGUI = new FormTemplate(form);
            //formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: Enable this again when we call getExpressionValue
        catch (SyntaxException e) {
            // There's a syntax error!
            e.printStackTrace();
        } catch (TypeException e) {
            System.out.println(e.getMessage());
            // There's a Type error!
            e.printStackTrace();
        }
    }
}
