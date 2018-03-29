package qls;

import ql.QLEvaluator;
import qls.analysis.TypeCheckerTest;
import qls.model.StyleSheet;

public class QLSTestUtilities {

    public static StyleSheet buildStyleSheet(String qlFile, String qlsFile) throws Exception {
        QLEvaluator qlEvaluator = new QLEvaluator(QLSTestUtilities.class.getResourceAsStream(qlFile));
        QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(qlEvaluator.getForm());
        return qlsFormBuilder.parseStyleSheet(TypeCheckerTest.class.getResourceAsStream(qlsFile));
    }
}
