package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.expressions.data.Variable;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String question;
    @NonNull private DataType dataType;
    @NonNull private Variable variable;

    @Override
    public List<Component> getComponents() {
        Panel panel = new Panel();
        GridLayout layout = new GridLayout(1,2);

        layout.setHgap(10);
        panel.setLayout(layout);

        JComponent component = dataType.getComponent().apply(variable);

        JLabel label = new JLabel(question, JLabel.LEFT);

        panel.add(label);
        panel.add(component);
        panel.setSize(new Dimension(600, 40));
        panel.setPreferredSize(new Dimension(600, 40));
        panel.setMaximumSize(new Dimension(600, 40));
        panel.setMinimumSize(new Dimension(600, 40));

        return Collections.singletonList(panel);
    }

    @Override
    public void checkType() {
        variable.checkAndReturnType();
    }
}
