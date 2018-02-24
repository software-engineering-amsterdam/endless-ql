package ql.visitors;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import ql.ast.QLNode;
import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BoolLiteral;
import ql.ast.expression.DateLiteral;
import ql.ast.expression.DecimalLiteral;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Expression;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.IntLiteral;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.MoneyLiteral;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.StrLiteral;
import ql.ast.expression.Subtract;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.grammar.QLBaseVisitor;
import ql.grammar.QLParser;
import ql.helpers.Location;

public class QLVisitorToAst extends QLBaseVisitor<Object> {
    
    private Map<String,Identifier> identifiers = new HashMap<String,Identifier>();
    
    @Override 
    public QLNode visitForm(QLParser.FormContext ctx) { 
        
        Identifier id   = (Identifier) visitIdentifier(ctx.identifier());
        Block block     = (Block) visitBlock(ctx.block());
        Form form       = new Form(id,block);
        
        return setLocation(form, ctx.start);
    }
    
    @Override 
    public QLNode visitBlock(QLParser.BlockContext ctx) { 
        
        Block block = new Block();
        
        for(QLParser.StatementContext child : ctx.statement())
        {
            Statement stmt = (Statement) visitStatement(child);
            
            block.addStatement((Statement) setLocation(stmt,child.start));
        }
        
        return block; 
    }

    @Override 
    public QLNode visitIfThen(QLParser.IfThenContext ctx) { 
        
        Expression expr     = (Expression) visit(ctx.condition);
        Statement thenStmt  = (Statement) visitStatement(ctx.thenStmt);
        IfThen stmt         = new IfThen(expr,thenStmt);
        
        return setLocation(stmt, ctx.start);
    }
    
    @Override 
    public QLNode visitIfThenElse(QLParser.IfThenElseContext ctx) { 
        
        Expression expr     = (Expression) visit(ctx.condition);
        Statement thenStmt  = (Statement) visitStatement(ctx.thenStmt);
        Statement elseStmt  = (Statement) visitStatement(ctx.elseStmt);
        IfThenElse stmt     = new IfThenElse(expr,thenStmt,elseStmt);
        
        return setLocation(stmt, ctx.start);
    }

    @Override 
    public QLNode visitComputedQuestion(QLParser.ComputedQuestionContext ctx) { 
        
        String label    = stripQuotations(ctx.label());
        Type type       = (Type) visit(ctx.type());
        Identifier id   = replaceWithOriginalIdentifier(((Identifier) visitIdentifier(ctx.identifier())).setType(type));
        Expression expr = (Expression) visit(ctx.expr());
        
        return setLocation(new ComputedQuestion(label,id,type,expr), ctx.start);
    }

    @Override 
    public QLNode visitAnswerableQuestion(QLParser.AnswerableQuestionContext ctx) { 
        
        String label    = stripQuotations(ctx.label());
        Type type       = (Type) visit(ctx.type());
        Identifier id   = replaceWithOriginalIdentifier(((Identifier) visitIdentifier(ctx.identifier())).setType(type));
        
        return setLocation(new AnswerableQuestion(label,id,type), ctx.start);
    }
    
    @Override 
    public QLNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return setLocation(new BoolLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return setLocation(new StrLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return setLocation(new IntLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return setLocation(new DecimalLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
        return setLocation(new MoneyLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitDateLiteral(QLParser.DateLiteralContext ctx) {
        return setLocation(new DateLiteral(ctx.getText()), ctx.start);
    }

    @Override 
    public QLNode visitIdentifier(QLParser.IdentifierContext ctx) { 
        return setLocation(replaceWithOriginalIdentifier(new Identifier(ctx.getText())),ctx.start);
    }
    
    @Override 
    public QLNode visitMulExpr(QLParser.MulExprContext ctx) {
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.MULTIPLY))       expr = new Multiply(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.DIVIDE))    expr = new Divide(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitAddExpr(QLParser.AddExprContext ctx) { 
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.PLUS))       expr = new Add(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.MINUS)) expr = new Subtract(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitRelExpr(QLParser.RelExprContext ctx) { 
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        QLNode expr     = null;
        
        if(isTokenType(ctx.op,QLParser.LT))      expr = new Less(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.LE)) expr = new LessEqual(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.GT)) expr = new Greater(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.GE)) expr = new GreaterEqual(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.EQ)) expr = new Equal(lhs,rhs);
        else if(isTokenType(ctx.op,QLParser.NE)) expr = new NotEqual(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitAndExpr(QLParser.AndExprContext ctx) { 

        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        And expr        = new And(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitOrExpr(QLParser.OrExprContext ctx) {
        
        Expression lhs  = (Expression) visit(ctx.lhs);
        Expression rhs  = (Expression) visit(ctx.rhs);
        Or expr         = new Or(lhs,rhs);
        
        return setLocation(expr, ctx.start);
    }

    @Override 
    public QLNode visitPreExpr(QLParser.PreExprContext ctx) { 

        Expression expr = (Expression) visit(ctx.ex);
        QLNode node     = null;
        
        if(isTokenType(ctx.op,QLParser.NOT))         node = new Negation(expr);
        else if(isTokenType(ctx.op,QLParser.PLUS))   node = new Positive(expr);
        else if(isTokenType(ctx.op,QLParser.MINUS))  node = new Negative(expr);
        
        return setLocation(node, ctx.start);
    }

    @Override 
    public QLNode visitBraExpr(QLParser.BraExprContext ctx) { 
        
        Expression expr = (Expression) visit(ctx.ex);
        
        return setLocation(expr, ctx.start);
    }
    
    @Override 
    public QLNode visitBooleanType(QLParser.BooleanTypeContext ctx) { 
        return setLocation(new Bool(), ctx.start); 
    }

    @Override 
    public QLNode visitStringType(QLParser.StringTypeContext ctx) { 
        return setLocation(new Str(), ctx.start); 
    }

    @Override 
    public QLNode visitIntegerType(QLParser.IntegerTypeContext ctx) { 
        return setLocation(new Int(), ctx.start); 
    }

    @Override 
    public QLNode visitDecimalType(QLParser.DecimalTypeContext ctx) { 
        return setLocation(new Decimal(), ctx.start); 
    }

    @Override 
    public QLNode visitMoneyType(QLParser.MoneyTypeContext ctx) { 
        return setLocation(new Money(), ctx.start); 
    }

    @Override 
    public QLNode visitDateType(QLParser.DateTypeContext ctx) { 
        return setLocation(new Date(), ctx.start); 
    }

    private QLNode setLocation(QLNode n, Token t) {
        int line    = t.getLine();
        int column  = t.getCharPositionInLine();
        int offset  = t.getStartIndex();
        
        n.setLocation(new Location(line, column, offset, n.toString().length()));
        
        return n;
    }
    
    private boolean isTokenType(Token t, int type) {
        return t.getType() == type;
    }
    
    private String stripQuotations(QLParser.LabelContext lbl) {
        String label    = lbl.getText();
        int endIndex    = label.length() - 1;
        label           = label.substring(1, endIndex);
        
        return label;
    }
    
    private Identifier replaceWithOriginalIdentifier(Identifier id) {
        
        String name = id.getName();
        
        if(identifiers.containsKey(name))
        {
            Identifier original = identifiers.get(name);
            
            if(id.getType().isUndefined()) return original;
            
            if(original.getType().equals(id.getType())) return identifiers.get(name);
        }
        
        identifiers.put(name, id);
        
        return id;
    }
}
