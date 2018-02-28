package nl.uva.js.qlparser.interpreter;

import nl.uva.js.qlparser.models.Form;

import java.util.List;

public class FormInterpreter {

    public static List<String> interpret(Form form) {
        return form.getComponents();
    }
}
