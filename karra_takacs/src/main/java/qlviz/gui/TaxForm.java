package qlviz.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qlviz.ParseBuilder;
import qlviz.QLBaseVisitor;
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
import qlviz.model.QuestionBlock;

public class TaxForm extends Application {

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

		VBox taxFormFields = new VBox();
		addFormFields(taxFormFields);
		Scene scene = new Scene(taxFormFields, 550, 250);
		stage.setTitle("Tax form");
		stage.setScene(scene);
		stage.show();
	}

	// adds form fields
	private void addFormFields(VBox taxFormFields) {
		HashMap<Integer, String> checkboxList = getQuestions();
		Iterator<Map.Entry<Integer, String>> checkBoxIterator = checkboxList.entrySet().iterator();
		while (checkBoxIterator.hasNext()) {
			CheckBox checkBoxLabel = new CheckBox(checkBoxIterator.next().getValue());
			checkBoxLabel.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					System.out.println(checkBoxLabel.isSelected());
				}
			});
			taxFormFields.getChildren().add(checkBoxLabel);
		}
	}

	// get questions from the input file
	private HashMap<Integer, String> getQuestions() {
		ParseBuilder parseBuilder = new ParseBuilder();
		ParseTree parseTree = parseBuilder.generateParseTree(this.getParameters().getRaw().get(0));
		QLBaseVisitor<BooleanExpression> booleanExpressionVisitor = new BooleanExpressionVisitor(
				new NumericExpressionVisitor(new BinaryNumericOperatorVisitor()), new BinaryBooleanOperatorVisitor(),
				new NumericComparisonOperatorVisitor());
		QLBaseVisitor<QuestionBlock> questionBlockVisitor = new QuestionBlockVisitor(
				new QuestionVisitor(new QuestionTypeVisitor()),
				pQuestionBlockVisitor -> new ConditionalBlockVisitor(booleanExpressionVisitor, pQuestionBlockVisitor));
		Form form = new FormVisitor(questionBlockVisitor).visit(parseTree);
		HashMap<Integer, String> checkboxList = new HashMap<Integer, String>();
		for (QuestionBlock questionBlock : form.getQuestions()) {
			for (int j = 0; j < questionBlock.getQuestions().size(); j++) {
				checkboxList.put(j, form.getQuestions().get(0).getQuestions().get(j).getText());
				System.out.println(form.getQuestions().get(0).getQuestions().get(j).getText());
			}
		}
		return checkboxList;
	}

}