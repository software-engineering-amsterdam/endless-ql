package com.chariotit.uva.sc.qdsl.parser;

import com.chariotit.uva.sc.qdsl.ast.*;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.ql.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.*;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.ql.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import com.chariotit.uva.sc.qdsl.parser.exception.UnknownOptionException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;


public class QLVisitor<T> extends QLBaseVisitor<AstNode> {

    private SourceFilePosition getSourceFilePosition(ParserRuleContext ctx) {
        return new SourceFilePosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public QLAstRoot visitForms(QLParser.FormsContext ctx) {
        List<Form> forms = new ArrayList<>();

        for (int i = 0; i < ctx.form().size(); i++) {
            forms.add(visitForm(ctx.form(i)));
        }

        return new QLAstRoot(forms, getSourceFilePosition(ctx));
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        List<FormElement> formElements = new ArrayList<>();

        for (int i = 0; i < ctx.elem().size(); i++) {
            formElements.add(visitElem(ctx.elem(i)));
        }

        return new Form(ctx.label().getText(), formElements, getSourceFilePosition(ctx));
    }

    @Override
    public Label visitLabel(QLParser.LabelContext ctx) {
        return new Label(ctx.WORD().getText(), getSourceFilePosition(ctx));
    }

    @Override
    public LineElement visitLine_elm(QLParser.Line_elmContext ctx) {
        return new LineElement(
                visitLabel(ctx.label()),
                visitQuestion(ctx.question()),
                visitType_expr(ctx.type_expr()),
                getSourceFilePosition(ctx)
        );
    }

    @Override
    public FormElement visitElem(QLParser.ElemContext ctx) {
        if (ctx.line_elm() != null) {
            return visitLine_elm(ctx.line_elm());
        } else if (ctx.block_elm() != null) {
            return visitBlock_elm(ctx.block_elm());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public BlockElement visitBlock_elm(QLParser.Block_elmContext ctx) {
        if (ctx.if_block() != null) {
            return visitIf_block(ctx.if_block());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public IfBlock visitIf_block(QLParser.If_blockContext ctx) {
        List<FormElement> ifElements = new ArrayList<>();
        Expression expression = visitExpr(ctx.expr());

        for (int i = 0; i < ctx.elem().size(); i++) {
            ifElements.add(visitElem(ctx.elem(i)));
        }

        if (ctx.ELSE() != null) {
            List<FormElement> elseElements = new ArrayList<>();

            for (int i = 0; i < ctx.else_elems().elem().size(); i++) {
                elseElements.add(visitElem(ctx.else_elems().elem(i)));
            }

            return new IfBlock(expression, ifElements, elseElements,
                    getSourceFilePosition(ctx)
            );
        }

        return new IfBlock(expression, ifElements, getSourceFilePosition(ctx));
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        // Remove leading and trailing quotes from question
        String question = ctx.STRING().getText().substring(
                1, ctx.STRING().getText().length() - 1
        );
        return new Question(question, getSourceFilePosition(ctx));
    }

    @Override
    public Expression visitExpr(QLParser.ExprContext ctx) {
        if (ctx.unop_expr() != null) {
            return visitUnop_expr(ctx.unop_expr());
        } else if (ctx.label_binop_expr() != null) {
            return visitLabel_binop_expr(ctx.label_binop_expr());
        } else if (ctx.const_binop_expr() != null) {
            return visitConst_binop_expr(ctx.const_binop_expr());
        } else if (ctx.constant() != null) {
            return visitConstant(ctx.constant());
        } else if (ctx.label_expr() != null) {
            return visitLabel_expr(ctx.label_expr());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public UnOpExpression visitUnop_expr(QLParser.Unop_exprContext ctx) {
        return new UnOpExpression(visitUnop(ctx.unop()), visitExpr(ctx.expr()), getSourceFilePosition(ctx));
    }

    @Override
    public LabelBinOpExpression visitLabel_binop_expr(QLParser.Label_binop_exprContext ctx) {
        return new LabelBinOpExpression(
                visitLabel_expr(ctx.label_expr()),
                visitBinop(ctx.binop()),
                visitExpr(ctx.expr()),
                getSourceFilePosition(ctx)
        );
    }

    @Override
    public ConstBinOpExpression visitConst_binop_expr(QLParser.Const_binop_exprContext ctx) {
        return new ConstBinOpExpression(
                visitConstant(ctx.constant()),
                visitBinop(ctx.binop()),
                visitExpr(ctx.expr()),
                getSourceFilePosition(ctx)
        );
    }

    @Override
    public LabelExpression visitLabel_expr(QLParser.Label_exprContext ctx) {
        return new LabelExpression(ctx.WORD().getSymbol().getText(), getSourceFilePosition(ctx));
    }

    @Override
    public Constant visitConstant(QLParser.ConstantContext ctx) {
        if (ctx.money_constant() != null) {
            return visitMoney_constant(ctx.money_constant());
        } else if (ctx.string_constant() != null) {
            return visitString_constant(ctx.string_constant());
        } else if (ctx.boolean_constant() != null) {
            return visitBoolean_constant(ctx.boolean_constant());
        } else if (ctx.integer_constant() != null) {
            return visitInteger_constant(ctx.integer_constant());
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public MoneyConstant visitMoney_constant(QLParser.Money_constantContext ctx) {
        return new MoneyConstant(new MoneyExpressionValue(Float.parseFloat(ctx.NUMBER(0).getText() + "." + ctx
                .NUMBER(1)
                .getText())), getSourceFilePosition(ctx));
    }

    @Override
    public StringConstant visitString_constant(QLParser.String_constantContext ctx) {
        // Remove leading and trailing quotes from question
        String string = ctx.STRING().getText().substring(
                1, ctx.STRING().getText().length() - 1
        );

        return new StringConstant(
                new StringExpressionValue(string),
                getSourceFilePosition(ctx));
    }

    @Override
    public BooleanConstant visitBoolean_constant(QLParser.Boolean_constantContext ctx) {
        if (ctx.TRUE() != null) {
            return new BooleanConstant(
                    new BooleanExpressionValue(true), getSourceFilePosition(ctx));
        } else if (ctx.FALSE() != null) {
            return new BooleanConstant(
                    new BooleanExpressionValue(false), getSourceFilePosition(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public IntegerConstant visitInteger_constant(QLParser.Integer_constantContext ctx) {
        return new IntegerConstant(
                new IntegerExpressionValue(
                        Integer.parseInt(ctx.NUMBER().getText())
                ),
                getSourceFilePosition(ctx)
        );
    }

    @Override
    public Operator visitBinop(QLParser.BinopContext ctx) {
        if (ctx.MINUS() != null) {
            return new MinusOp(getSourceFilePosition(ctx));
        } else if (ctx.PLUS() != null) {
            return new PlusOp(getSourceFilePosition(ctx));
        } else if (ctx.MULTIPLY() != null) {
            return new MultiplyOp(getSourceFilePosition(ctx));
        } else if (ctx.DIVIDE() != null) {
            return new DivideOp(getSourceFilePosition(ctx));
        } else if (ctx.EQ() != null) {
            return new EqOp(getSourceFilePosition(ctx));
        } else if (ctx.NEQ() != null) {
            return new NeqOp(getSourceFilePosition(ctx));
        } else if (ctx.GTE() != null) {
            return new GteOp(getSourceFilePosition(ctx));
        } else if (ctx.GT() != null) {
            return new GtOp(getSourceFilePosition(ctx));
        } else if (ctx.LTE() != null) {
            return new LteOp(getSourceFilePosition(ctx));
        } else if (ctx.LT() != null) {
            return new LtOp(getSourceFilePosition(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public Operator visitUnop(QLParser.UnopContext ctx) {
        if (ctx.MINUS() != null) {
            return new MinusOp(getSourceFilePosition(ctx));
        } else if (ctx.PLUS() != null) {
            return new PlusOp(getSourceFilePosition(ctx));
        } else if (ctx.NOT() != null) {
            return new NotOp(getSourceFilePosition(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public TypeExpression visitType_expr(QLParser.Type_exprContext ctx) {
        TypeExpression typeExpression = new TypeExpression(
                visitTypeNode(ctx.typeNode()),
                getSourceFilePosition(ctx)
        );

        if (ctx.expr() != null) {
            typeExpression.setExpression(visitExpr(ctx.expr()));
        }

        return typeExpression;
    }

    @Override
    public TypeNode visitTypeNode(QLParser.TypeNodeContext ctx) {
        if (ctx.BOOLEAN_TYPE() != null) {
            return new BooleanTypeNode(getSourceFilePosition(ctx));
        } else if (ctx.INTEGER_TYPE() != null) {
            return new IntegerTypeNode(getSourceFilePosition(ctx));
        } else if (ctx.MONEY_TYPE() != null) {
            return new MoneyTypeNode(getSourceFilePosition(ctx));
        } else if (ctx.STRING_TYPE() != null) {
            return new StringTypeNode(getSourceFilePosition(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public AstNode visit(ParseTree tree) {
        return super.visit(tree);
    }
}