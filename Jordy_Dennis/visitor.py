from LexParser.QLGrammarParser import QLGrammarParser
from LexParser.QLGrammarVisitor import QLGrammarVisitor
from AST import *

class Visitor(QLGrammarVisitor):
    def __init__(self):
        self.program = {}
        self.QLNode = QLNode()


    # Visit a parse tree produced by QLGrammarParser#form.
    def visitForm(self, ctx:QLGrammarParser.FormContext):
        print("FORM")
        
        temp_form = FormNode(ctx.ID().getText())
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
        
        questionN = QuestionNode(question, var, varType)      
        
        return questionN


    # Visit a parse tree produced by QLGrammarParser#assignment.
    def visitAssignment(self, ctx:QLGrammarParser.AssignmentContext):
        print("ASSIGMENT")
        # return "ASSIGNMENT"
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#expression.
    def visitExpression(self, ctx:QLGrammarParser.ExpressionContext):
        print("Exp")
        return "EXPRESSION"


    # Visit a parse tree produced by QLGrammarParser#conditional.
    def visitConditional(self, ctx:QLGrammarParser.ConditionalContext):
        print("CONDITIONAL")

        conditionalN = conditionalNode()

        if_condition = self.visit(ctx.if_conditional())
        conditionalN.addIfChild(if_condition)

        if(ctx.elif_conditional()):
            print("HIER")
            for i in ctx.elif_conditional():
                conditionalN.addElifChild(self.visit(i))
        
        if(ctx.else_conditional()):
            print("ELSE")
            else_condition = self.visit(ctx.else_conditional())
            conditionalN.addElseChild(else_condition)

 
        print("haha")
        return conditionalN


    # Visit a parse tree produced by QLGrammarParser#if_conditional.
    def visitIf_conditional(self, ctx:QLGrammarParser.If_conditionalContext):
        print("IF")

        condition = self.visit(ctx.expression())
        conditionN = conditionNode(condition);

        if_questions = self.visit(ctx.block())
        conditionN.addQuestions(if_questions)
        
        return conditionN
       


    # Visit a parse tree produced by QLGrammarParser#elif_conditional.
    def visitElif_conditional(self, ctx:QLGrammarParser.Elif_conditionalContext):
        print("ELIF")
        
        condition = self.visit(ctx.expression())
        conditionN = conditionNode(condition);

        elif_questions = self.visit(ctx.block())
        conditionN.addQuestions(elif_questions)
        
        return conditionN


    # Visit a parse tree produced by QLGrammarParser#else_conditional.
    def visitElse_conditional(self, ctx:QLGrammarParser.Else_conditionalContext):
        print("ELSE")

        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#types.
    def visitTypes(self, ctx:QLGrammarParser.TypesContext):
        print("TYPES")
        return self.visitChildren(ctx)

