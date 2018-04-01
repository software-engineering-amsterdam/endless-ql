package qlviz.gui;

import com.google.inject.*;
import javafx.application.Application;
import javafx.stage.Stage;
import qlviz.gui.renderer.*;
import qlviz.gui.viewModel.*;
import qlviz.gui.viewModel.linker.QuestionViewModelLinker;
import qlviz.interpreter.*;
import qlviz.interpreter.style.QLSParserModule;
import qlviz.model.Form;


public class QLForm extends Application {
	private FormRenderer renderer;
	private Form model;
	private FormViewModel viewModel;


	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {

		var injector = Guice.createInjector(
				new QLSParserModule(),
				new ExpressionParserModule(),
				new QLParserModule(),
				new ViewModelFactoryModule(),
				new ViewModelLinkerModule());

		var rendererFactory = new JavafxFormRendererFactory(
				injector.getInstance(StyleModelBuilder.class),
				stage,
				this.getParameters().getNamed());



		var modelBuilder = injector.getInstance(ModelBuilder.class);

		try
		{
	        this.model = modelBuilder.parseForm(this.getParameters().getNamed().get("form"));
	        this.renderer = rendererFactory.create();

            var viewModelFactory = injector.getInstance(FormViewModelFactory.class);
            this.viewModel = viewModelFactory.create(this.model);

            var viewModelLinker = injector.getInstance(QuestionViewModelLinker.class);
            viewModelLinker.linkQuestionStubs(this.viewModel);
            this.renderer.render(this.viewModel);
		}
		catch (ParserException e) {
			ErrorRenderer errorRenderer = new JavafxErrorRenderer(stage);
			errorRenderer.render(e.getAnalysisResults());
		}


	}
}
