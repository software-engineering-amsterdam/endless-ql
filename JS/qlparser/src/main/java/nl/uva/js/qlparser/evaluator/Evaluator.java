package nl.uva.js.qlparser.evaluator;

import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Evaluator {

    public VerticalLayout eval() {
        VerticalLayout layout = new VerticalLayout();

        // example placeholder
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
