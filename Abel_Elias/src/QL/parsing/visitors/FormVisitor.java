package QL.parsing.visitors;

import QL.classes.Question;
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
    }

    public FormVisitor(QLParser.FormContext form){
        this.questionMap = new LinkedHashMap<>();
        this.blockVisitor = new BlockVisitor(this.questionMap, true);
        this.updateVisitor = new UpdateVisitor(this.questionMap, true);
        this.form = form;
        visitForm(this.form);
    }

    // Node visitor
    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        blockVisitor.visitBlock(ctx.block());
        return questionMap;
    }

    public HashMap<String, Question> update(){
        updateVisitor.visitBlock(form.block());
        return questionMap;
    }
}