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
    def updateQuestions(self, initial = False):
        self.questions = collections.OrderedDict()
        self.get_questions(self.ast.form.block)
        # deep cody dict. This is used to insert if-questions in the GUI
        toBeDeleteQuestions = self.questions.copy()
        print(self.ast)
        if self.form:
            # for every question that is evaluated
            for varName in self.questions:
                # get information of the question
                label = self.questions[varName].getQuestion()
                type = self.varDict[varName]['node'].checkTypes()

                value = self.varDict[varName]['node'].evaluate()

                # if the question is not yet in the GUI
                if(not self.isQuestionInForm(varName)):

                    # it is not the initial setup process
                    if not initial:
                        # delete every question that is under the to be inserted if-question
                        for varNameToBeDeleted in toBeDeleteQuestions:
                            self.deleteQuestionInForm(varNameToBeDeleted)

                    #insert new question into the GUI
                    self.form.add_question(varName, label, type, value)
                # delete question from the to be deleted list
                del toBeDeleteQuestions[varName]

                # remove if question if no longer valid
                self.deleteInvalidQuestions()

        return self.questions

    def deleteQuestionInForm(self, varName):
        for formQuestion in self.form.questions:
            if formQuestion.varName == varName:
                formQuestion.frame.destroy()
                self.form.questions.remove(formQuestion)

    def deleteInvalidQuestions(self):
        for question in self.form.questions:
            print(question.question_text)
            if (question.varName not in self.questions):
                question.frame.destroy()
                self.form.questions.remove(question)

    def isQuestionInForm(self, varName):
        for formQuestion in self.form.questions:
            if formQuestion.varName == varName:
                return True
        return False

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
                print(if_exp)


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
