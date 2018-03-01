package org.uva.sea.ql.gui;

import com.sun.xml.internal.rngom.parse.host.Base;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.uva.sea.ql.gui.model.BaseQuestionRow;

public class GUIHelper {

    public static Label printComputedValue(BaseQuestionRow questionRow) {
        if (questionRow.isComputed()) {
            Label computedLabel = new Label();
            if (questionRow.getValue() != null) {
                computedLabel.setText(questionRow.displayValue());
                computedLabel.setMinWidth(100.0);
                return computedLabel;
            }
        }
        return new Label();
    }

    public static Label printLabel(BaseQuestionRow questionRow) {
        return new Label(questionRow.getLabel().replace("\"", ""));
    }
}
