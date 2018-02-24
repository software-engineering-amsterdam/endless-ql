package qlviz.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.QLBaseVisitor;
import qlviz.common.QuestionText;
import qlviz.gui.renderer.JavafxFormRenderer;
import qlviz.gui.renderer.JavafxQuestionBlockRenderer;
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
import qlviz.model.Question;
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
		this.renderer = new JavafxFormRenderer(stage, vbox -> new JavafxQuestionBlockRenderer());

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

	private void addFormFields(VBox taxFormFields, Iterator<Map.Entry<Integer, String>> checkBoxIterator,
			List<String> questionsInBlock) {
		while (checkBoxIterator.hasNext()) {
			CheckBox checkBoxLabel = new CheckBox(checkBoxIterator.next().getValue());
			checkBoxLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
			
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					if (checkBoxLabel.isSelected()) {
						 if (checkBoxLabel.getText().contains(QuestionText.SELL.toString().toLowerCase())) {
							new TextFieldWidget().addFields(questionsInBlock,taxFormFields);
						} 
					}
				}
			});
			taxFormFields.getChildren().add(checkBoxLabel);
		}
	}

	private List<String> getQuestions(HashMap<Integer, String> checkboxList, Form form) {
		for (QuestionBlock questionBlock : form.getQuestions()) {
			for (int j = 0; j < questionBlock.getQuestions().size(); j++) {
				checkboxList.put(j, form.getQuestions().get(0).getQuestions().get(j).getText());
			}
		}
		
		List<String> questionsInBlock = new ArrayList<String>();
		List<QuestionBlock> questionBlocks = form.getQuestions().get(1).getBlocks().get(0).getQuestionBlocks();
		for (int k = 0; k < questionBlocks.size(); k++) {
			for (Question conditionalBlockQuestions : questionBlocks.get(k).getQuestions()) {
				questionsInBlock.add(conditionalBlockQuestions.getText());
			}
		}
		return questionsInBlock;
	}

}