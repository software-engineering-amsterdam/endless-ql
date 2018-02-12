package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.antlr.QLVisitor;
import nl.uva.js.qlparser.models.enums.DataType;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class QLVisitorImpl implements QLVisitor {

    @Override
    public DataType visitDatatype(QLParser.DatatypeContext ctx) {
        return null;
    }

    @Override
    public Boolean visitBoolval(QLParser.BoolvalContext ctx) {
        return false;
    }

    @Override
    public Object visitValues(QLParser.ValuesContext ctx) {
        return null;
    }

    @Override
    public Object visitBoolOp(QLParser.BoolOpContext ctx) {
        return null;
    }

    @Override
    public Object visitCompOp(QLParser.CompOpContext ctx) {
        return null;
    }

    @Override
    public Object visitArithOp(QLParser.ArithOpContext ctx) {
        return null;
    }

    @Override
    public Object visitOper(QLParser.OperContext ctx) {
        return null;
    }

    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        return null;
    }

    @Override
    public Object visitFormBlock(QLParser.FormBlockContext ctx) {
        return null;
    }

    @Override
    public Object visitFormExpression(QLParser.FormExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitExpression(QLParser.ExpressionContext ctx) {
        return null;
    }

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        return null;
    }

    @Override
    public Object visit(ParseTree tree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}
