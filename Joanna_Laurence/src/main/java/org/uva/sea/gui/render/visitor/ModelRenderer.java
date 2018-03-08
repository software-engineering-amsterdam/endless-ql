package org.uva.sea.gui.render.visitor;

import org.uva.sea.gui.model.*;
import org.uva.sea.gui.render.ViewRenderer;

public class ModelRenderer implements QuestionModelVisitor {

    private ViewRenderer viewRenderer;

    public ModelRenderer(ViewRenderer viewRenderer) {
        this.viewRenderer = viewRenderer;
    }

    @Override
    public Void visit(BooleanQuestionModel question) {
        this.viewRenderer.drawBooleanQuestionRow(question);
        return null;
    }

    @Override
    public Void visit(DateQuestionModel question) {
        this.viewRenderer.drawQuestionRow(question);
        return null;
    }

    @Override
    public Void visit(DecimalQuestionModel question) {
        this.viewRenderer.drawQuestionRow(question);
        return null;
    }

    @Override
    public Void visit(ErrorQuestionModel question) {
        this.viewRenderer.displayError(question.displayValue());
        return null;
    }

    @Override
    public Void visit(IntQuestionModel question) {
        this.viewRenderer.drawQuestionRow(question);
        return null;
    }

    @Override
    public Void visit(MoneyQuestionModel question) {
        this.viewRenderer.drawQuestionRow(question);
        return null;
    }

    @Override
    public Void visit(StringQuestionModel question) {
        this.viewRenderer.drawQuestionRow(question);
        return null;
    }
}
