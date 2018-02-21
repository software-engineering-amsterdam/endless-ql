package nl.uva.js.qlparser.ui;


import com.vaadin.ui.*;
import nl.uva.js.qlparser.interpreter.FormInterpreter;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import nl.uva.js.qlparser.logic.QLIngester;
import nl.uva.js.qlparser.models.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@SpringUI
@Theme("valo")
public class WebUI extends UI {
    @Value("${ql.mode}")
    private String mode;

    @Autowired
    private Form qlForm;

    @Override
    protected void init(VaadinRequest request) {
        if (mode.equals("file")) setContent(createFileLayout());
        else if (mode.equals("dynamic")) {

            TextArea area = new TextArea("Enter Questionnaire Language:");
            area.setWidth("700");
            area.setHeight("500");

            final Button btnRender = new Button("Render QL");

            setContent(new VerticalLayout(area, btnRender));

            btnRender.addClickListener(
                    e -> setContent(createDynamicLayout(area.getValue()))
            );
        }
    }

    private Layout createFileLayout() {
        return FormInterpreter.interpret(qlForm);
    }

    private Layout createDynamicLayout(String qlInput) {
        return FormInterpreter.interpret(QLIngester.parseFormFromString(qlInput));
    }
}
