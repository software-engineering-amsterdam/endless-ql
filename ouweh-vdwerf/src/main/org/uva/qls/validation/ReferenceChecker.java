package org.uva.qls.validation;

import org.uva.ql.validation.Checker;
import org.uva.qls.ast.Segment.Question;
import org.uva.qls.ast.Stylesheet;
import org.uva.ql.ast.Form;

import java.util.List;

class ReferenceChecker extends Checker {

    private Form form;
    private Stylesheet stylesheet;

    public ReferenceChecker(Form form, Stylesheet styleSheet) {
        this.form = form;
        this.stylesheet = styleSheet;

    }
    @Override
    public void runCheck() {
        List<Question> qlsQuestions = stylesheet.getQuestions();



    }
}
