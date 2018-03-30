package com.chariotit.uva.sc.qdsl.formbuilder;

import com.chariotit.uva.sc.qdsl.ast.ql.node.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public class FormQuestionMapBuilder {
    private HashMap<LineElement, FormQuestion> map = new HashMap<>();
    private QLAstRoot astRoot;
    private QLFormBuilder formBuilder;


    private FormQuestionMapBuilder(QLAstRoot astRoot, QLFormBuilder formBuilder) {
        this.astRoot = astRoot;
        this.formBuilder = formBuilder;
    }

    public static HashMap<LineElement, FormQuestion> buildMap(QLAstRoot astRoot, QLFormBuilder
            formBuilder) {
        FormQuestionMapBuilder builder = new FormQuestionMapBuilder(astRoot, formBuilder);

        return builder.buildMap();

    }

    private HashMap<LineElement, FormQuestion> buildMap() {
        for (Form form : astRoot.getForms()) {
            build(form);
        }

        return map;
    }

    private void build(Form form) {
        build(form.getFormElements());
    }

    private void build(List<FormElement> formElements) {
        for (FormElement formElement : formElements) {
            build(formElement);
        }
    }

    private void build(FormElement formElement) {
        if (formElement instanceof LineElement) {
            addQuestion((LineElement)formElement);
        } else if (formElement instanceof IfBlock) {
            build((IfBlock)formElement);
        } else {
            throw new RuntimeException("Undefined FormElement type");
        }
    }

    private void build(IfBlock ifBlock) {
        if (ifBlock.getIfElements() != null) {
            build(ifBlock.getIfElements());
        }

        if (ifBlock.getElseElements() != null) {
            build(ifBlock.getElseElements());
        }
    }

    private void addQuestion(LineElement lineElement) {
        JComponent jComponent = formBuilder.componentForElement(lineElement);
        JLabel jLabel = new JLabel(lineElement.getQuestion().getQuestion());
        FormQuestion formQuestion = new FormQuestion(jLabel, jComponent);
        map.put(lineElement, formQuestion);
    }
}
