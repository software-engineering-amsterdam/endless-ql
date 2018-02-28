package nl.uva.js.qlparser.models.formexpressions;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.dataexpressions.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    @NonNull private DataType dataType;
    private DataExpression value;

    @Override
    public List<Component> getComponents() {
        return Collections.singletonList(getQuestionnaireComponent());
    }

    private Component getQuestionnaireComponent() {
        Component component = dataType.getComponent().get();

        if (component instanceof TextField) {
            NonNullRun.consumer(value, v -> ((TextField) component).setValue(v.value().toString()));
        }

        return addQuestionTo(component);
    }

    private Component addQuestionTo(Component component) {
        component.setId(name);
        component.setCaption(question);
        return component;
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
