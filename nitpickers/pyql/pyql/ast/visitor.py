from pyql.antlr.QLVisitor import QLVisitor
from pyql.antlr.QLParser import QLParser
from pyql.ast.form.question_statement import QuestionStatement
from pyql.ast.form.block import Block
from pyql.ast.form.if_statement import IfStatement
from pyql.ast.code_location import CodeLocation
from pyql.ast.form.form import Form
from pyql.ast.expression.expressions import *

# TODO check if can get rid of 'if getChildCount() > 1'


class ParseTreeVisitor(QLVisitor):

    def visitForm(self, ctx: QLParser.FormContext):
        identifier = ctx.identifier().accept(self)
        location = self.location(ctx)
        block = ctx.block().accept(self)
        return Form(identifier, location, block)

    def visitConditionalBlock(self, ctx: QLParser.Conditional_blockContext):
        return IfStatement(self.location(ctx), ctx.expression().accept(self), ctx.block().accept(self))

    def visitBlock(self, ctx: QLParser.BlockContext):
        return Block(self.location(ctx), [s.accept(self) for s in ctx.statement()])

    def visitStatement(self, ctx: QLParser.StatementContext):
        return self.visitChildren(ctx)

    def visitQuestion(self, ctx: QLParser.QuestionContext):
        return QuestionStatement(self.location(ctx), ctx.identifier().accept(self), ctx.STR(), ctx.question_type().accept(self))

    def visitQuestionType(self, ctx: QLParser.Question_typeContext):
        return ctx.getText()

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    def visitOrExpression(self, ctx: QLParser.OrExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.andExpression(0).accept(self)
            right = ctx.andExpression(1).accept(self)
            return Or(self.location(ctx), left, right)
        return self.visitChildren(ctx)

    def visitAndExpression(self, ctx: QLParser.AndExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.relExpression(0).accept(self)
            right = ctx.relExpression(1).accept(self)
            return And(self.location(ctx), left, right)
        return self.visitChildren(ctx)

    def visitRelExpression(self, ctx: QLParser.RelExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.addExpression(0).accept(self)
            right = ctx.addExpression(1).accept(self)
            location = self.location(ctx)
            switcher = {
                "<": LessThan(location, left, right),
                ">": GreaterThan(location, left, right),
                "<=": LessThanOrEqual(location, left, right),
                ">=": GreaterThanOrEqual(location, left, right),
                "==": Equals(location, left, right),
                "!=": NotEquals(location, left, right)
            }
            return switcher.get(self.operator(ctx))
        return self.visitChildren(ctx)

    def visitAddExpression(self, ctx: QLParser.AddExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.mulExpression(0).accept(self)
            right = ctx.mulExpression(1).accept(self)
            location = self.location(ctx)
            switcher = {
                "+": Addition(location, left, right),
                "-": Subtraction(location, left, right),
            }
            return switcher.get(self.operator(ctx))
        return self.visitChildren(ctx)

    def visitMulExpression(self, ctx: QLParser.MulExpressionContext):
        if ctx.getChildCount() > 1:
            left = ctx.unExpression(0).accept(self)
            right = ctx.unExpression(1).accept(self)
            location = self.location(ctx)
            switcher = {
                "*": Multiplication(location, left, right),
                "/": Division(location, left, right),
            }
            return switcher.get(self.operator(ctx))
        return self.visitChildren(ctx)

    def visitUnExpression(self, ctx: QLParser.UnExpressionContext):
        return self.visitChildren(ctx)

    def visitLiteral(self, ctx: QLParser.LiteralContext):
        return ctx.getText()

    def visitIdentifier(self, ctx: QLParser.IdentifierContext):
        return ctx.getText()

    def visitMoney(self, ctx: QLParser.MoneyContext):
        print("visit money")

    def location(self, context):
        return CodeLocation(context.start.line, context.start.column)

    def operator(self, ctx):
        return ctx.getChild(1).getText()
