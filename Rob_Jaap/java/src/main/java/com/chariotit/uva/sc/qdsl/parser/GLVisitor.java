package com.chariotit.uva.sc.qdsl.parser;

import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.ast.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.node.type.BooleanType;
import com.chariotit.uva.sc.qdsl.ast.node.type.IntegerType;
import com.chariotit.uva.sc.qdsl.ast.node.type.MoneyType;
import com.chariotit.uva.sc.qdsl.ast.node.type.StringType;
import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;

import java.util.ArrayList;
import java.util.List;


public class GLVisitor<T> extends QLBaseVisitor<AstNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        List<FormElement> formElements = new ArrayList<>();

        for (int i = 0; i < ctx.elem().size(); i++) {
            formElements.add((FormElement)visitElem(ctx.elem(i)));
        }

        return new Form(ctx.label().getText(), formElements);
    }


    @Override
    public Forms visitForms(QLParser.FormsContext ctx) {
        List<Form> forms = new ArrayList<>();

        for (int i = 0; i < ctx.form().size(); i++) {
            forms.add((Form)visitForm(ctx.form(i)));
        }

        return new Forms(forms);
    }

    @Override
    public Label visitLabel(QLParser.LabelContext ctx) {
        return new Label(ctx.WORD().getText());
    }

    @Override
    public FormElement visitElem(QLParser.ElemContext ctx) {
        return (FormElement)super.visitElem(ctx);
    }

    @Override
    public IfBlock visitIf_block(QLParser.If_blockContext ctx) {
        List<FormElement> elements = new ArrayList<>();

        for (int i = 0; i < ctx.elem().size(); i++) {
            elements.add(visitElem(ctx.elem(i)));
        }
        return new IfBlock((Expression)visitExpr(ctx.expr()), elements);
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        return (Question)super.visitQuestion(ctx);
    }

    @Override
    public Expression visitExpr(QLParser.ExprContext ctx) {

        return (Expression)super.visitExpr(ctx);
    }

    @Override
    public AstNode visitConstant(QLParser.ConstantContext ctx) {
        return super.visitConstant(ctx);
    }

    @Override
    public Operator visitBinop(QLParser.BinopContext ctx) {
        if (ctx.MINUS() != null) {
            return new MinusOp();
        } else if (ctx.PLUS() != null) {
            return new PlusOp();
        } else if (ctx.MULTIPLY() != null) {
            return new MultiplyOp();
        } else if (ctx.DIVIDE() != null) {
            return new DivideOp();
        } else if (ctx.EQ() != null) {
            return new EqOp();
        } else if (ctx.NEQ() != null) {
            return new NeqOp();
        } else if (ctx.GTE() != null) {
            return new GteOp();
        } else if (ctx.GT() != null) {
            return new GtOp();
        } else if (ctx.LTE() != null) {
            return new LteOp();
        } else if (ctx.LT() != null) {
            return new LtOp();
        } else {
            throw new RuntimeException("Undefined step in GLVisitor in visitBinop");
        }
    }

    @Override
    public AstNode visitUnop(QLParser.UnopContext ctx) {
        return super.visitUnop(ctx);
    }

    @Override
    public AstNode visitType_expr(QLParser.Type_exprContext ctx) {
        return super.visitType_expr(ctx);
    }

    @Override
    public Type visitType(QLParser.TypeContext ctx) {
        if (ctx.BOOLEAN_TYPE() != null) {
            return new BooleanType();
        } else if (ctx.INTEGER_TYPE() != null) {
            return new IntegerType();
        } else if (ctx.MONEY_TYPE() != null) {
            return new MoneyType();
        } else if (ctx.STRING_TYPE() != null) {
            return new StringType();
        } else {
            throw new RuntimeException("Undefined step in GLVisitor in visitType");
        }
    }

    @Override
    public AstNode visitLine_elm(QLParser.Line_elmContext ctx) {
        return super.visitLine_elm(ctx);
    }

    @Override
    public AstNode visitBlock_elm(QLParser.Block_elmContext ctx) {
        return super.visitBlock_elm(ctx);
    }

    @Override
    public LabelExpression visitLabel_expr(QLParser.Label_exprContext ctx) {
        return new LabelExpression(visitLabel(ctx.label()));
    }
}