package nl.uva.js.qlparser.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.annotations.Theme;

@SpringUI
@Theme("valo")
public class QlparserUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final TextField name;
		name = new TextField("Name");

		final Button hello = new Button("Hello");
		hello.addClickListener(
				e -> Notification.show("Hi " + name.getValue())
		);
		setContent(new VerticalLayout(name, hello));
	}
}
