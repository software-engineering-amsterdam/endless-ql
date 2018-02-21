package nl.uva.js.qlparser.ui;


import com.vaadin.ui.*;
import nl.uva.js.qlparser.interpreter.FormInterpreter;
import nl.uva.js.qlparser.logic.Ingester;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import nl.uva.js.qlparser.models.Form;

import java.io.IOException;

@SpringUI
@Theme("valo")
public class WebUI extends UI {

    // TODO: DI
    private Ingester ingester = new Ingester();
    private FormInterpreter interpreter = new FormInterpreter();

    @Override
    protected void init(VaadinRequest request) {
        TextArea area = new TextArea("Enter Questionnaire Language:");
        area.setWidth("700");
        area.setHeight("500");

        final Button btnRender = new Button("Render QL");

        setContent(new VerticalLayout(area, btnRender));

        btnRender.addClickListener(
                e -> setContent(createLayout(area.getValue()))
        );
    }

    private Layout createLayout(String qlInput) {

        // Dummy data
        return interpreter.interpretForm(null);

        /*
        try {
            String tempFileLocation = ""; // Todo
            Form form = ingester.toParsedForm(tempFileLocation);
            return interpreter.interpretForm(form);

        } catch (IOException e) {
            VerticalLayout error = new VerticalLayout();
            error.addComponent(new Label("Could not parse Questionnaire Language."));
            return error;
        }
        */
    }
}
