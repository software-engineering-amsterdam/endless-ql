package nl.uva.js.qlparser.interpreter;

import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import nl.uva.js.qlparser.models.Form;

import java.util.ArrayList;

public class FormInterpreter {

    public static Layout interpret(Form form) {
        FormLayout layout = new FormLayout();

        ArrayList<Component> components = form.getComponents();

        layout.addComponents(components.toArray(new Component[components.size()]));
        return layout;
    }
}
