package nl.uva.js.qlparser.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import nl.uva.js.qlparser.interpreter.FormInterpreter;
import nl.uva.js.qlparser.logic.QLIngester;
import nl.uva.js.qlparser.models.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;

@SpringUI
@Theme("valo")
public class WebUI extends UI {
    @Value("${ql.mode}")
    private String mode;

    @Autowired
    private Form qlForm;

    private Button btnReload;

    @Override
    protected void init(VaadinRequest request) {
        if (mode.equals("file")) {
            btnReload = new Button("Reload QL file");
            btnReload.addClickListener(
                    e -> setContent(createLayoutFromFile())
            );

            setContent(createLayoutFromFile());
        }

        else if (mode.equals("dynamic")) {

            TextArea area = new TextArea("Enter Questionnaire Language:");
            area.setWidth("700");
            area.setHeight("500");

            final Button btnRender = new Button("Render QL");

            setContent(new FormLayout(area, btnRender));

            btnRender.addClickListener(
                    e -> setContent(createLayoutFromQLString(area.getValue()))
            );
        }
    }

    private Layout createLayoutFromFile() {
        FormLayout layout = new FormLayout();

        ArrayList<Component> components = FormInterpreter.interpret(qlForm);

        layout.addComponent(btnReload);
        layout.addComponents(components.toArray(new Component[components.size()]));
        return layout;
    }

    private Layout createLayoutFromQLString(String qlInput) {
        ArrayList<Component> components = FormInterpreter.interpret(QLIngester.parseFormFromString(qlInput));
        return new FormLayout(components.toArray(new Component[components.size()]));
    }
}
