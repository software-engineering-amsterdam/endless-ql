package qls;

import ql.QLForm;
import qls.analysis.TypeCheckerTest;
import qls.model.StyleSheet;

public class QLSTestUtilities {

    public static StyleSheet buildStyleSheet(String qlFile, String qlsFile) throws Exception {
        QLForm qlForm = new QLForm(QLSTestUtilities.class.getResourceAsStream(qlFile));
        QLSBuilder qlsBuilder = new QLSBuilder(qlForm.getForm());
        return qlsBuilder.parseStyleSheet(TypeCheckerTest.class.getResourceAsStream(qlsFile));
    }
}
