package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.expressions.data.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class IfBlock implements FormExpression {
    @NonNull private DataExpression condition;
    private LinkedList<FormExpression> expressions;

    @Override
    public List<Component> getComponents() {
        LinkedList<Component> components = new LinkedList<>();

        expressions.stream()
                .map(FormExpression::getComponents)
                .forEach(components::addAll);

//        return components;
        return Collections.emptyList();
    }

    @Override
    public void checkType() {
        DataType conditionType = condition.checkAndReturnType();
        if (conditionType != DataType.BOOLEAN)
            throw new TypeMismatchException(DataType.BOOLEAN, conditionType);

        expressions.forEach(TypeCheckable::checkType);
    }
}
