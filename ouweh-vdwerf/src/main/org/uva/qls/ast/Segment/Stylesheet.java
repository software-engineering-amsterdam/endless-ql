package org.uva.qls.ast.Segment;

import org.uva.qls.ast.Segment.Page;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.List;

public class Stylesheet extends Segment {

    private String id;
    private List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this);
    }
}
