package nl.uva.js.qlparser.interpreter;

import com.vaadin.ui.*;
import nl.uva.js.qlparser.models.Form;

public class FormInterpreter {

    public Layout interpretForm(Form form) {
        VerticalLayout layout = new VerticalLayout();

        // TODO: IMPLEMENT (DUMMY DATA)
        TextField name;
        name = new TextField("Name");
        layout.addComponent(name);

        // A single-select radio button group
        RadioButtonGroup<String> single =
                new RadioButtonGroup<>("I am...");
        single.setItems("Male", "Female", "Apache helicopter");
        layout.addComponent(single);

        // A multi-select check box group
        CheckBoxGroup<String> multi =
                new CheckBoxGroup<>("I have bought...");
        multi.setItems("A house", "A car", "A ship");
        layout.addComponent(multi);

        return layout;
    }
}
