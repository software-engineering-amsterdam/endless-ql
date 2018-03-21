package qls;

import ql.QLFormBuilder;
import ql.model.Form;
import qls.analysis.TypeCheckerTest;

public class QLSTestUtilities {

    public static void buildStyleSheet(String qlFile, String qlsFile) throws Exception {
        QLFormBuilder qlFormBuilder = new QLFormBuilder();
        Form form = qlFormBuilder.buildForm(TypeCheckerTest.class.getResourceAsStream(qlFile));

        QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(form, qlFormBuilder.getSymbolTable());
        qlsFormBuilder.parseStyleSheet(TypeCheckerTest.class.getResourceAsStream(qlsFile));
    }
}
