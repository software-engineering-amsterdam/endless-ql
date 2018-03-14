package org.uva.qls.ast.Segment;

import org.uva.qls.ast.TreeNode;
import java.util.List;

public  abstract class Segment extends TreeNode {
    public abstract List<Question> getQuestions();
}
