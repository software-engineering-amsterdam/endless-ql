package qlviz.interpreter;

import qlviz.QLVisitor;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

public interface ConditionalBlockVisitorFactory {
    QLVisitor<ConditionalBlock> create(QLVisitor<QuestionBlock> questionBlockVisitor);
}
