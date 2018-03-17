package org.uva.qls.ast.Segment;

import org.uva.qls.ast.TreeNode;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.List;

public  abstract class Segment extends TreeNode {

    public abstract <S> S accept(SegmentVisitor<S> visitor);
}
