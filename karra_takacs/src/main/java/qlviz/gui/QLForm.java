package qlviz.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import qlviz.QLBaseVisitor;
import qlviz.gui.renderer.javafx.JavafxConditionalBlockRenderer;
import qlviz.gui.renderer.javafx.JavafxFormRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionBlockRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionRenderer;
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
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;

public class QLForm extends Application {
	private JavafxFormRenderer renderer;
	private Form model;

	// Example to add checkboxes to the form

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
		this.renderer = new JavafxFormRenderer(stage,
				vbox -> new JavafxQuestionBlockRenderer(vbox, JavafxQuestionRenderer::new,
						pane -> new JavafxConditionalBlockRenderer(pane,
								(pane1, conditionalBlockRenderer) -> new JavafxQuestionBlockRenderer(
										pane1,
										JavafxQuestionRenderer::new,
										pane2 -> conditionalBlockRenderer))));

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
								new NumericExpressionParser(new BinaryNumericOperatorVisitor())
						),
						pQuestionBlockVisitor -> new ConditionalBlockVisitor(booleanExpressionVisitor, pQuestionBlockVisitor)
				);
		FormVisitor visitor = new FormVisitor(questionBlockVisitor);
		this.model = new ModelBuilder(visitor, new QuestionLinkerImpl(new TypedQuestionWalker()))
				.createFormFromMarkup(this.getParameters().getRaw().get(0));

		NumericExpressionViewModelFactory numericExpressionViewModelFactory = new NumericExpressionViewModelFactoryImpl();
		BooleanExpressionViewModelFactory booleanExpressionFactory = new BooleanExpressionViewModelFactoryImpl(numericExpressionViewModelFactory);
		QuestionViewModelFactoryImpl questionViewModelFactory =
				new QuestionViewModelFactoryImpl(numericExpressionViewModelFactory::create);
		QuestionBlockViewModelFactory questionBlockViewModelFactory =
				new QuestionBlockViewModelFactory(questionViewModelFactory::create, booleanExpressionFactory::create);
		FormViewModel viewModel = new FormViewModelImpl(model, renderer, questionBlockViewModelFactory::create);

		QuestionViewModelLinker viewModelLinker = new QuestionViewModelLinkerImpl(new QuestionViewModelCollectorImpl());
		viewModelLinker.linkQuestionStubs(viewModel);
		this.renderer.render(viewModel);
	}

}