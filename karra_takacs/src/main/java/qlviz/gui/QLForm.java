package qlviz.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.QLBaseVisitor;
import qlviz.common.QuestionText;
import qlviz.gui.renderer.javafx.JavafxFormRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionBlockRenderer;
import qlviz.gui.renderer.javafx.JavafxQuestionRenderer;
import qlviz.interpreter.BinaryBooleanOperatorVisitor;
import qlviz.interpreter.BinaryNumericOperatorVisitor;
import qlviz.interpreter.BooleanExpressionVisitor;
import qlviz.interpreter.ConditionalBlockVisitor;
import qlviz.interpreter.FormVisitor;
import qlviz.interpreter.NumericComparisonOperatorVisitor;
import qlviz.interpreter.NumericExpressionVisitor;
import qlviz.interpreter.QuestionBlockVisitor;
import qlviz.interpreter.QuestionTypeVisitor;
import qlviz.interpreter.QuestionVisitor;
import qlviz.model.BooleanExpression;
import qlviz.model.Form;
import qlviz.model.question.Question;
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
				new BooleanExpressionVisitor(
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

		this.renderer.render(this.model);
	}

}