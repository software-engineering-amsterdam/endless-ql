from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor
from AST import *
import logging

class Visitor(QLGrammarVisitor):
    def __init__(self):
        self.program = {}
        self.QLNode = QLNode()
        # used to log debug self.logger.debugs
        # set to logging.DEBUG to show debug messages
        logging.basicConfig(level=logging.DEBUG)
        self.logger = logging.getLogger(__name__)


    # Visit a parse tree produced by QLGrammarParser#form.
    def visitForm(self, ctx:QLGrammarParser.FormContext):
        self.logger.debug("FORM")
        
        # create formNode
        formName = ctx.ID().getText()
        formNode = FormNode(formName, ctx.start.line)

        # visit the block
        statements = self.visit(ctx.block())
        
        # add all the statements to the block
        formNode.addStatements(statements)
        
        self.QLNode.addForm(formNode)


    # Visit a parse tree produced by QLGrammarParser#block.
    def visitBlock(self, ctx:QLGrammarParser.BlockContext):
        self.logger.debug("BLOCK")

        # collect all the statements into one block
        block = []
        for i in ctx.statement():
            block.append(self.visit(i))
        return block


    # Visit a parse tree produced by QLGrammarParser#statement.
    def visitStatement(self, ctx:QLGrammarParser.StatementContext):
        self.logger.debug("STATEMENT")
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#question.
    def visitQuestion(self, ctx:QLGrammarParser.QuestionContext):
        self.logger.debug("QUESTION")

        #collect information about the question
        question = ctx.STRING().getText()
        var = ctx.ID().getText()
        varType = self.visit(ctx.types())
        
        questionN = QuestionNode(question, var, varType, ctx.start.line)      
        
        return questionN


    # Visit a parse tree produced by QLGrammarParser#assignment.
    def visitAssignment(self, ctx:QLGrammarParser.AssignmentContext):
        self.logger.debug("ASSIGMENT")
        question = ctx.STRING().getText()
        varName = ctx.ID().getText()
        varType = ctx.types().getText()
        expr = self.visit(ctx.expression())
        assignNode = AssignmentNode(question, varName, varType, expr, ctx.start.line)

        return assignNode


    # Visit a parse tree produced by QLGrammarParser#expression.
    def visitExpression(self, ctx:QLGrammarParser.ExpressionContext):
        self.logger.debug("Exp")
        print("Left: "+ str(ctx.left))
        self.visitChildren(ctx)
        return "EXPRESSION"

    def visitLiteral(self, ctx:QLGrammarParser.LiteralContext):
        self.logger.debug("LITERAL")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLGrammarParser#unaryexp.
    def visitUnaryexp(self, ctx:QLGrammarParser.UnaryexpContext):
        # print(ctx.NOT().getText())
        return self.visitChildren(ctx)



    # Visit a parse tree produced by QLGrammarParser#conditional.
    def visitConditional(self, ctx:QLGrammarParser.ConditionalContext):
        self.logger.debug("CONDITIONAL")

        #visit if_block
        if_condition = self.visit(ctx.if_conditional())

        #create conditionalNode
        conditionalN = ConditionalNode(if_condition)

        #visit optional elif
        if(ctx.elif_conditional()):
            for i in ctx.elif_conditional():
                elif_condition = self.visit(i)
                conditionalN.addElifCondition(elif_condition)
        
        #visit optional else
        if(ctx.else_conditional()):
            else_condition = self.visit(ctx.else_conditional())
            conditionalN.addElseChild(else_condition)

        return conditionalN


    # Visit a parse tree produced by QLGrammarParser#if_conditional.
    def visitIf_conditional(self, ctx:QLGrammarParser.If_conditionalContext):
        self.logger.debug("IF")

        #visit condition of the if
        condition = self.visit(ctx.expression())
        conditionN = ConditionNode(condition, ctx.start.line);

        #visit block of if
        if_questions = self.visit(ctx.block())
        conditionN.addQuestions(if_questions)
        
        return conditionN
       


    # Visit a parse tree produced by QLGrammarParser#elif_conditional.
    def visitElif_conditional(self, ctx:QLGrammarParser.Elif_conditionalContext):
        self.logger.debug("ELIF")
        
        #visit condition of elif
        condition = self.visit(ctx.expression())
        conditionN = ConditionNode(condition, ctx.start.line);
        
        #visit block of elif
        elif_questions = self.visit(ctx.block())
        conditionN.addQuestions(elif_questions)
        
        return conditionN


    # Visit a parse tree produced by QLGrammarParser#else_conditional.
    def visitElse_conditional(self, ctx:QLGrammarParser.Else_conditionalContext):
        self.logger.debug("ELSE")

        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#types.
    def visitTypes(self, ctx:QLGrammarParser.TypesContext):
        self.logger.debug("TYPES")

        return ctx.getText()


