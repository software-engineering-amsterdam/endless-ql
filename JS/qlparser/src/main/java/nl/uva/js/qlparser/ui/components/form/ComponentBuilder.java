package nl.uva.js.qlparser.ui.components.form;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.expressions.data.Value;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;
import nl.uva.js.qlparser.wrappers.arithmetic.CalculatableInteger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.function.Consumer;

import static nl.uva.js.qlparser.ui.GUIBuilder.FORM_WIDTH;

public class ComponentBuilder {

    public static final String YES = "Yes";
    public static final String NO = "No";

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
                checkBox.setSelected(((Boolean) newValue.value()));
        });

        return checkBox;
    }

    public static Component buildSectionHeader(String name) {
        Panel panel = buildComponentPanel();
        Label label = new Label(name);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panel.add(label);

        return panel;
    }

    public static Panel buildComponentPanel() {
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(FORM_WIDTH - 100, 40));
        return panel;
    }

    public static JPanel buildRadioButtons(Variable variable) {
//        Listen to field changes and update the variable accordingly
        ActionListener radioActionListener = event -> variable.setValue(Value.builder()
                .dataType(variable.getDataType())
                .value(event.getActionCommand().equals(YES))
                .build());

        JRadioButton buttonYes   = new JRadioButton(YES);
        buttonYes.setActionCommand(YES);
        buttonYes.addActionListener(radioActionListener);

        JRadioButton buttonNo    = new JRadioButton(NO);
        buttonNo.setActionCommand(NO);
        buttonNo.addActionListener(radioActionListener);

        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(buttonYes);
        radioButtons.add(buttonNo);

//        Set value if there is any present
        updateRadioValue(variable, buttonYes, buttonNo);

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                updateRadioValue(variable, buttonYes, buttonNo);
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(300, 20));
        panel.add(buttonYes);
        panel.add(buttonNo);
        return panel;
    }

    private static void updateRadioValue(Variable variable, JRadioButton buttonYes, JRadioButton buttonNo) {
        NonNullRun.consumer(variable.getValue(), value -> buttonYes.setSelected(true == (Boolean) value.value()));
        NonNullRun.consumer(variable.getValue(), value -> buttonNo.setSelected(false == (Boolean) value.value()));
    }

    public static JComboBox buildDropdown(Variable variable) {
        JComboBox<String> dropdown = new JComboBox<>();
        dropdown.addItem(YES);
        dropdown.addItem(NO);

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value ->
                dropdown.setSelectedItem(((Boolean) value.value()) ? YES : NO));

//        Listen to field changes and update the variable accordingly
        dropdown.addActionListener(event -> {
            variable.setValue(Value.builder()
                    .dataType(variable.getDataType())
                    .value(Objects.equals(dropdown.getSelectedItem(), YES))
                    .build());
        });

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                dropdown.setSelectedItem(((Boolean) newValue.value()) ? YES : NO);
        });

        return dropdown;
    }

    public static JSlider buildSlider(Variable variable) {
        final int min  = 0;
        final int max  = 100;
        final int init = 0;

        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value -> slider.setValue(((CalculatableInteger)value.value()).get()));

//        Listen to field changes and update the variable accordingly
        slider.addChangeListener(event -> {
            variable.setValue(Value.builder()
                    .dataType(variable.getDataType())
                    .value(slider.getValue())
                    .build());
        });

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                slider.setValue((Integer) newValue.value());
        });

        return slider;
    }

    public static JSpinner buildSpinbox(Variable variable) {
        final int min  = 0;
        final int max  = 100;
        final int init = 0;

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(init, min, max, 1);
        JSpinner spinner = new JSpinner(spinnerNumberModel);

//        Set value if there is any present
        NonNullRun.consumer(variable.getValue(), value -> spinner.setValue(((CalculatableInteger)value.value()).get()));

//        Listen to field changes and update the variable accordingly
        spinner.addChangeListener(event -> {
            variable.setValue(Value.builder()
                    .dataType(variable.getDataType())
                    .value(spinner.getValue())
                    .build());
        });

//        Listen to external changes
        variable.addChangeListener(newValue -> {
            if (!newValue.getName().equals(variable.getName()))
                spinner.setValue(newValue.value());
        });

        return spinner;
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
