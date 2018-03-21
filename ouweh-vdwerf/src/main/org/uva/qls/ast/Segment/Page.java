package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.List;

public class Page extends Segment {

    private String id;
    private List<Segment> segments;
    private List<DefaultStatement> defaultStatements;

    public Page(String id, List<Segment> segments, List<DefaultStatement> defaultStatements) {
        this.id = id;
        this.segments = segments;
        this.defaultStatements = defaultStatements;
    }

    @Override
    public String getId() {
        return "Page." + id;
    }

    public String getTitle() {
        return id;
    }

    public List<DefaultStatement> getDefaultStatements() {
        return defaultStatements;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this, parent);
    }
}
