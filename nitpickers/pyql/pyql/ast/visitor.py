from pyql.antlr.QLVisitor import QLVisitor
from pyql.antlr.QLParser import QLParser
from pyql.ast.form.question_statement import QuestionStatement
from pyql.ast.form.block import Block
from pyql.ast.form.if_statement import IfStatement
from pyql.ast.code_location import CodeLocation


class ParseTreeVisitor(QLVisitor):

    def visitForm(self, ctx: QLParser.FormContext):
        block = ctx.block().accept(self)
        identifier = ctx.identifier().accept(self)
        location = self.location(ctx)
        print("visit form")

    def visitConditional_block(self, ctx: QLParser.Conditional_blockContext):
        return IfStatement(self.location(ctx), ctx.expression(), ctx.block().accept(self))

    def visitBlock(self, ctx: QLParser.BlockContext):
        statements = [s.accept(self) for s in ctx.statement()]
        return Block(self.location(ctx), statements)

    def visitStatement(self, ctx: QLParser.StatementContext):
        return self.visitChildren(ctx)

    def visitQuestion(self, ctx: QLParser.QuestionContext):
        return QuestionStatement(self.location(ctx), ctx.identifier().accept(self), ctx.STR(), ctx.question_type().accept(self))

    def visitQuestion_type(self, ctx: QLParser.Question_typeContext):
        return ctx.getText()

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        print("visit expression")

    def visitOrExpression(self, ctx: QLParser.OrExpressionContext):
        print("visit or expression")

    def visitAndExpression(self, ctx: QLParser.AndExpressionContext):
        print("visit and expressino")

    def visitRelExpression(self, ctx: QLParser.RelExpressionContext):
        print("visit relational expression")

    def visitAddExpression(self, ctx: QLParser.AddExpressionContext):
        print("visit add expression")

    def visitMulExpression(self, ctx: QLParser.MulExpressionContext):
        print("visit multiplication expression")

    def visitUnExpression(self, ctx: QLParser.UnExpressionContext):
        print("visit unary expression")

    def visitLiteral(self, ctx: QLParser.LiteralContext):
        return ctx.getText()

    def visitIdentifier(self, ctx: QLParser.IdentifierContext):
        return ctx.getText()

    def visitMoney(self, ctx: QLParser.MoneyContext):
        print("visit money")

    def location(self, context):
        return CodeLocation(context.start.line, context.start.column)
