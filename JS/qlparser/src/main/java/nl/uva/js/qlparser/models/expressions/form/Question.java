package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.expressions.data.DataExpression;
import nl.uva.js.qlparser.models.expressions.data.Variable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    @NonNull private DataType dataType;
    @NonNull private Variable variable;

    @Override
    public List<Component> getComponents() {
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(1,2));

        JComponent component = dataType.getComponent().get();

        if (component instanceof JTextField)
            ((JTextField) component).getDocument().addDocumentListener(new TextChangeListener((JTextField) component));
        else if (component instanceof JCheckBox)
            ((JCheckBox) component).addItemListener(e -> variable.setValue(e.getStateChange() == ItemEvent.SELECTED));

        panel.add(new JLabel(question, JLabel.TRAILING));
        panel.add(component);

        return Collections.singletonList(panel);
    }

    @Override
    public void checkType() {
        variable.checkAndReturnType();
    }

    @RequiredArgsConstructor
    class TextChangeListener implements DocumentListener {
        @NonNull private JTextField textField;

        private void updateValue() {
            variable.setValue(variable.getDataType().getValueOf().apply(textField.getText()));
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            updateValue();
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            updateValue();
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {

        }
    }
}
