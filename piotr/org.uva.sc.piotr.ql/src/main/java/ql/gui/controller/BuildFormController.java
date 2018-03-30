package ql.gui.controller;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.ast.ASTBuilder;
import ql.ast.model.Form;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.gui.view.ErrorMessageView;
import ql.gui.view.WindowView;

import java.io.IOException;

final class BuildFormController {
    public static Form buildForm(String selectedFilePath, WindowView windowView) {
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
        QLLexer qlLexer = new QLLexer(charStream);
        qlLexer.removeErrorListeners();
        qlLexer.addErrorListener(syntaxErrorNotificationController);

        CommonTokenStream commonTokenStream = new CommonTokenStream(qlLexer);

        // Parser
        QLParser qlParser = new QLParser(commonTokenStream);
        qlParser.removeErrorListeners();
        qlParser.addErrorListener(syntaxErrorNotificationController);

        QLParser.FormContext formContext = qlParser.form();

        // if found syntax error -> stop
        if (syntaxErrorNotificationController.getFoundSyntaxError()) {
            return null;
        }

        // AST Builder
        ASTBuilder astBuilder = new ASTBuilder();
        return astBuilder.visitForm(formContext);
    }
}
