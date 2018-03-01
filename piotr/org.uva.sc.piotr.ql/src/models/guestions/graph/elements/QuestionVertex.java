package models.guestions.graph.elements;

import grammar.QLParser;

import java.util.ArrayList;

public class QuestionVertex {

    private String label;
    private String name;
    private ArrayList<ReferenceEdge> referresTo;

    // temp - to be removed for the final version
    private QLParser.QuestionContext ctx;

    public QuestionVertex(String label, String name, QLParser.QuestionContext ctx) {
        this.label = label;
        this.name = name;
        this.ctx = ctx;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QLParser.QuestionContext getCtx() {
        return ctx;
    }

    public void setCtx(QLParser.QuestionContext ctx) {
        this.ctx = ctx;
    }

    public ArrayList<ReferenceEdge> getReferresTo() {
        return referresTo;
    }

    public void setReferresTo(ArrayList<ReferenceEdge> referresTo) {
        this.referresTo = referresTo;
    }

    public boolean addReferresTo(QuestionVertex vertex) {
        return this.referresTo.add(new ReferenceEdge(vertex));
    }
}
