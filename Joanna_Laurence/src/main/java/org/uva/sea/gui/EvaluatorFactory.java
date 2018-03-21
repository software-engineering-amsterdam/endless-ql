package org.uva.sea.gui;

import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.QlSEvaluator;

public final class EvaluatorFactory {
    public static BaseEvaluator getEvaluator(String qlFile, String qlsFile) {
        return (qlsFile == null) ? new QlEvaluator(qlFile) : new QlSEvaluator(qlFile, qlsFile);
    }
}
