package com.chariotit.uva.sc.qdsl.parser;

import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.ast.node.constant.BooleanConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.IntegerConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.MoneyConstant;
import com.chariotit.uva.sc.qdsl.ast.node.constant.StringConstant;
import com.chariotit.uva.sc.qdsl.ast.node.operator.*;
import com.chariotit.uva.sc.qdsl.ast.node.type.*;
import com.chariotit.uva.sc.qdsl.ast.node.type.BooleanTypeNode;
import com.chariotit.uva.sc.qdsl.ast.node.type.StringTypeNode;
import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import com.chariotit.uva.sc.qdsl.parser.exception.UnknownOptionException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;


public class QLVisitor<T> extends QLBaseVisitor<AstNode> {

    private Integer lineNumber(ParserRuleContext ctx) {
        return ctx.getStart().getLine();
    }

    private Integer columnNumber(ParserRuleContext ctx) {
        return ctx.getStart().getCharPositionInLine();
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        List<FormElement> formElements = new ArrayList<>();

        for (int i = 0; i < ctx.elem().size(); i++) {
            formElements.add(visitElem(ctx.elem(i)));
        }

        return new Form(ctx.label().getText(), formElements, lineNumber(ctx), columnNumber(ctx));
    }


    @Override
    public AstRoot visitForms(QLParser.FormsContext ctx) {
        List<Form> forms = new ArrayList<>();

        for (int i = 0; i < ctx.form().size(); i++) {
            forms.add(visitForm(ctx.form(i)));
        }

        return new AstRoot(forms, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public Label visitLabel(QLParser.LabelContext ctx) {
        return new Label(ctx.WORD().getText(), lineNumber(ctx), columnNumber(ctx));
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

            return new IfBlock(expression, ifElements, elseElements, lineNumber(ctx),
                    columnNumber(ctx));
        }

        return new IfBlock(expression, ifElements, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        // Remove leading and trailing quotes from question
        String question = ctx.STRING().getText().substring(
                1, ctx.STRING().getText().length() - 1
        );
        return new Question(question, lineNumber(ctx), columnNumber(ctx));
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
        return new MoneyConstant(Float.parseFloat(ctx.NUMBER(0).getText() + "." + ctx.NUMBER(1)
                .getText()), lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public StringConstant visitString_constant(QLParser.String_constantContext ctx) {
        // Remove leading and trailing quotes from question
        String string = ctx.STRING().getText().substring(
                1, ctx.STRING().getText().length() - 1
        );

        return new StringConstant(string, lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public BooleanConstant visitBoolean_constant(QLParser.Boolean_constantContext ctx) {
        if (ctx.TRUE() != null) {
            return new BooleanConstant(true, lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.FALSE() != null) {
            return new BooleanConstant(false, lineNumber(ctx), columnNumber(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public IntegerConstant visitInteger_constant(QLParser.Integer_constantContext ctx) {
        return new IntegerConstant(Integer.parseInt(ctx.NUMBER().getText()), lineNumber(ctx),
                columnNumber(ctx));
    }

    @Override
    public Operator visitBinop(QLParser.BinopContext ctx) {
        if (ctx.MINUS() != null) {
            return new MinusOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.PLUS() != null) {
            return new PlusOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.MULTIPLY() != null) {
            return new MultiplyOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.DIVIDE() != null) {
            return new DivideOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.EQ() != null) {
            return new EqOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.NEQ() != null) {
            return new NeqOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.GTE() != null) {
            return new GteOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.GT() != null) {
            return new GtOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.LTE() != null) {
            return new LteOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.LT() != null) {
            return new LtOp(lineNumber(ctx), columnNumber(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public Operator visitUnop(QLParser.UnopContext ctx) {
        if (ctx.MINUS() != null) {
            return new MinusOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.PLUS() != null) {
            return new PlusOp(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.NOT() != null) {
            return new NotOp(lineNumber(ctx), columnNumber(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public TypeExpression visitType_expr(QLParser.Type_exprContext ctx) {
        TypeExpression typeExpression = new TypeExpression(visitTypeNode(ctx.typeNode()), lineNumber
                (ctx), columnNumber(ctx));

        if (ctx.expr() != null) {
            typeExpression.setExpression(visitExpr(ctx.expr()));
        }

        return typeExpression;
    }

    @Override
    public TypeNode visitTypeNode(QLParser.TypeNodeContext ctx) {
        if (ctx.BOOLEAN_TYPE() != null) {
            return new BooleanTypeNode(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.INTEGER_TYPE() != null) {
            return new IntegerTypeNode(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.MONEY_TYPE() != null) {
            return new MoneyTypeNode(lineNumber(ctx), columnNumber(ctx));
        } else if (ctx.STRING_TYPE() != null) {
            return new StringTypeNode(lineNumber(ctx), columnNumber(ctx));
        } else {
            throw new UnknownOptionException();
        }
    }

    @Override
    public LineElement visitLine_elm(QLParser.Line_elmContext ctx) {
        return new LineElement(
                visitLabel(ctx.label()),
                visitQuestion(ctx.question()),
                visitType_expr(ctx.type_expr()),
                lineNumber(ctx), columnNumber(ctx)
        );
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
    public LabelExpression visitLabel_expr(QLParser.Label_exprContext ctx) {
        return new LabelExpression(ctx.WORD().getSymbol().getText(), lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public UnOpExpression visitUnop_expr(QLParser.Unop_exprContext ctx) {
        return new UnOpExpression(visitUnop(ctx.unop()), visitExpr(ctx.expr()), lineNumber(ctx), columnNumber(ctx));
    }

    @Override
    public LabelBinOpExpression visitLabel_binop_expr(QLParser.Label_binop_exprContext ctx) {
        return new LabelBinOpExpression(
                visitLabel_expr(ctx.label_expr()),
                visitBinop(ctx.binop()),
                visitExpr(ctx.expr()),
                lineNumber(ctx),
                columnNumber(ctx)
        );
    }

    @Override
    public ConstBinOpExpression visitConst_binop_expr(QLParser.Const_binop_exprContext ctx) {
        return new ConstBinOpExpression(
                visitConstant(ctx.constant()),
                visitBinop(ctx.binop()),
                visitExpr(ctx.expr()),
                lineNumber(ctx),
                columnNumber(ctx)
        );
    }

    @Override
    public AstNode visit(ParseTree tree) {
        return super.visit(tree);
    }
}