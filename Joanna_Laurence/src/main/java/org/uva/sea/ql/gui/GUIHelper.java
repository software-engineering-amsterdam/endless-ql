package org.uva.sea.ql.gui;

import javafx.scene.control.Label;

public class GUIHelper {

    public static Label printComputedValue(QuestionGUI questionGUI) {
        if (questionGUI.isComputed()) {
            Label computedLabel = new Label();
            if (questionGUI.getValue() != null) {
                computedLabel.setText(questionGUI.displayValue());
                computedLabel.setMinWidth(100.0);
                return computedLabel;
            }
        }
        return new Label();
    }

    public static Label printLabel(QuestionGUI questionGUI) {
        return new Label(questionGUI.getLabel().replace("\"", ""));
    }
}
