package QL.parsing.visitors;

import QL.classes.Question;
import QL.parsing.checkers.Checks;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FormVisitor extends QLBaseVisitor {
    private LinkedHashMap<String, Question> questionMap;
    private BlockVisitor blockVisitor;
    private UpdateVisitor updateVisitor;
    private QLParser.FormContext form;

    public FormVisitor() {
        this.questionMap = new LinkedHashMap<>();
        this.blockVisitor = new BlockVisitor(this.questionMap, true);
        this.updateVisitor = new UpdateVisitor(this.questionMap, true);
    }

    // Node visitor
    @Override
    public FormVisitor visitForm(QLParser.FormContext form) {
        this.form = form;
        Checks.checkForm(form);
        blockVisitor.visitBlock(form.block());
        this.form = form;
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