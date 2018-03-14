package nl.uva.js.qlparser.ui.components.form;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.expressions.data.Value;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;

public class ComponentBuilder {

    private static <T extends JTextField> T attachTextFieldListeners(T textField, Variable variable) {

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value -> textField.setText(value.value().toString()));

//        Listen to field changes and update the variable accordingly
        textField.addKeyListener(new TextFieldListener(textField, variable, (e) -> {
            textField.setCaretColor(Color.RED);
        }));

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                textField.setText(variable.value().toString());
        });

        return textField;
    }

    public static JFormattedTextField buildMoneyField(Variable variable) {
        JFormattedTextField formattedTextField = new JFormattedTextField();

        attachTextFieldListeners(formattedTextField, variable);

        return formattedTextField;
    }

    public static JFormattedTextField buildDateField(Variable variable) {
        JFormattedTextField formattedTextField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));

        attachTextFieldListeners(formattedTextField, variable);

        return formattedTextField;
    }

    public static JTextField buildTextField(Variable variable) {
        JTextField textField = new JTextField(1);

        attachTextFieldListeners(textField, variable);

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
    private static class TextFieldListener implements KeyListener {
        @NonNull private JTextField textField;
        @NonNull private Variable variable;
        @NonNull private Consumer<Exception> exceptionConsumer;

        @Override
        public void keyTyped(KeyEvent e) {
            try {
                textField.setCaretColor(Color.BLACK);
                variable.setValue(
                        Value.builder()
                                .dataType(variable.getDataType())
                                .value(variable.getDataType().getValueOf().apply(textField.getText()))
                                .build());
            } catch (Exception ex) {
                exceptionConsumer.accept(ex);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyTyped(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyTyped(e);
        }
    }
}
