package org.uva.sea.ql.gui.renderer;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import org.uva.sea.ql.exceptions.EvaluationException;
import org.uva.sea.ql.gui.model.*;
import org.uva.sea.ql.evaluate.valueTypes.*;

//TODO: Handle situation when newInput.getText().equals("")
public class JavafxRendererVisitor implements QuestionRenderer, QuestionModelVisitor {

    private final Pane parent;
    private final QuestionModel model;

    public JavafxRendererVisitor(Pane parent, QuestionModel model) {
        this.parent = parent;
        this.model = model;
    }

    @Override
    public void visit(BooleanQuestionModel question) {
        CheckBox checkBox = new CheckBox();
        if (question.getValue() != null) {
            checkBox.setSelected(question.getBasicValue());
        }
        checkBox.selectedProperty()
                .addListener((observable, oldIsFocused, newIsFocused) ->
                {
                    System.out.println("Checkbox set into " + newIsFocused + " " + question.getVariableName());
                    model.updateQuestion(question.getVariableName(), new BooleanValue(newIsFocused));
                });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), checkBox));
    }

    @Override
    public void visit(DateQuestionModel question) {
        TextField newInput = printTextField(question);
        newInput.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                model.updateQuestion(question.getVariableName(), new DateValue(newInput.getText()));
            }
        });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), newInput));
    }

    @Override
    public void visit(DecimalQuestionModel question) {
        TextField newInput = printTextField(question);
        newInput.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                model.updateQuestion(question.getVariableName(), new DecimalValue(newInput.getText()));
            }
        });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), newInput));
    }

    @Override
    public void visit(ErrorQuestionModel question) {
        alertError(question);
    }

    @Override
    public void visit(IntQuestionModel question) {
        TextField newInput = printTextField(question);
        newInput.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                model.updateQuestion(question.getVariableName(), new IntValue(newInput.getText()));
            }
        });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), newInput));
    }

    @Override
    public void visit(MoneyQuestionModel question) {
        TextField newInput = printTextField(question);
        newInput.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                try {
                    model.updateQuestion(question.getVariableName(), new MoneyValue(newInput.getText()));
                } catch (EvaluationException e) {
                    e.printStackTrace();
                }
            }
        });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), newInput));
    }

    @Override
    public void visit(StringQuestionModel question) {
        TextField newInput = printTextField(question);
        newInput.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                model.updateQuestion(question.getVariableName(), new StringValue(newInput.getText()));
            }
        });
        parent.getChildren().add(createQuestionRow(printLabel(question.getLabel()), newInput));
    }

    @Override
    public void render(BaseQuestionModel questionRow) {
        questionRow.accept(this);
    }

    private Node createQuestionRow(Label label, Control input) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);
        wrapper.add(input, 1, 0);

        return wrapper;
    }

    private Label printLabel(String string) {
        return new Label(string.replace("\"", ""));
    }

    private TextField printTextField(BaseQuestionModel question) {
        TextField textField = new TextField();
        if (question.getValue() != null) {
            textField.setText(question.displayValue());
        }
        textField.setEditable(true);
        textField.setMinWidth(100.0);

        if (question.isComputed()) {
            textField.setEditable(false);
        }

        return textField;
    }

    private Label printComputedValue(BaseQuestionModel question) {
        Label computedLabel = new Label();
        if (question.getValue() != null) {
            computedLabel.setText(question.displayValue());
            computedLabel.setMinWidth(100.0);
            return computedLabel;
        }
        return new Label();
    }

    private void alertError(ErrorQuestionModel error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Alert");
        String s = error.displayValue();
        alert.setContentText(s);
        alert.show();

    }
}
