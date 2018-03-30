package com.chariotit.uva.sc.qdsl.formbuilder;

import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.type.BooleanExpressionValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisibilityChecker {

    private HashMap<LineElement, FormQuestion> questions;
    private QLAstRoot astRoot;

    public VisibilityChecker(HashMap<LineElement, FormQuestion> questions, QLAstRoot astRoot) {
        this.questions = questions;
        this.astRoot = astRoot;
    }

    public void checkVisibility() {
        setAllInvisible();

        for (Form form : astRoot.getForms()) {
            check(form);
        }
    }

    private void check(Form form) {
        check(form.getFormElements());
    }

    private void check(List<FormElement> formElements) {
        for (FormElement formElement : formElements) {
            check(formElement);
        }
    }

    private void check(FormElement formElement) {
        if (formElement instanceof LineElement) {
            setVisible((LineElement)formElement);
        } else if (formElement instanceof IfBlock) {
            check((IfBlock)formElement);
        } else {
            throw new RuntimeException("Undefined FormElement type");
        }
    }


    private void check(IfBlock ifBlock) {
        if (((BooleanExpressionValue) ifBlock.getExpression().getExpressionValue()).getValue() &&
                ifBlock.getIfElements() != null) {
            check(ifBlock.getIfElements());
        } else if (ifBlock.getElseElements() != null) {
            check(ifBlock.getElseElements());
        }
    }

    private void setVisible(LineElement lineElement) {
        FormQuestion formQuestion = questions.get(lineElement);
        formQuestion.setVisible(true);
    }

    private void setAllInvisible() {
        for (Map.Entry<LineElement, FormQuestion> entry : questions.entrySet()) {
            entry.getValue().setVisible(false);
        }
    }
}
