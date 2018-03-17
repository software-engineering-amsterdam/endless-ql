package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.ArrayList;
import java.util.List;

public class Section extends Segment {

    private String id;
    private List<Segment> segments;
    private List<DefaultStatement> defaultStatements;

    public Section(String id, List<Segment> segments, List<DefaultStatement> defaultStatements){
        this.id = id;
        this.segments = segments;
        this.defaultStatements = defaultStatements;
    }

    @Override
    public String getId() {
        return "Section."+id;
    }

    public String getTitle() {
        return id;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<DefaultStatement> getDefaultStatements() {
        return defaultStatements;
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this, parent);
    }
}
