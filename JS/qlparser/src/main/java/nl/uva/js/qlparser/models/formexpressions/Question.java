package nl.uva.js.qlparser.models.formexpressions;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.dataexpressions.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;

import java.util.ArrayList;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    @NonNull private DataType dataType;
    private DataExpression value; //TODO: Handle values

    @Override
    public ArrayList<Component> getComponents() {
        ArrayList<Component> components = new ArrayList<>();
        components.add(getQuestionnaireComponent());

        return components;
    }

    private Component getQuestionnaireComponent() {
        switch (dataType) {
            case BOOLEAN:
                return addQuestionTo(new CheckBox());
            case DATE:
                return addQuestionTo(new DateField());
            case DECIMAL:
            case INTEGER:
            case MONEY:
            case STRING:
            default:
                TextField textField = new TextField();
                textField.setPlaceholder(placeHolder());
                return addQuestionTo(textField);
        }
    }

    private Component addQuestionTo(Component component) {
        component.setId(name);
        component.setCaption(question);
        return component;
    }

    private String placeHolder() {
        switch (dataType) {
            case DECIMAL:
                return "0.0";
            case INTEGER:
                return "0";
            case MONEY:
                return "0,00";
            default:
                return "";
        }
    }

    @Override
    public void checkType() {
        NonNullRun.consumer(value, v -> {
            DataType inferredType = v.checkAndReturnType();
            if (inferredType != dataType) {
                throw new TypeMismatchException(dataType, inferredType);
            }
        });
    }
}
