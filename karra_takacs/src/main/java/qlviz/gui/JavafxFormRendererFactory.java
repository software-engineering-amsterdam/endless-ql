package qlviz.gui;

import com.google.inject.Guice;
import javafx.stage.Stage;
import qlviz.gui.renderer.FormRenderer;
import qlviz.gui.renderer.JavafxRendererModule;
import qlviz.gui.renderer.StyledJavafxRendererModule;

import java.util.Map;

public class JavafxFormRendererFactory implements FormRendererFactory {

	private final StyleModelBuilder styleModelBuilder;
	private final Stage stage;
	private final Map<String, String> commandLineArgs;

	public JavafxFormRendererFactory(StyleModelBuilder styleModelBuilder, Stage stage, Map<String, String> commandLineArgs) {
		this.styleModelBuilder = styleModelBuilder;

		this.stage = stage;
		this.commandLineArgs = commandLineArgs;
	}

	@Override
	public FormRenderer create() {
		if (commandLineArgs.containsKey("stylesheet")) {
			var stylesheet = styleModelBuilder.createFromMarkup(commandLineArgs.get("stylesheet"));
			return Guice.createInjector(new StyledJavafxRendererModule(stylesheet, stage))
					.getInstance(FormRenderer.class);
		}
		else
		{
			return Guice.createInjector(new JavafxRendererModule(stage))
					.getInstance(FormRenderer.class);
		}
	}
}

