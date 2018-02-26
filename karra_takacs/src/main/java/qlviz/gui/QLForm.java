package qlviz.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import qlviz.QLBaseVisitor;
import qlviz.gui.renderer.javafx.JavafxFormRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionBlockRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionRenderer;
import qlviz.gui.viewModel.*;
import qlviz.interpreter.BinaryBooleanOperatorVisitor;
import qlviz.interpreter.BinaryNumericOperatorVisitor;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.ConditionalBlockVisitor;
import qlviz.interpreter.FormVisitor;
import qlviz.interpreter.NumericComparisonOperatorVisitor;
import qlviz.interpreter.NumericExpressionVisitor;
import qlviz.interpreter.QuestionBlockVisitor;
import qlviz.interpreter.QuestionTypeVisitor;
import qlviz.interpreter.QuestionVisitor;
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
		this.renderer = new JavafxFormRenderer(stage, vbox -> new JavafxQuestionBlockRenderer(vbox, JavafxQuestionRenderer::new));

		QLBaseVisitor<BooleanExpression> booleanExpressionVisitor =
				new qlviz.interpreter.BooleanExpressionVisitor(
					new NumericExpressionVisitor(
							new BinaryNumericOperatorVisitor()
					),
					new BinaryBooleanOperatorVisitor(),
					new NumericComparisonOperatorVisitor());
		QLBaseVisitor<QuestionBlock> questionBlockVisitor =
				new QuestionBlockVisitor(
						new QuestionVisitor(
								new QuestionTypeVisitor()
						),
						pQuestionBlockVisitor -> new ConditionalBlockVisitor(booleanExpressionVisitor, pQuestionBlockVisitor)
				);
		FormVisitor visitor = new FormVisitor(questionBlockVisitor);
		this.model = new ModelBuilder(visitor).createFormFromMarkup(this.getParameters().getRaw().get(0));

		QuestionViewModelFactoryImpl questionViewModelFactory = new QuestionViewModelFactoryImpl();
		QuestionBlockViewModelFactory questionBlockViewModelFactory = new QuestionBlockViewModelFactory(questionViewModelFactory::create);
		FormViewModel viewModel = new FormViewModelImpl(model, renderer, questionBlockViewModelFactory::create);
		this.renderer.render(viewModel);
	}

}