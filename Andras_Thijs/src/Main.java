import AST.FormReader;
import GUI.FormTemplate;
import Nodes.Condition;
import Nodes.QLForm;
import QLExceptions.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args){
        FormReader formReader = new FormReader();

        try {
            // Read the form from a file and set the parent structure.
            QLForm form = formReader.parseFile("resources/test_grammar.txt");
            form.setParents();

            // Check for duplicate names and labels.
            TwoLists lists = new TwoLists();
            lists.checkQuestions(form.getQuestions());
            // Recursively check all Questions in Conditions.
            List<Condition> conditions = form.getConditions();
            for(Condition c : conditions) {
                lists.checkQuestions(c.getQuestions());
                conditions.addAll(c.getConditions());
            }

            // TODO: Improve this?
            // Write a log line for every duplicate label.
            for(String label : lists.getDuplicateLabels())
                System.out.println("WARNING: DUPLICATE LABEL " + label);

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

//            FormTemplate formGUI = new FormTemplate(form);
//            formGUI.renderForm();

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
