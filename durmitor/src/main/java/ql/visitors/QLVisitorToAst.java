package ql.visitors;

import ql.ast.Identifier;
import ql.ast.expression.Expression;
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
import ql.grammar.QLBaseVisitor;
import ql.grammar.QLParser;

public class QLVisitorToAst extends QLBaseVisitor<Object> {
    
    @Override 
    public Form visitForm(QLParser.FormContext ctx) { 
        
        Identifier id   = visitIdentifier(ctx.identifier());
        Block block     = visitBlock(ctx.block());
        
        return new Form(id,block);
    }
    
    @Override 
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) { 
        
        return new Identifier(ctx.getText());
    }
    
    @Override 
    public Block visitBlock(QLParser.BlockContext ctx) { 
        
        Block block = new Block();
        
        for(QLParser.StatementContext stmt : ctx.statement())
        {
            block.addStatement((Statement) visitStatement(stmt));
        }
        
        return block; 
    }

    @Override 
    public IfThen visitIfThen(QLParser.IfThenContext ctx) { 
        
        Expression expr = (Expression) visitExpr(ctx.condition);
        Statement stmt  = (Statement) visitStatement(ctx.thenStmt);
        
        return new IfThen(expr,stmt);
    }
    
    @Override 
    public IfThenElse visitIfThenElse(QLParser.IfThenElseContext ctx) { 
        
        Expression expr     = (Expression) visitExpr(ctx.condition);
        Statement thenStmt  = (Statement) visitStatement(ctx.thenStmt);
        Statement elseStmt  = (Statement) visitStatement(ctx.elseStmt);
        
        return new IfThenElse(expr,thenStmt,elseStmt);
    }

    @Override 
    public ComputedQuestion visitComputedQuestion(QLParser.ComputedQuestionContext ctx) { 
        
        String label            = ctx.lbl.getText();
        int endIndex            = label.length() - 1;
        label                   = label.substring(1, endIndex);
        Type type               = visitType(ctx.type());
        Identifier identifier   = visitIdentifier(ctx.identifier());
        Expression expr         = (Expression) visitExpr(ctx.expr());
        
        return new ComputedQuestion(label,identifier,type,expr); 
    }

    @Override 
    public AnswerableQuestion visitAnswerableQuestion(QLParser.AnswerableQuestionContext ctx) { 
        
        String label               = ctx.lbl.getText();
        int endIndex            = label.length() - 1;
        label                   = label.substring(1, endIndex);
        Identifier identifier   = visitIdentifier(ctx.identifier());
        Type type               = visitType(ctx.type());
        
        return new AnswerableQuestion(label,identifier,type); 
    }
    
    public Type visitType(QLParser.TypeContext ctx) { 
        
        switch(ctx.getText())
        {
            case "boolean": return new Bool();
            case "string": return new Str();
            case "integer": return new Int();
            case "decimal": return new Decimal();
            case "money": return new Money();
            case "date": return new Date();
            default: return new Undefined();
        }
    }

}
