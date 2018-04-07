package ql.gui.model;

import ql.gui.view.ErrorMessageView;
import ql.gui.view.WindowView;

public final class ValidatorsHandler {

    public static boolean execute(WindowView windowView, ql.logic.validators.Validator[] validators) {
        // validating
        for (ql.logic.validators.Validator validator : validators) {
            if (!validator.validate()) {
                if (validator.criticalErrorOccurred()) {
                    ErrorMessageView.showErrorDialog(windowView, "Validation error", validator.getMessage());
                    return false;
                } else {
                    ErrorMessageView.showWarningDialog(windowView, "Validation warning", validator.getMessage());
                }
            }
        }
        return true;
    }

}
