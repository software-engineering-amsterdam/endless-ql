package nl.uva.js.qlparser.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.data.HasValue;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import nl.uva.js.qlparser.interpreter.FormInterpreter;
import nl.uva.js.qlparser.logic.QLIngester;
import nl.uva.js.qlparser.models.Form;
import nl.uva.js.qlparser.models.Reloadable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@SpringUI
@Theme("valo")
public class WebUI extends UI {
    @Value("${ql.mode}")
    private String mode;

    @Autowired
    private Reloadable<Form> qlForm;

    private Button reloadButton;

    @Override
    protected void init(VaadinRequest request) {
        if (mode.equals("file")) {
            reloadButton = new Button("Reload QL file and refresh");
            reloadButton.addClickListener(e -> reload());
            setContent(createLayoutFromFile());

        } else if (mode.equals("dynamic")) {
            TextArea area = new TextArea("Enter Questionnaire Language:");
            area.setWidth("700");
            area.setHeight("500");

            Button btnRender = new Button("Render QL");
            btnRender.addClickListener(
                    e -> setContent(createLayoutFromQLString(area.getValue()))
            );

            setContent(new FormLayout(area, btnRender));
        }
    }

    private void reload() {
        try {
            qlForm.reload();
            Page.getCurrent().reload();

//        The form reload action can throw exceptions when the file is unparsable or nowhere to be found
        } catch (Exception e) {
            reloadButton.setComponentError(new UserError("Unable to load QL file"));
        }
    }

    private Layout createLayoutFromFile() {
        FormLayout layout = new FormLayout();

        List<Component> components = FormInterpreter.interpret(qlForm.getValue());
        addListeners(components);

        layout.addComponent(reloadButton);
        components.forEach(layout::addComponent);

        return layout;
    }

    private Layout createLayoutFromQLString(String qlInput) {
        FormLayout formLayout = new FormLayout();

        List<Component> components = FormInterpreter.interpret(QLIngester.parseFormFromString(qlInput));
        addListeners(components);

        components.forEach(formLayout::addComponent);
        return formLayout;
    }

    private void reEvaluate(Component component, Object value) {
        showNotification(component.getId() + " is now " + value.toString()); // TODO
    }

    private void addListeners(List<Component> components) {
        for (Component component : components) {
            if (component instanceof HasValue)
                ((HasValue) component).addValueChangeListener(event -> reEvaluate(component, event.getValue()));
        }
    }
}
