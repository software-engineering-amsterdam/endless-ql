package nl.uva.js.qlparser.models.ql.expressions.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String question;
    @JsonIgnore @NonNull private DataType dataType;
    @NonNull private Variable variable;

    @Override
    @JsonIgnore
    public List<Component> getComponents() {
        Panel panel = ComponentBuilder.getComponentPanel();

        GridLayout layout = new GridLayout(1,2);

        layout.setHgap(10);
        panel.setLayout(layout);

        JComponent component = dataType.getComponent().apply(variable);
        component.setName(variable.getName());

        JLabel label = new JLabel(question, JLabel.LEFT);

        panel.add(label);
        panel.add(component);

        return Collections.singletonList(panel);
    }

    @Override
    public void checkType() {
        variable.returnCheckedType();
    }
}
