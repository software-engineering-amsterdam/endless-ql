package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.expressions.data.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;

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
    public List<String> getComponents() {
        return Collections.singletonList(getQuestionnaireComponent());
    }

    private String getQuestionnaireComponent() {
        String component = dataType.getComponent().get();

// TODO
//        if (component instanceof TextField)
//            NonNullRun.consumer(value, v -> component.setValue(v.value().toString()));

        return component;
    }

    @Override
    public void checkType() {
        NonNullRun.consumer(value, v -> {
            DataType inferredType = v.checkAndReturnType();
            if (inferredType != dataType)
                throw new TypeMismatchException(dataType, inferredType);
        });
    }
}
