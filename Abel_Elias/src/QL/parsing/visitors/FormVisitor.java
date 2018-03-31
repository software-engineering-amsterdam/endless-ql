package QL.parsing.visitors;

import QL.classes.Question;
import QL.parsing.checkers.Checks;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FormVisitor extends QLBaseVisitor {
    public LinkedHashMap<String, Question> questionMap;
    private BlockVisitor blockVisitor;
    private UpdateVisitor updateVisitor;
    private QLParser.FormContext form;

    public FormVisitor() {
        this.questionMap = new LinkedHashMap<>();
        this.blockVisitor = new BlockVisitor(this.questionMap, true);
        this.updateVisitor = new UpdateVisitor(this.questionMap, true);
        this.form = form;
    }

    // Node visitor
    @Override
    public FormVisitor visitForm(QLParser.FormContext form) {
        Checks.checkForm(form);
        blockVisitor.visitBlock(form.block());
        return this;
    }

    public LinkedHashMap<String, Question> update(){
        updateVisitor.visitBlock(form.block());
        return questionMap;
    }

    public LinkedHashMap<String, Question> getQuestions() {
        return questionMap;
    }
}