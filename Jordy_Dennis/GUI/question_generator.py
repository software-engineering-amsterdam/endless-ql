"""
    Generate the questions from the varDict and Ast
"""

import pprint
from AST import *
import collections


class Question_Generator:

    def __init__(self, varDict, ast):
        self.varDict = varDict
        self.ast = ast
        self.questions = collections.OrderedDict()

    # Get a list of all the questions that need to be rendered (depending on the evaluation of the statements)
    def getQuestionsFromAst(self):
        for form in self.ast.forms:
            self.get_questions(form.block)
        return self.questions

    # Create the list of all the questions by recursively looping through the statements and adding them to te dictionairy
    def get_questions(self, block):
        for statement in block:
            if type(statement) == QuestionNode:
                self.questions[statement.getVarName()] = statement
            elif type(statement) == AssignmentNode:
                statement.evaluate(self.varDict)


            elif type(statement) == ConditionalNode:
                visited = False
                # check if block
                ifblock = statement.getIf();
                if_exp = ifblock.getExpression()

                if (if_exp.evaluate()):
                    self.get_questions(ifblock.block)
                    visited = True

                # check elif block
                if (not visited):
                    elifBlocks = statement.getElIf()
                    for elifBlock in elifBlocks:
                        elif_exp = elifBlock.getExpression()
                        if (elif_exp.evaluate()):
                            self.get_questions(elifBlock.block)
                            visited = True
                            break

                # check else block
                elseBlock = statement.getElse()
                if (elseBlock and not visited):
                    self.get_questions(elseBlock)


def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)
