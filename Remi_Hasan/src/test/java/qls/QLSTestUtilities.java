package qls;

import ql.QLFormBuilder;
import ql.model.Form;
import qls.analysis.TypeCheckerTest;
import qls.model.StyleSheet;

public class QLSTestUtilities {

    public static StyleSheet buildStyleSheet(String qlFile, String qlsFile) throws Exception {
        QLFormBuilder qlFormBuilder = new QLFormBuilder();
        Form form = qlFormBuilder.buildForm(TypeCheckerTest.class.getResourceAsStream(qlFile));

        QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(form, qlFormBuilder.getSymbolTable());
        return qlsFormBuilder.parseStyleSheet(TypeCheckerTest.class.getResourceAsStream(qlsFile));
    }
}
