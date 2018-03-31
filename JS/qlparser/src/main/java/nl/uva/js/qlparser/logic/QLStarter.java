package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.expressions.Form;
import nl.uva.js.qlparser.models.qls.Stylesheet;
import nl.uva.js.qlparser.ui.GUIBuilder;

class QLStarter {

    public static void main(String[] args) {
        Form form = NonNullRun.function(System.getProperty("ql.file"), qlfile ->
                FormBuilder.parseFormFromLocation(QLStarter.class.getClassLoader().getResource(qlfile).getFile()));

        if (form == null) GUIBuilder.getGUI(Form.builder().name("emptyForm").build()).setVisible(true);
        else GUIBuilder.getGUI(form).setVisible(true);

        // TEMPORARY
        try {
            Stylesheet stylesheet = NonNullRun.function(System.getProperty("qls.file"), qlsfile ->
                    StylesheetBuilder.parseStylesheetFromLocation(QLStarter.class.getClassLoader().getResource(qlsfile).getFile()));
        } catch (ParseException e) {
            System.err.println("Unable to parse QLS");
        }

    }
}
