package org.uva.sea.gui;

import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.QlSEvaluator;

public class EvaluatorFactory {
    public static BaseEvaluator getEvaluator(String qlFile, String qlsFile) {
        if (qlsFile == null) {
            return new QlEvaluator(qlFile);
        } else {
            return new QlSEvaluator(qlFile, qlsFile);
        }
    }
}
