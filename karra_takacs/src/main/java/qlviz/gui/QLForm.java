package qlviz.gui;

import com.google.inject.*;
import javafx.application.Application;
import javafx.stage.Stage;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.gui.renderer.*;
import qlviz.gui.viewModel.*;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactory;
import qlviz.gui.viewModel.booleanExpressions.BooleanExpressionViewModelFactoryImpl;
import qlviz.gui.viewModel.linker.QuestionViewModelCollectorImpl;
import qlviz.gui.viewModel.linker.QuestionViewModelLinker;
import qlviz.gui.viewModel.linker.QuestionViewModelLinkerImpl;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactory;
import qlviz.gui.viewModel.numericExpressions.NumericExpressionViewModelFactoryImpl;
import qlviz.interpreter.*;
import qlviz.interpreter.linker.QuestionLinkerImpl;
import qlviz.interpreter.style.QLSParserModule;
import qlviz.model.Form;
import qlviz.typecheker.AnalysisResult;
import qlviz.typecheker.Severity;

import java.util.ArrayList;
import java.util.List;


public class QLForm extends Application {
	private FormRenderer renderer;
	private Form model;
	private FormViewModel viewModel;
	private boolean containsDuplicates = false;
	private QLParser parser;


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
				new QLParserModule());

		var rendererFactory = new JavafxFormRendererFactory(
				injector.getInstance(StyleModelBuilder.class),
				stage,
				this.getParameters().getNamed());



		var modelBuilder = injector.getInstance(ModelBuilder.class);

		try
		{
	        this.model = modelBuilder.parseForm(this.getParameters().getNamed().get("form"));
	        this.renderer = rendererFactory.create();
            NumericExpressionViewModelFactory numericExpressionViewModelFactory = new NumericExpressionViewModelFactoryImpl();
            BooleanExpressionViewModelFactory booleanExpressionFactory = new BooleanExpressionViewModelFactoryImpl(numericExpressionViewModelFactory);
            ConditionCollector conditionCollector = new CachingConditionCollector(this.model);
            QuestionViewModelFactoryImpl questionViewModelFactory =
                    new QuestionViewModelFactoryImpl(
                            numericExpressionViewModelFactory::create,
                            booleanExpressionFactory::create,
                            conditionCollector::getConditions);

            this.viewModel = new FormViewModelImpl(model, questionViewModelFactory::create);

            QuestionViewModelLinker viewModelLinker = new QuestionViewModelLinkerImpl(new QuestionViewModelCollectorImpl());
            viewModelLinker.linkQuestionStubs(this.viewModel);
            this.renderer.render(this.viewModel);
		}
		catch (ParserException e) {
			ErrorRenderer errorRenderer = new JavafxErrorRenderer(stage);
			errorRenderer.render(e.getAnalysisResults());
		}


	}
}
