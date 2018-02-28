package nl.uva.js.qlparser.models.formexpressions;

import com.vaadin.ui.AbstractField;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.dataexpressions.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.exceptions.TypeMismatchException;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class IfBlock implements FormExpression {
    @NonNull private DataExpression condition;
    private LinkedList<FormExpression> expressions;

    @Override
    public List<AbstractField> getComponents() {
        LinkedList<AbstractField> components = new LinkedList<>();

        expressions.stream()
                .map(FormExpression::getComponents)
                .forEach(components::addAll);

        for (AbstractField component : components) {
            component.setVisible(evaluateCondition());
        }

        return components;
    }

    private boolean evaluateCondition() {
        return false; // TODO 
    }

    @Override
    public void checkType() {
        DataType conditionType = condition.checkAndReturnType();
        if (conditionType != DataType.BOOLEAN)
            throw new TypeMismatchException(DataType.BOOLEAN, conditionType);

        expressions.forEach(TypeCheckable::checkType);
    }
}
