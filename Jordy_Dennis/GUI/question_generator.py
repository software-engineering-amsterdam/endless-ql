"""
    Generate the questions from the varDict and Ast
"""

import pprint
from AST import *
import collections


class Question_Generator:

    def __init__(self, varDict, ast, form):
        self.varDict = varDict
        self.ast = ast
        self.questions = collections.OrderedDict()
        self.form = form

    def getVarDict(self):
        return self.varDict

    # Get a list of all the questions that need to be rendered (depending on the evaluation of the statements)
    def updateQuestions(self, initial=False):
        self.questions = collections.OrderedDict()
        self.get_questions(self.ast.form.block)
        # deep cody dict. This is used to insert if-questions in the GUI
        toBeDeleteQuestions = self.questions.copy()
        if self.form:
            # for every question that is evaluated
            for varName in self.questions:
                # get information of the question
                label = self.questions[varName].getQuestion()
                var_type = self.varDict[varName]['node'].checkTypes()
                value = self.varDict[varName]['node'].evaluate()
                # check if assignment node, only show evaluated value
                if (type(self.questions[varName]) == AssignmentNode):
                    if (self.getFormQuestion(varName)):
                        self.getFormQuestion(varName).set_value(value)

                # if the question is not yet in the GUI
                if (not self.form.isQuestionOnPage(varName)):

                    # it is not the initial setup process
                    if not initial:
                        # delete every question that is under the to be inserted if-question
                        for varNameToBeDeleted in toBeDeleteQuestions:
                            self.form.removeQuestionFromPage(varNameToBeDeleted)

                    # insert new question into the GUI
                    self.form.addQuestionToPage(varName, label, var_type, value)
                # delete question from the to be deleted list
                del toBeDeleteQuestions[varName]

                # remove if question if no longer valid
                self.form.deleteInvalidQuestions(self.questions)

        return self.questions



    # this function is used to delete question that are no longer valid, i.e. the questions in an if or elif or else block
    def deleteInvalidQuestions(self):
        for question in self.form.questions:
            if (question.varName not in self.questions):
                self.form.removeQuestionFromPage(question.varName)

    def getFormQuestion(self, varName):
        for formQuestion in self.form.questions:
            if formQuestion.varName == varName:
                return formQuestion
        return None

    # Create the list of all the questions by recursively looping through the statements and adding them to te dictionairy
    def get_questions(self, block):
        for statement in block:
            if type(statement) == QuestionNode:
                self.questions[statement.getVarName()] = statement
            elif type(statement) == AssignmentNode:
                statement.evaluate(self.varDict)
                self.questions[statement.getVarName()] = statement


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
