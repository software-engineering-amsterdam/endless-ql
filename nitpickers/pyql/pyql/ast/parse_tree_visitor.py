from pyql.antlr.QLParser import QLParser
from pyql.antlr.QLVisitor import QLVisitor
from pyql.ast.expression.expressions import *
from pyql.ast.form.block import Block
from pyql.ast.form.form import Form
from pyql.ast.form.ql_statements import *
from util import values
from util.code_location import CodeLocation
from util.types import *


class ParseTreeVisitor(QLVisitor):

    def visitForm(self, ctx: QLParser.FormContext):
        return Form(self.location(ctx), ctx.identifier().accept(self), ctx.block().accept(self))

    def visitIfStatement(self, ctx: QLParser.IfStatementContext):
        return If(self.location(ctx), ctx.expression().accept(self), ctx.block().accept(self))

    def visitIfElseStatement(self, ctx: QLParser.IfElseStatementContext):
        return IfElse(self.location(ctx), ctx.expression().accept(self), ctx.block(0).accept(self),
                      ctx.block(1).accept(self))

    def visitBlock(self, ctx: QLParser.BlockContext):
        return Block(self.location(ctx), [s.accept(self) for s in ctx.statement()])

    def visitStatement(self, ctx: QLParser.StatementContext):
        return self.visitChildren(ctx)

    def visitBasicQuestion(self, ctx: QLParser.BasicQuestionContext):
        return Question(self.location(ctx), ctx.identifier().accept(self), ctx.STRING(),
                        ctx.questionType().accept(self))

    def visitComputedQuestion(self, ctx: QLParser.ComputedQuestionContext):
        return ComputedQuestion(self.location(ctx), ctx.identifier().accept(self), ctx.STRING(),
                                ctx.questionType().accept(self),
                                ctx.expression().accept(self))

    def visitBooleanType(self, ctx: QLParser.BooleanTypeContext):
        return Boolean()

    def visitStringType(self, ctx: QLParser.StringTypeContext):
        return String()

    def visitIntegerType(self, ctx: QLParser.IntegerTypeContext):
        return Integer()

    def visitDecimalType(self, ctx: QLParser.DecimalTypeContext):
        return Decimal()

    def visitMoneyType(self, ctx: QLParser.MoneyTypeContext):
        return Money()

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    def visitOrExpression(self, ctx: QLParser.OrExpressionContext):
        if ctx.getChildCount() > 1:
            return Or(self.location(ctx), ctx.andExpression(0).accept(self), ctx.andExpression(1).accept(self))
        return self.visitChildren(ctx)

    def visitAndExpression(self, ctx: QLParser.AndExpressionContext):
        if ctx.getChildCount() > 1:
            return And(self.location(ctx), ctx.relExpression(0).accept(self), ctx.relExpression(1).accept(self))
        return self.visitChildren(ctx)

    def visitRelExpression(self, ctx: QLParser.RelExpressionContext):
        if ctx.getChildCount() > 1:
            return self.binaryExpressionFactory(self.location(ctx), ctx.addExpression(0).accept(self),
                                                ctx.addExpression(1).accept(self), self.binaryOperator(ctx))
        return self.visitChildren(ctx)

    def visitAddExpression(self, ctx: QLParser.AddExpressionContext):
        if ctx.getChildCount() > 1:
            mulExpressions = [m.accept(self) for m in ctx.mulExpression()]
            operators = [o.accept(self) for o in ctx.addOperator()]
            return self.buildMultiaryExpression(self.location(ctx), mulExpressions, operators)
        return self.visitChildren(ctx)

    def visitAddOperator(self, ctx: QLParser.AddOperatorContext):
        return ctx.getText()

    def visitMulExpression(self, ctx: QLParser.MulExpressionContext):
        if ctx.getChildCount() > 1:
            unExpressions = [m.accept(self) for m in ctx.unExpression()]
            operators = [o.accept(self) for o in ctx.mulOperator()]
            return self.buildMultiaryExpression(self.location(ctx), unExpressions, operators)
        return self.visitChildren(ctx)

    def visitMulOperator(self, ctx: QLParser.MulOperatorContext):
        return ctx.getText()

    def visitNegNotUnExpression(self, ctx: QLParser.NegNotUnExpressionContext):
        return self.unaryExpressionFactory(self.location(ctx), ctx.unExpression().accept(self),
                                           ctx.getChild(0).getText())

    def visitPrimaryUnExpression(self, ctx: QLParser.PrimaryUnExpressionContext):
        return self.visitChildren(ctx)

    def visitPrimary(self, ctx: QLParser.PrimaryContext):
        expression = ctx.expression()
        if expression:
            return expression.accept(self)
        return self.visitChildren(ctx)

    def visitMoneyLiteral(self, ctx: QLParser.MoneyLiteralContext):
        return Literal(self.location(ctx), values.MoneyValue(self.trimLeadingZeros(ctx.getText()[1:])))

    def visitDecimalLiteral(self, ctx: QLParser.DecimalLiteralContext):
        return Literal(self.location(ctx), values.DecimalValue(self.trimLeadingZeros(ctx.getText())))

    def visitIntLiteral(self, ctx: QLParser.IntLiteralContext):
        return Literal(self.location(ctx), values.IntegerValue(self.trimLeadingZeros(ctx.getText())))

    def visitStringLiteral(self, ctx: QLParser.StringLiteralContext):
        return Literal(self.location(ctx), values.StringValue(ctx.getText()))

    def visitBoolLiteral(self, ctx: QLParser.BoolLiteralContext):
        value = ctx.getText() == "true"
        return Literal(self.location(ctx), values.BooleanValue(value))

    def visitIdentifier(self, ctx: QLParser.IdentifierContext):
        return Identifier(self.location(ctx), ctx.getText())

    def location(self, context):
        return CodeLocation(context.start.line, context.start.column)

    def buildMultiaryExpression(self, location, expressions, operators):
        if len(expressions) == 2:
            return self.binaryExpressionFactory(location, expressions[0], expressions[1], operators[0])
        operator = operators.pop()
        expression = expressions.pop()
        return self.binaryExpressionFactory(location, self.buildMultiaryExpression(location, expressions, operators),
                                            expression, operator)

    def trimLeadingZeros(self, number):
        if str(number) == "0":
            return number
        return number.lstrip("0")

    def binaryOperator(self, ctx):
        return ctx.getChild(1).getText()

    def unaryExpressionFactory(self, location, expression, operator):
        switcher = {
            "!": Not(location, expression),
            "-": Subtraction(location, Literal(location, values.IntegerValue("0")), expression)
        }
        return switcher.get(operator)

    def binaryExpressionFactory(self, location, left, right, operator):
        switcher = {
            "*": Multiplication,
            "/": Division,
            "+": Addition,
            "-": Subtraction,
            "<": LessThan,
            ">": GreaterThan,
            "<=": LessThanOrEqual,
            ">=": GreaterThanOrEqual,
            "==": Equals,
            "!=": NotEquals
        }
        return switcher.get(operator)(location, left, right)
