package ql.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryOperator;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Expression;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.Subtract;
import ql.ast.expression.UnaryOperator;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
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
import ql.ast.type.Undefined;
import ql.exceptions.ANTLRError;
import ql.exceptions.QLException;
import ql.grammar.QLBaseVisitor;
import ql.grammar.QLParser;
import ql.grammar.QLParser.AndExpressionContext;
import ql.grammar.QLParser.BracesExpressionContext;
import ql.grammar.QLParser.DateDMYContext;
import ql.grammar.QLParser.DateMDYContext;
import ql.grammar.QLParser.DateYDMContext;
import ql.grammar.QLParser.DateYMDContext;
import ql.grammar.QLParser.MoneyContext;
import ql.grammar.QLParser.MoneyTypeContext;
import ql.grammar.QLParser.OrExpressionContext;
import ql.grammar.QLParser.PrefixExpressionContext;
import ql.grammar.QLParser.RelationalExpressionContext;
import ql.helpers.Currency;
import ql.helpers.DateParser;
import ql.helpers.Location;

public class TextToAST extends QLBaseVisitor<Object> {
    
    List<QLException> errors;
    
    public TextToAST() {
        errors = new ArrayList<QLException>();
    }
    
    public TextToAST(List<QLException> errors) {
        this.errors = errors;
    }
    
    private Map<String,Identifier> identifiers = new HashMap<String,Identifier>();
    
    @Override 
    public Form visitForm(QLParser.FormContext ctx) { 
        
        Identifier id   = (Identifier) visitIdentifier(ctx.identifier());
        Block block     = (Block) visitBlock(ctx.block());
        Form form       = new Form(id,block);
        
        form.setLocation(tokenToLocation(ctx));
        
        return form;
    }
    
    @Override 
    public Block visitBlock(QLParser.BlockContext ctx) { 
        
        Block block = new Block();
        
        for(QLParser.StatementContext child : ctx.statement())
        {
            Statement stmt = (Statement) child.accept(this);
            
            stmt.setLocation(tokenToLocation(child));
            block.addStatement(stmt);
        }
        
        return block; 
    }

    @Override 
    public IfThen visitIfThen(QLParser.IfThenContext ctx) { 
        
        Expression expr     = (Expression) ctx.condition.accept(this);
        Statement thenStmt  = (Statement) ctx.thenStatement.accept(this);
        IfThen stmt         = new IfThen(expr,thenStmt);
        
        stmt.setLocation(tokenToLocation(ctx));
        
        return stmt;
    }
    
    @Override 
    public IfThenElse visitIfThenElse(QLParser.IfThenElseContext ctx) { 
        
        IfThen ifThenStmt   = (IfThen) ctx.ifThen().accept(this);
        Statement elseStmt  = (Statement) ctx.elseStatement.accept(this);
        IfThenElse stmt     = new IfThenElse(ifThenStmt.getCondition(),ifThenStmt.getThenStatement(),elseStmt);
        
        stmt.setLocation(tokenToLocation(ctx));
        
        return stmt;
    }

    @Override 
    public ComputedQuestion visitComputedQuestion(QLParser.ComputedQuestionContext ctx) { 
        
        String label                = stripQuotations(ctx.label());
        Type type                   = (Type) visit(ctx.type());
        Identifier id               = createIdentifier(ctx.identifier(),type);
        Expression expr             = (Expression) ctx.expression().accept(this);
        ComputedQuestion question   = new ComputedQuestion(label, id, expr);
        
        question.setLocation(tokenToLocation(ctx));
        
        return question;
    }

    @Override 
    public AnswerableQuestion visitAnswerableQuestion(QLParser.AnswerableQuestionContext ctx) { 
        
        String label                = stripQuotations(ctx.label());
        Type type                   = (Type) visit(ctx.type());
        Identifier id               = createIdentifier(ctx.identifier(),type);
        AnswerableQuestion question = new AnswerableQuestion(label,id);
        
        question.setLocation(tokenToLocation(ctx));
        
        return question;
    }
    
    @Override 
    public BoolLiteral visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        
        BoolLiteral literal = new BoolLiteral(ctx.getText());
        
        literal.setLocation(tokenToLocation(ctx));
        
        return literal;
    }

    @Override 
    public StrLiteral visitStringLiteral(QLParser.StringLiteralContext ctx) {
        
        StrLiteral literal = new StrLiteral(ctx.getText());
        
        literal.setLocation(tokenToLocation(ctx));
        
        return literal;
    }

    @Override 
    public IntLiteral visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        
        IntLiteral literal = new IntLiteral(ctx.getText());
        
        literal.setLocation(tokenToLocation(ctx));
        
        return literal;
    }

    @Override 
    public DecimalLiteral visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        
        DecimalLiteral literal = new DecimalLiteral(ctx.getText());
        
        literal.setLocation(tokenToLocation(ctx));
        
        return literal;
    }

    @Override
    public MoneyLiteral visitMoney(MoneyContext ctx) {
        
        MoneyLiteral literal = new MoneyLiteral(Currency.valueOf(ctx.currency().getText()), ctx.value.getText());
        
        literal.setLocation(tokenToLocation(ctx));
        
        return literal;
    }
    
    @Override
    public DateLiteral visitDateYMD(DateYMDContext ctx) {
        
        DateLiteral date    = new DateLiteral();
        Location location   = tokenToLocation(ctx);
        
        try 
        {
            if(ctx.getText().contains("-")) date = new DateLiteral(DateParser.fromYMD(ctx.getText(), "-"));
            else if(ctx.getText().contains("/")) date = new DateLiteral(DateParser.fromYMD(ctx.getText(), "/"));
            else if(ctx.getText().contains(".")) date = new DateLiteral(DateParser.fromYMD(ctx.getText(), "."));
            else date = new DateLiteral(DateParser.fromYMD(ctx.getText(), "-"));
        } 
        catch(QLException e) 
        {
            e.setLocation(location);
            errors.add(e);
        }
        
        date.setLocation(location);
        
        return date;
    }

    @Override
    public DateLiteral visitDateYDM(DateYDMContext ctx) {
        
        DateLiteral date    = new DateLiteral();
        Location location   = tokenToLocation(ctx);
        
        try 
        {
            if(ctx.getText().contains("-")) date = new DateLiteral(DateParser.fromYDM(ctx.getText(), "-"));
            else if(ctx.getText().contains("/")) date = new DateLiteral(DateParser.fromYDM(ctx.getText(), "/"));
            else if(ctx.getText().contains(".")) date = new DateLiteral(DateParser.fromYDM(ctx.getText(), "."));
            else date = new DateLiteral(DateParser.fromYDM(ctx.getText(), "-"));
        } 
        catch(QLException e) 
        {
            e.setLocation(location);
            errors.add(e);
        }
        
        date.setLocation(location);
        
        return date;
    }
    
    @Override
    public DateLiteral visitDateDMY(DateDMYContext ctx) {
        
        DateLiteral date    = new DateLiteral();
        Location location   = tokenToLocation(ctx);
        
        try 
        {
            if(ctx.getText().contains("-")) date = new DateLiteral(DateParser.fromDMY(ctx.getText(), "-"));
            else if(ctx.getText().contains("/")) date = new DateLiteral(DateParser.fromDMY(ctx.getText(), "/"));
            else if(ctx.getText().contains(".")) date = new DateLiteral(DateParser.fromDMY(ctx.getText(), "."));
            else date = new DateLiteral(DateParser.fromDMY(ctx.getText(), "-"));
        } 
        catch(QLException e) 
        {
            e.setLocation(location);
            errors.add(e);
        }
        
        date.setLocation(location);
        
        return date;
    }

    @Override
    public DateLiteral visitDateMDY(DateMDYContext ctx) {
        
        DateLiteral date    = new DateLiteral();
        Location location   = tokenToLocation(ctx);
        
        try 
        {
            if(ctx.getText().contains("-")) date = new DateLiteral(DateParser.fromMDY(ctx.getText(), "-"));
            else if(ctx.getText().contains("/")) date = new DateLiteral(DateParser.fromMDY(ctx.getText(), "/"));
            else if(ctx.getText().contains(".")) date = new DateLiteral(DateParser.fromMDY(ctx.getText(), "."));
            else date = new DateLiteral(DateParser.fromMDY(ctx.getText(), "-"));
        } 
        catch(QLException e) 
        {
            e.setLocation(location);
            errors.add(e);
        }
        
        date.setLocation(location);
        
        return date;
    }

    @Override 
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) {
        return createIdentifier(ctx);
    }
    
    @Override 
    public BinaryOperator visitMultiplyExpression(QLParser.MultiplyExpressionContext ctx) {
        
        Expression lhs      = (Expression) ctx.firstOperand.accept(this);
        Expression rhs      = (Expression) ctx.secondOperand.accept(this);
        BinaryOperator expr;
        
        if(isTokenType(ctx.operator,QLParser.ASTERISK))
        {
            expr = new Multiply(lhs,rhs);
        }
        else
        {
            expr = new Divide(lhs,rhs);
        }
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }

    @Override 
    public BinaryOperator visitAddExpression(QLParser.AddExpressionContext ctx) {
        
        Expression lhs      = (Expression) ctx.firstOperand.accept(this);
        Expression rhs      = (Expression) ctx.secondOperand.accept(this);
        BinaryOperator expr;
        
        if(isTokenType(ctx.operator,QLParser.PLUS))
        {
            expr = new Add(lhs,rhs);
        }
        else
        {
            expr = new Subtract(lhs,rhs);
        }
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }

    @Override
    public BinaryOperator visitRelationalExpression(RelationalExpressionContext ctx) {
        
        Expression lhs  = (Expression) ctx.firstOperand.accept(this);
        Expression rhs  = (Expression) ctx.secondOperand.accept(this);
        BinaryOperator expr;
        
        if(isTokenType(ctx.operator,QLParser.LT))       expr = new Less(lhs,rhs);
        else if(isTokenType(ctx.operator,QLParser.LE))  expr = new LessEqual(lhs,rhs);
        else if(isTokenType(ctx.operator,QLParser.GT))  expr = new Greater(lhs,rhs);
        else if(isTokenType(ctx.operator,QLParser.GE))  expr = new GreaterEqual(lhs,rhs);
        else if(isTokenType(ctx.operator,QLParser.EQ))  expr = new Equal(lhs,rhs);
        else                                            expr = new NotEqual(lhs,rhs);
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }
    
    @Override
    public And visitAndExpression(AndExpressionContext ctx) {
        
        Expression lhs  = (Expression) ctx.firstOperand.accept(this);
        Expression rhs  = (Expression) ctx.secondOperand.accept(this);
        And expr         = new And(lhs,rhs);
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }

    @Override
    public Or visitOrExpression(OrExpressionContext ctx) {
        
        Expression lhs  = (Expression) ctx.firstOperand.accept(this);
        Expression rhs  = (Expression) ctx.secondOperand.accept(this);
        Or expr         = new Or(lhs,rhs);
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }
    
    
    @Override
    public UnaryOperator visitPrefixExpression(PrefixExpressionContext ctx) {

        UnaryOperator op;
        Expression expr = (Expression) ctx.expression().accept(this);
        
        if(isTokenType(ctx.operator,QLParser.EXCLAMATION))  op = new Negation(expr);
        else if(isTokenType(ctx.operator,QLParser.PLUS))    op = new Positive(expr);
        else                                                op = new Negative(expr);
        
        op.setLocation(tokenToLocation(ctx));
        
        return op;
    }

    @Override
    public Expression visitBracesExpression(BracesExpressionContext ctx) {
        
        Expression expr = (Expression) visit(ctx.expression());
        
        expr.setLocation(tokenToLocation(ctx));
        
        return expr;
    }
    

    @Override 
    public Bool visitBooleanType(QLParser.BooleanTypeContext ctx) { 
        
        Bool type = new Bool();
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    @Override 
    public Str visitStringType(QLParser.StringTypeContext ctx) { 
        
        Str type = new Str();
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    @Override 
    public Int visitIntegerType(QLParser.IntegerTypeContext ctx) { 
        
        Int type = new Int();
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    @Override 
    public Decimal visitDecimalType(QLParser.DecimalTypeContext ctx) { 
        
        Decimal type = new Decimal();
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    @Override
    public Money visitMoneyType(MoneyTypeContext ctx) {
        
        Currency currency;
        
        if(Currency.exists(ctx.getText())) {
            currency = Currency.valueOf(ctx.getText());
        } else {
            currency = Currency.defaultCurrency;
            errors.add(new ANTLRError("Unknown currency "+ctx.getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine()));
        }
        
        Money type = new Money(currency);
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    @Override 
    public Date visitDateType(QLParser.DateTypeContext ctx) { 
        
        Date type = new Date();
        
        type.setLocation(tokenToLocation(ctx));
        
        return type;
    }

    private Location tokenToLocation(ParserRuleContext ctx) {
        
        int line    = ctx.getStart().getLine();
        int column  = ctx.getStart().getCharPositionInLine();
        int offset  = ctx.getStart().getStartIndex();
        int length  = 1 + ctx.getStart().getStopIndex() - offset;
        
        return new Location(line, column, offset, length);
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
    
    private Identifier createIdentifier(QLParser.IdentifierContext ctx, Type type) {
        
        Identifier id = new Identifier(ctx.getText(), type);
        
        id.setLocation(tokenToLocation(ctx));
        
        if(identifiers.containsKey(id.getName())) id = replaceWithOriginalIdentifier(id);
        else identifiers.put(id.getName(), id);
        
        return id;
    }
    
    private Identifier createIdentifier(QLParser.IdentifierContext ctx) {
        return createIdentifier(ctx, new Undefined());
    }
    
    private Identifier replaceWithOriginalIdentifier(Identifier id) {
        
        String name = id.getName();
        
        if(identifiers.containsKey(name))
        {
            Identifier original = identifiers.get(name);
            
            if(id.getType().isUndefined()) return original;
            
            if(original.getType().isUndefined())
            {
                original.setType(id.getType());
                original.setLocation(id.getLocation());
            }
            
            return (original.getType().equals(id.getType()))? identifiers.get(name) : id;
        }
        
        return id;
    }
}
