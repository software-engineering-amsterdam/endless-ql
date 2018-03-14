package nl.uva.js.qlparser.ui.components.form;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.expressions.data.Value;
import nl.uva.js.qlparser.models.expressions.data.Variable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ComponentBuilder {

    public static JTextField buildTextField(Variable variable) {
        JTextField textField = new JTextField(1);

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value -> textField.setText(value.value().toString()));

//        Listen to field changes and update the variable accordingly
        textField.getDocument().addDocumentListener(new TextChangeListener(textField, variable));

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                textField.setText(variable.value().toString());
        });

        return textField;
    }

    public static JCheckBox buildCheckBox(Variable variable) {
        JCheckBox checkBox = new JCheckBox();

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value -> checkBox.setSelected(((Boolean) value.value())));

//        Listen to field changes and update the variable accordingly
        checkBox.addActionListener(event -> {
            variable.setValue(Value.builder()
                .dataType(variable.getDataType())
                .value(checkBox.isSelected())
                .build());
        });

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                checkBox.setSelected(((Boolean) variable.value()));
        });

        return checkBox;
    }

    @RequiredArgsConstructor
    private static class TextChangeListener implements DocumentListener {
        @NonNull private JTextField textField;
        @NonNull private Variable variable;

        private void updateValue() {
            if (variable.getValue() instanceof Value)
                variable.setValue(
                        Value.builder()
                            .dataType(variable.getDataType())
                            .value(variable.getDataType().getValueOf().apply(textField.getText()))
                            .build());
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            updateValue();
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            if (!textField.getText().isEmpty()) updateValue();
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) { updateValue(); }
    }
}
