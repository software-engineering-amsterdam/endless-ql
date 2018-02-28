package nl.uva.js.qlparser.interpreter;

import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import nl.uva.js.qlparser.models.Form;

import java.util.List;

public class FormInterpreter {

    public static List<AbstractField> interpret(Form form) {
        return form.getComponents();
    }
}
