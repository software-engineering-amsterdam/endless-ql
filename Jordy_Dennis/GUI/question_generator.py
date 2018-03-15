"""
    Generate the questions from the varDict and Ast
"""

import pprint
from AST import *
from QLS import *
import collections


class Question_Generator:

    def __init__(self, varDict, ast, astQLS, form):
        self.varDict = varDict
        self.ast = ast
        self.astQLS = astQLS
        self.questions = collections.OrderedDict()
        self.form = form

    # Get a list of all the questions that need to be rendered (depending on the evaluation of the statements)
    def updateQuestions(self, initial=False):
        if(self.astQLS):
            self.qls()
            return
        self.questions = collections.OrderedDict()
        self.get_questions(self.ast.form.block)
        # deep cody dict. This is used to insert if-questions in the GUI
        toBeDeleteQuestions = self.questions.copy()
        if self.form:
            # for every question that is evaluated
            for varName in list(self.questions.keys()):
                if varName in self.questions.keys():
                    # get information of the question
                    label = self.questions[varName].getQuestion()
                    var_type = self.varDict[varName]['node'].checkTypes()
                    value = self.varDict[varName]['node'].evaluate()
                    # check if assignment node, only show evaluated value
                    if (type(self.questions[varName]) == AssignmentNode):
                        if (self.form.getQuestionFromPage(varName)):
                            self.form.getQuestionFromPage(varName).setValue(value)

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

    def qls(self):
        print("UPDATE")
        self.questions = collections.OrderedDict()
        self.get_questions(self.ast.form.block)
        for page in self.astQLS.getPages():
            pageName = page.getName()
            print("PAGE: ", pageName)
            if not self.form.doesPageExist(pageName):
                self.form.addPage(page.name)

            # add sections and questions
            self.addSection(pageName, page.getSection())

    def addSection(self, pageName, sections, prev=""):
        for section in sections:
            for question in section.getQuestions():
                varName = question.getVarName()
                if (varName in self.questions):

                    # get data of question
                    label = self.questions[varName].getQuestion()
                    var_type = self.varDict[varName]['node'].checkTypes()
                    value = self.varDict[varName]['node'].evaluate()

                    # don't let the value of an assignment node be changed, only use evaluated data
                    if (type(self.questions[varName]) == AssignmentNode):
                        if (self.form.getQuestionFromPage(varName, pageName)):
                            self.form.getQuestionFromPage(varName, pageName).setValue(value)

                    # insert new question
                    if not self.form.isQuestionOnPage(varName, pageName):
                        self.form.insertQuestion(prev, varName, label, var_type, value, pageName)
                    prev = varName
                # delete question
                else:
                    self.form.removeQuestionFromPage(varName, pageName)


            # add child sections
            self.addSection(pageName, section.getSections(), prev)

    def getVarDict(self):
        return self.varDict

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)
