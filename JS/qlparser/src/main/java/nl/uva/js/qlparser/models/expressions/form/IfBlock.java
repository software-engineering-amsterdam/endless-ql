package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.expressions.data.DataExpression;

import javax.swing.*;
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
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

//        A value can either be true, false or null. As null does not represent false in Java, this has to be checked.
        panel.setVisible(Boolean.TRUE.equals(condition.value()));

        condition.addChangeListener(event -> panel.setVisible(Boolean.TRUE.equals(condition.value())));
        
        expressions.stream()
                .map(FormExpression::getComponents)
                .flatMap(List::stream)
                .forEach(panel::add);

        return Collections.singletonList(panel);
    }

    @Override
    public void checkType() {
        DataType conditionType = condition.returnCheckedType();
        if (conditionType != DataType.BOOLEAN)
            throw new TypeMismatchException(DataType.BOOLEAN, conditionType);

        expressions.forEach(TypeCheckable::checkType);
    }
}
