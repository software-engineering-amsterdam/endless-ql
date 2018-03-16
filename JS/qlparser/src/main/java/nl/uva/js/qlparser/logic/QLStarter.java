package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.ui.GUIBuilder;

class QLStarter {

    public static void main(String[] args) {
        Form form = FormBuilder.parseFormFromLocation(QLStarter.class.getClassLoader().getResource(
                System.getProperty("ql.file"))
                        .getFile());
        GUIBuilder.getGUI(form).setVisible(true);

        // TEMPORARY
        try {
            Stylesheet stylesheet = StylesheetBuilder.parseStylesheetFromLocation(QLStarter.class.getClassLoader().getResource(
                    System.getProperty("qls.file"))
                    .getFile());
            System.out.println(stylesheet.getName());
        } catch (ParseException e) {
            System.out.println("Unable to parse QLS");
        }

    }
}
