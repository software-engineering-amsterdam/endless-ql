package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;

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
}
