package qls;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import qls.ast.ASTBuilder;
import qls.ast.model.Stylesheet;
import qls.ast.visitors.TestASTTraverse;
import qls.grammar.QLSLexer;
import qls.grammar.QLSParser;
import qls.gui.controller.GuiController;

class Main {
    public static void main(String[] args) {
        new GuiController();
    }
}
