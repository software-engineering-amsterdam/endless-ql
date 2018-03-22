package nl.uva.js.qlparser.models.ql.expressions.form;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.ui.components.form.ComponentBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String question;
    @NonNull private DataType dataType;
    @NonNull private Variable variable;

    @Override
    public List<Component> getComponents() {
        Panel panel = ComponentBuilder.getComponentPanel();

        GridLayout layout = new GridLayout(1,2);

        layout.setHgap(10);
        panel.setLayout(layout);

        JComponent component = dataType.getComponent().apply(variable);

        JLabel label = new JLabel(question, JLabel.LEFT);

        panel.add(label);
        panel.add(component);
        panel.setName(variable.getName());

        return Collections.singletonList(panel);
    }

    @Override
    public void checkType() {
        variable.returnCheckedType();
    }

    @Override
    @JsonValue
    public List<Map<String, Object>> getJsonRepresentation() {
        Map<String, Object> jsonInformation = new HashMap<>();
        jsonInformation.put("question", question);
        jsonInformation.put("answer", variable);

        return Collections.singletonList(jsonInformation);
    }
}
