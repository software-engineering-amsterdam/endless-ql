package org.uva.qls.visitor;


import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Section;

public interface SegmentVisitor<N> {

    N visit(Section section);

    N visit(QuestionReference questionReference);


}
