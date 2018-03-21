package qlviz.gui;

import com.google.inject.*;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import qlviz.QLVisitor;
import qlviz.gui.renderer.*;
import qlviz.gui.renderer.javafx.*;
import qlviz.gui.renderer.layout.NaiveQuestionLocator;
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
import qlviz.model.style.*;
import qlviz.typecheker.AnalysisResult;
import qlviz.typecheker.Severity;
import qlviz.typecheker.StaticChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QLForm extends Application {
	private FormRenderer renderer;
	private Form model;
	private FormViewModel viewModel;
	private boolean containsDuplicates = false;


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

		Injector injector = Guice.createInjector(
				new QLSParserModule(),
				new ExpressionParserModule(),
				new QLParserModule());
		Injector rendererInjector = Guice.createInjector(new JavafxRendererModule(stage));
		StyleModelBuilder styleBuilder = injector.getInstance(StyleModelBuilder.class);



		if (this.getParameters().getRaw().size() > 1) {

			Stylesheet stylesheet = styleBuilder.createFromMarkup(this.getParameters().getRaw().get(1));
			rendererInjector = Guice.createInjector(new StyledJavafxRendererModule(stylesheet, stage));
		}
		this.renderer = rendererInjector.getInstance(FormRenderer.class);

		List<AnalysisResult> staticCheckResults = new ArrayList<>();
		QLVisitor<Form> visitor = injector.getInstance(Key.get(new TypeLiteral<QLVisitor<Form>>(){}));
		ModelBuilder modelBuilder = new ModelBuilder(visitor, new QuestionLinkerImpl(new TypedQuestionWalker()));

		this.model = modelBuilder.createFormFromMarkup(this.getParameters().getRaw().get(0));


		StaticChecker staticChecker = new StaticChecker();
		List<AnalysisResult> duplicateResults = staticChecker.checkForDuplicateLabels(this.model);
		if (duplicateResults.stream().anyMatch(analysisResult -> analysisResult.getSeverity() == Severity.Error)) {
			staticCheckResults = duplicateResults;
		}
		else
		{
			modelBuilder.linkQuestions(this.model);
            staticCheckResults = staticChecker.validate(this.model, containsDuplicates);
		}

		if (staticCheckResults.stream().anyMatch(analysisResult -> analysisResult.getSeverity() == Severity.Error)) {
			ErrorRenderer errorRenderer = new JavafxErrorRenderer(stage);
			errorRenderer.render(staticCheckResults);
		}
		else
		{
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
	}

}
