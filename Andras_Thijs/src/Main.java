import AST.FormReader;
import Nodes.QLForm;
import java.io.IOException;


public class Main {
    public static void main (String[] args){

        FormReader formReader = new FormReader();

        try {
            QLForm form = formReader.parseFile("resources/test_grammar.txt");
            //FormTemplate formGUI = new FormTemplate(form);
            //formGUI.initGUI();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
