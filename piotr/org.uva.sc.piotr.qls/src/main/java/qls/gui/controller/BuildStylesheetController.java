package qls.gui.controller;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.gui.controller.SyntaxErrorNotificationController;
import ql.gui.view.ErrorMessageView;
import ql.gui.view.WindowView;
import qls.ast.ASTBuilder;
import qls.ast.model.Stylesheet;
import qls.grammar.QLSLexer;
import qls.grammar.QLSParser;

import java.io.IOException;

public final class BuildStylesheetController {
    public static Stylesheet buildStylesheet(String selectedFilePath, WindowView windowView) {
        CharStream charStream;

        try {
            charStream = CharStreams.fromFileName(selectedFilePath);
        } catch (IOException e) {
            ErrorMessageView.showErrorDialog(windowView, "Fatal error", e.getMessage());
            return null;
        }

        SyntaxErrorNotificationController syntaxErrorNotificationController
                = new SyntaxErrorNotificationController(windowView);

        // Lexer
        QLSLexer qlsLexer = new QLSLexer(charStream);
        qlsLexer.removeErrorListeners();
        qlsLexer.addErrorListener(syntaxErrorNotificationController);

        CommonTokenStream commonTokenStream = new CommonTokenStream(qlsLexer);

        // Parser
        QLSParser qlsParser = new QLSParser(commonTokenStream);
        qlsParser.removeErrorListeners();
        qlsParser.addErrorListener(syntaxErrorNotificationController);

        QLSParser.StylesheetContext stylesheetContext = qlsParser.stylesheet();

        // if found syntax error -> stop
        if (syntaxErrorNotificationController.getFoundSyntaxError()) {
            return null;
        }

        // AST Builder
        ASTBuilder astBuilder = new ASTBuilder();
        return astBuilder.visitStylesheet(stylesheetContext);
    }
}
