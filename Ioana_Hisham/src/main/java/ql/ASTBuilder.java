package ql;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.ast.Form;
import ql.ast.Node;
import ql.ast.expressions.literals.Identifier;
import ql.ast.statements.Statement;

import java.util.ArrayList;
import java.util.List;


public class ASTBuilder extends QLBaseVisitor<Node> {

    @Override
    public Node visitForm(QLParser.FormContext formContext) {
        int lineNumber = formContext.start.getLine();
        Identifier identifier = new Identifier(lineNumber, formContext.Identifier().getText());
        List<Statement> statements = new ArrayList<Statement>();
        for (QLParser.StatementsContext statementContext: formContext.statements()) {
            statements.add((Statement) statementContext.accept(this));
        }
        return new Form(lineNumber, identifier, statements);
    }
}
