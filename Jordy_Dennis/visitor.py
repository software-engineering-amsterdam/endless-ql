from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor
from AST.ast_nodes import *

class Visitor(QLGrammarVisitor):
    def __init__(self):
        self.program = {}
        self.QLNode = QLNode()


    # Visit a parse tree produced by QLGrammarParser#form.
    def visitForm(self, ctx:QLGrammarParser.FormContext):
        print("FORM")
        
        temp_form = formNode(ctx.ID().getText())
        value = self.visit(ctx.block())
        
        for i in value:
            temp_form.addChild(i)
        
        self.QLNode.addForm(temp_form)

        return temp_form


    # Visit a parse tree produced by QLGrammarParser#block.
    def visitBlock(self, ctx:QLGrammarParser.BlockContext):
        print("BLOCK")

        temp = []
        for i in ctx.statement():
            temp.append(self.visit(i))
        return temp


    # Visit a parse tree produced by QLGrammarParser#statement.
    def visitStatement(self, ctx:QLGrammarParser.StatementContext):
        print("STATEMENT")
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#question.
    def visitQuestion(self, ctx:QLGrammarParser.QuestionContext):
        print("QUESTION")
        question = ctx.STRING().getText()
        var = ctx.ID().getText()
        varType = ctx.types().getText()
        
        questionN = questionNode(question, var, varType)      
        
        return questionN


    # Visit a parse tree produced by QLGrammarParser#assignment.
    def visitAssignment(self, ctx:QLGrammarParser.AssignmentContext):
        print("ASSIGMENT")
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#expression.
    def visitExpression(self, ctx:QLGrammarParser.ExpressionContext):
        print("EXPRESSION")
        return "EXPRESSION"


    # Visit a parse tree produced by QLGrammarParser#conditional.
    def visitConditional(self, ctx:QLGrammarParser.ConditionalContext):
        print("CONDITIONAL")
        conditionalN = self.visit(ctx.if_conditional())
        if(ctx.elif_conditional()):
            print("HIER")
            for i in ctx.elif_conditional():
                conditionalN.addElifChild(self.visit(i))
        if(ctx.else_conditional()):
            print("ELSE")
            conditionalN.addElseChild(self.visit(ctx.else_conditional()))

 
        print("haha")
        return conditionalN


    # Visit a parse tree produced by QLGrammarParser#if_conditional.
    def visitIf_conditional(self, ctx:QLGrammarParser.If_conditionalContext):
        print("IF")
        condition = self.visit(ctx.expression())
        conditionalN = conditionalNode(condition)
        if_questions = self.visit(ctx.block())
        conditionalN.addIfChild(if_questions)
        
        return conditionalN
       


    # Visit a parse tree produced by QLGrammarParser#elif_conditional.
    def visitElif_conditional(self, ctx:QLGrammarParser.Elif_conditionalContext):
        print("ELIF")
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#else_conditional.
    def visitElse_conditional(self, ctx:QLGrammarParser.Else_conditionalContext):
        print("ELSE")
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#types.
    def visitTypes(self, ctx:QLGrammarParser.TypesContext):
        print("TYPES")
        return self.visitChildren(ctx)

