package org.uva.qls.ast;

import org.uva.qls.ast.DefaultStatement.DefaultStatement;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Segment;

import java.util.ArrayList;
import java.util.List;

public class Page extends TreeNode {

    private String id;
    private List<Segment> segments;
    private List<DefaultStatement> defaultStatements;

    public Page(String id, List<Segment> segments, List<DefaultStatement> defaultStatements){
        this.id = id;
        this.segments = segments;
        this.defaultStatements = defaultStatements;
    }

    public String getId() {
        return id;
    }

    public List<DefaultStatement> getDefaultStatements() {
        return defaultStatements;
    }

    public List<Segment> getSegments() {
        return segments;
    }
    
}
