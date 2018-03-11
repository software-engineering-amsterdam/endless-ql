package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.expressions.Form;
import nl.uva.js.qlparser.ui.GUIBuilder;

public class QLStarter {

    public static void main(String[] args) {
        Form form = FormBuilder.parseFormFromLocation(QLStarter.class.getClassLoader().getResource(
                System.getProperty("ql.file"))
                        .getFile());
        GUIBuilder.getGUI(form).setVisible(true);
    }
}
