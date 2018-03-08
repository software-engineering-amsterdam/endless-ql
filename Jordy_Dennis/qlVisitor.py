from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor
from AST import *
import logging
import sys


class QLVisitor(QLGrammarVisitor):
    def __init__(self):
        self.program = {}
        self.QLAst = QLAst()

        self.logger = logging.getLogger(__name__)

    def getAst(self):
        return self.QLAst

    # Visit a parse tree produced by QLGrammarParser#form.
    def visitForm(self, ctx: QLGrammarParser.FormContext):
        self.logger.debug("FORM")

        # create formNode
        formName = ctx.ID().getText()
        formNode = FormNode(formName, ctx.start.line)

        # visit the block
        statements = self.visit(ctx.block())

        # add all the statements to the block
        formNode.addStatements(statements)

        self.QLAst.addForm(formNode)

    # Visit a parse tree produced by QLGrammarParser#block.
    def visitBlock(self, ctx: QLGrammarParser.BlockContext):
        self.logger.debug("BLOCK")

        # collect all the statements into one block
        block = []
        for i in ctx.statement():
            block.append(self.visit(i))
        return block

    # Visit a parse tree produced by QLGrammarParser#statement.
    def visitStatement(self, ctx: QLGrammarParser.StatementContext):
        self.logger.debug("STATEMENT")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLGrammarParser#question.
    def visitQuestion(self, ctx: QLGrammarParser.QuestionContext):
        self.logger.debug("QUESTION")

        # collect information about the question
        question = ctx.STRING().getText()
        varName = ctx.ID().getText()
        varType = self.visit(ctx.types())

        varNode = VarNode(varName, varType, ctx.start.line)
        questionN = QuestionNode(question, varNode, ctx.start.line)

        return questionN

    """ --------------------------- ASSIGNMENT AND VARIABLE DECLARATION------------------------- """

    # Visit a parse tree produced by QLGrammarParser#assignment.
    def visitAssignment(self, ctx: QLGrammarParser.AssignmentContext):
        self.logger.debug("ASSIGMENT")
        question = ctx.STRING().getText()
        varName = ctx.ID().getText()
        varType = mapStringToType(ctx.types().getText())
        expr = self.visit(ctx.expression())

        varNode = VarNode(varName, varType, ctx.start.line, True)
        assignNode = AssignmentNode(question, varNode, expr, ctx.start.line)

        return assignNode

    """ --------------------------- EXPRESSION ------------------------------------------------- """

    # Visit a parse tree produced by QLGrammarParser#expression.
    def visitExpression(self, ctx: QLGrammarParser.ExpressionContext):
        self.logger.debug("EXP")
        # this is a binop
        if (ctx.left and ctx.right):
            left = self.visit(ctx.left)
            right = self.visit(ctx.right)
            op = getOp(ctx)
            binNode = BinaryNode(left, right, op, ctx.start.line)
            return binNode

        elif (ctx.left):
            return self.visit(ctx.left)
        return self.visitChildren(ctx)

    def visitLiteral(self, ctx: QLGrammarParser.LiteralContext):
        self.logger.debug("LITERAL")
        litVal, litType = getLiteralValue(ctx)
        litNode = LiteralNode(litVal, litType, ctx.start.line)
        return litNode

    # Visit a parse tree produced by QLGrammarParser#unaryexp.
    def visitUnaryexp(self, ctx: QLGrammarParser.UnaryexpContext):
        self.logger.debug("UNARY")
        expr = self.visit(ctx.expression())

        op = ctx.NOT().getText()

        unaryNode = UnaryNode(expr, op, ctx.start.line)
        return unaryNode

    """ --------------------------- CONDITIONAL --------------------------------------------- """

    # Visit a parse tree produced by QLGrammarParser#conditional.
    def visitConditional(self, ctx: QLGrammarParser.ConditionalContext):
        self.logger.debug("CONDITIONAL")

        # visit if_block
        if_condition = self.visit(ctx.if_conditional())

        # create conditionalNode
        conditionalN = ConditionalNode(if_condition, ctx.start.line)

        # visit optional elif
        if (ctx.elif_conditional()):
            for i in ctx.elif_conditional():
                elif_condition = self.visit(i)
                conditionalN.addElifCondition(elif_condition)

        # visit optional else
        if (ctx.else_conditional()):
            else_condition = self.visit(ctx.else_conditional())
            conditionalN.addElseChild(else_condition)

        return conditionalN

    # Visit a parse tree produced by QLGrammarParser#if_conditional.
    def visitIf_conditional(self, ctx: QLGrammarParser.If_conditionalContext):
        self.logger.debug("IF")

        # visit condition of the if
        condition = self.visit(ctx.expression())
        conditionN = ConditionNodeBlock(condition, ctx.start.line);

        # visit block of if
        if_questions = self.visit(ctx.block())
        conditionN.addQuestions(if_questions)
        return conditionN

    # Visit a parse tree produced by QLGrammarParser#elif_conditional.
    def visitElif_conditional(self, ctx: QLGrammarParser.Elif_conditionalContext):
        self.logger.debug("ELIF")

        # visit condition of elif
        condition = self.visit(ctx.expression())
        conditionN = ConditionNodeBlock(condition, ctx.start.line);

        # visit block of elif
        elif_questions = self.visit(ctx.block())
        conditionN.addQuestions(elif_questions)

        return conditionN

    # Visit a parse tree produced by QLGrammarParser#else_conditional.
    def visitElse_conditional(self, ctx: QLGrammarParser.Else_conditionalContext):
        self.logger.debug("ELSE")

        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLGrammarParser#types.
    def visitTypes(self, ctx: QLGrammarParser.TypesContext):
        self.logger.debug("TYPES")

        return mapStringToType(ctx.getText())

    # Visit a parse tree produced by QLGrammarParser#varnode.
    # Type is temporarily none, this node is used to link the variables
    # in a later stage
    def visitVarnode(self, ctx:QLGrammarParser.VarnodeContext):
        self.logger.debug("VARIABLE")
        varName = ctx.ID().getText()

        varNode = VarNode(varName, None, ctx.start.line, False)

        return varNode


# get operator from ctx object
def getOp(ctx):
    op = None
    if (ctx.COMPARE()):
        op = ctx.COMPARE().getText()
    elif (ctx.MATH_OPERATOR_PRIO()):
        op = ctx.MATH_OPERATOR_PRIO().getText()
    elif (ctx.MATH_OPERATOR()):
        op = ctx.MATH_OPERATOR().getText()
    elif (ctx.AND()):
        op = "and"
    elif (ctx.OR()):
        op = "or"
    return op

# For a literal, change the type to a python type
def getLiteralValue(ctx):
    litType = None
    litVal = None
    if (ctx.INT()):
        litType = int
        litVal = ctx.INT()
    elif (ctx.BOOL()):
        litType = bool
        litVal = str(ctx.BOOL()).capitalize()
    elif (ctx.STRING()):
        litType = str
        litVal = ctx.STRING()
    elif (ctx.FLOAT()):
        litType = float
        litVal = ctx.FLOAT()
    elif (ctx.ID()):
        litType = "var"
        litVal = ctx.ID()
    return litVal, litType

# Map string-type to an actual python type
def mapStringToType(obj):
    if obj == 'int' or obj == 'integer':
        return int
    elif obj == 'string' or obj == 'str':
        return str
    elif obj == 'money' or obj == 'float':
        return float
    elif obj == 'bool' or obj == 'boolean':
        return bool
    else:
        print("QL Interpreter error:")
        print("Unknown type " + obj)
        sys.exit(1)

