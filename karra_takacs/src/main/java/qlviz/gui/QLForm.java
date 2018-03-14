package qlviz.gui;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import qlviz.QLBaseVisitor;
import qlviz.QLSBaseVisitor;
import qlviz.gui.renderer.ErrorRenderer;
import qlviz.gui.renderer.JavafxErrorRenderer;
import qlviz.gui.renderer.QuestionRenderer;
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
import qlviz.interpreter.QuestionVisitor;
import qlviz.interpreter.linker.QuestionLinkerImpl;
import qlviz.interpreter.style.*;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.PropertySetting;
import qlviz.model.style.Stylesheet;
import qlviz.model.style.Widget;
import qlviz.typecheker.AnalysisResult;
import qlviz.typecheker.Severity;
import qlviz.typecheker.StaticChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QLForm extends Application {
	private JavafxFormRenderer renderer;
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
		QLSBaseVisitor<PropertySetting> propertySettingVisitor = new QLSBaseVisitor<>();
		QLSBaseVisitor<Widget> widgetVisitor = new WidgetVisitor(
				new WidgetTypeTranslator(),
				new ParameterVisitor());
		QuestionTypeTranslator questionTypeTranslator = new QuestionTypeVisitor();
		QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor =
				new DefaultWidgetVisitor(propertySettingVisitor, widgetVisitor, questionTypeTranslator);
		QLSBaseVisitor<Stylesheet> stylesheetVisitor = new StylesheetVisitor(
				new PageVisitor(
						new SectionVisitor(
								new qlviz.interpreter.style.QuestionVisitor(
										new WidgetVisitor(
												new WidgetTypeTranslator(),
												new ParameterVisitor()
										)
								),
								defaultWidgetVisitor
						),
						defaultWidgetVisitor)
		);
		StyleModelBuilder styleBuilder = new StyleModelBuilder(stylesheetVisitor);


		if (this.getParameters().getRaw().size() > 1) {
			Stylesheet stylesheet = styleBuilder.createFromMarkup(this.getParameters().getRaw().get(1));
			WidgetFinder widgetFinder = new ChainedWidgetFinder(List.of(
					new StylesheetWidgetFinder(new NaiveQuestionLocator(stylesheet)),
					new DefaultWidgetProvider()
			));
			JavafxWidgetFactory javafxWidgetFactory = new JavafxWidgetFactory();

			Function<Pane, QuestionRenderer> questionRendererFactory =
					pane -> new StyledJavafxQuestionRenderer(pane, javafxWidgetFactory, widgetFinder);
			this.renderer = new StyledJavafxFormRenderer(stage, questionRendererFactory, stylesheet, new NaiveQuestionLocator(stylesheet));
		}
		else {
			this.renderer = new JavafxFormRenderer(stage, JavafxQuestionRenderer::new);
		}


		NumericExpressionParser numericExpressionParser = new NumericExpressionParser(new BinaryNumericOperatorVisitor());
		QLBaseVisitor<BooleanExpression> booleanExpressionVisitor =
				new BooleanExpressionParser(
					new NumericExpressionParser(
							new BinaryNumericOperatorVisitor()
					),
					new BinaryBooleanOperatorVisitor(),
					new NumericComparisonOperatorVisitor());
		QLBaseVisitor<QuestionBlock> questionBlockVisitor =
				new QuestionBlockVisitor(
						new QuestionVisitor(
								new QuestionTypeVisitor(),
								numericExpressionParser,
								new BooleanExpressionParser(
										numericExpressionParser,
										new BinaryBooleanOperatorVisitor(),
										new NumericComparisonOperatorVisitor())
						),
						pQuestionBlockVisitor -> new ConditionalBlockVisitor(booleanExpressionVisitor, pQuestionBlockVisitor)
				);
		List<AnalysisResult> staticCheckResults = new ArrayList<>();
		FormVisitor visitor = new FormVisitor(questionBlockVisitor);
		ModelBuilder modelBuilder = new ModelBuilder(visitor, new QuestionLinkerImpl(new TypedQuestionWalker()));

		this.model = modelBuilder.createFormFromMarkup(this.getParameters().getRaw().get(0));


		StaticChecker staticChecker = new StaticChecker();
		List<AnalysisResult> duplicateResults = staticChecker.checkForDuplicateLables(this.model);
		if (duplicateResults.stream().anyMatch(analysisResult -> analysisResult.getSeverity() == Severity.Error)) {
			staticCheckResults = duplicateResults;
		}
		else
		{
			modelBuilder.linkQuestions(this.model);
            staticCheckResults = staticChecker.valdiate(this.model, containsDuplicates);
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