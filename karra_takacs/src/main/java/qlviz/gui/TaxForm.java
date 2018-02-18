package qlviz.gui;

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
		CheckBox buy = new CheckBox("Buy");
		CheckBox sell = new CheckBox("Sell");
		CheckBox loanType = new CheckBox("Loan Type");
		ParseBuilder parseBuilder = new ParseBuilder();
		ParseTree parseTree = parseBuilder.generateParseTree(this.getParameters().getRaw().get(0));
		QuestionVisitor questionVisitor = new QuestionVisitor(new QuestionTypeVisitor());

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
		Form form =  new FormVisitor(questionBlockVisitor).visit(parseTree);
		  for( QuestionBlock questionBlock : form.getQuestions()) {
			for(int j = 0 ;j <questionBlock.getQuestions().size();j++) {
			
			System.out.println(form.getQuestions().get(0).getQuestions().get(j).getText());
			}
		  }
		buy.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				System.out.println(buy.isSelected());
			}
		});

		sell.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				System.out.println(sell.isSelected());
			}
		});
		loanType.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				System.out.println(loanType.isSelected());
			}
		});
		taxFormFields.getChildren().addAll(buy, sell, loanType);
		Scene scene = new Scene(taxFormFields, 550, 250);
		stage.setTitle("Tax form");
		stage.setScene(scene);
		stage.show();
	}

}