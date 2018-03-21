package org.uva.qls.ast.Segment;

import org.uva.qls.ast.TreeNode;
import org.uva.qls.visitor.SegmentVisitor;

public abstract class Segment extends TreeNode {

    public abstract String getId();

    public abstract <S> S accept(SegmentVisitor<S> visitor, Segment parent);
}
