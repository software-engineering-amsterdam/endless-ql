package qlviz.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import qlviz.model.QuestionType;

public interface QuestionTypeTranslator {
    QuestionType translate(TerminalNode node);
}
