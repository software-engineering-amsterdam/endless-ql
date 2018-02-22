package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

public interface ConditionalBlockVisitorFactory {
    QLBaseVisitor<ConditionalBlock> create(QLBaseVisitor<QuestionBlock> questionBlockVisitor);
}
