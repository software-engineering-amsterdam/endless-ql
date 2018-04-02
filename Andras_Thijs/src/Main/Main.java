package Main;

import AST.FormReader;
import GUI.QLGUI;
import Nodes.Condition;
import Nodes.QLForm;
import QLExceptions.*;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.util.List;

class Main {

    public static void main (String[] args){

        try {

            FormReader formReader = new FormReader();

            // Read the form from a file and set the parent structure.
            QLForm form = formReader.parseCharStream(CharStreams.fromFileName(new PathChooser().getFilePath()));
            form.setParents();

            // Check for duplicate names and labels.
            DuplicateChecker checker = new DuplicateChecker();
            checker.checkQuestions(form.getQuestions());
            // Recursively check all Questions in Conditions.
            List<Condition> conditions = form.getConditions();
            for(Condition c : conditions) {
                checker.checkQuestions(c.getQuestions());
                conditions.addAll(c.getConditions());
            }

            // Write a log line for every duplicate label.
            for(String label : checker.getDuplicateLabels())
                System.out.println("WARNING: DUPLICATE LABEL " + label);

            QLGUI formGUI = new QLGUI(form);
            formGUI.renderForm();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SyntaxException e) {
            System.out.println("There is a Syntax error at Node: " + e.getNode());
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (TypeException e) {
            System.out.println("There is a Type error at Node: " + e.getNode());
            System.out.println("Expected: " + e.getExpected());
            System.out.println("Received: " + e.getReceived());
            e.printStackTrace();
        }
    }

}
