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

    def updateQuestions(self, initial=False):
        if self.astQLS:
            self.updateQls(initial)
        else:
            self.updateQl(initial)

    # Get a list of all the questions that need to be rendered (depending on the evaluation of the statements)
    def updateQl(self, initial=False):
        self.questions = collections.OrderedDict()
        self.getQuestions(self.ast.form.block)
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
                    # don't let the value of an assignment node be changed, only use evaluated data
                    if (type(self.questions[varName]) == AssignmentNode):
                        if (self.form.getQuestionFromSection(varName, 'default', 'default')):
                            self.form.getQuestionFromSection(varName, 'default', 'default').setValue(value)

                    # if the question is not yet in the GUI
                    if (not self.form.isQuestionOnPage(varName)):

                        # it is not the initial setup process
                        if not initial:
                            # delete every question that is under the to be inserted if-question
                            for varNameToBeDeleted in toBeDeleteQuestions:
                                self.form.removeQuestionFromSection('default', 'default', varNameToBeDeleted)

                        # insert new question into the GUI
                        self.form.insertQuestion("", varName, 'default', label, var_type, value)
                    # delete question from the to be deleted list
                    del toBeDeleteQuestions[varName]

                    # remove if question if no longer valid
                    self.form.deleteInvalidQuestions(self.questions)

    """
        Setup QLS
    """

    def updateQls(self, initial):
        self.questions = collections.OrderedDict()
        self.getQuestions(self.ast.form.block)
        pages = self.astQLS.getPages()
        for page in pages:
            pageName = pages[page].getName()
            if not self.form.doesPageExist(pageName):
                self.form.addPage(pages[page].name)

            # add sections and questions
            self.addSection(pageName, pages[page].getSection())

        # show first page
        if (initial):
            self.form.getPage(next(iter(pages))).show()

    def addSection(self, pageName, sections, insertAfter=""):
        page = self.form.getPage(pageName)
        for section in sections:
            sectionName = section.getName()
            isSectionEmpty = True
            if not self.form.doesSectionExists(sectionName, pageName):
                page.createSection(sectionName)

            for question in section.getQuestions():
                varName = question.getVarName()
                if (varName in self.questions):
                    isSectionEmpty = False
                    # get data of question
                    label = self.questions[varName].getQuestion()
                    var_type = self.varDict[varName]['node'].checkTypes()
                    value = self.varDict[varName]['node'].evaluate()

                    # don't let the value of an assignment node be changed, only use evaluated data
                    if (type(self.questions[varName]) == AssignmentNode):
                        if (self.form.getQuestionFromSection(varName, sectionName, pageName)):
                            self.form.getQuestionFromSection(varName, sectionName, pageName).setValue(value)


                    # insert new question
                    if not self.form.isQuestionOnPage(varName, sectionName, pageName):
                        self.form.insertQuestion(insertAfter, varName, sectionName, label, var_type, value, pageName, question.default)
                        if (type(self.questions[varName]) == AssignmentNode):
                            self.form.getQuestionFromSection(varName, sectionName, pageName).disableWidget()

                    insertAfter = varName
                # delete question
                else:
                    self.form.removeQuestionFromSection(pageName, sectionName, varName)

            if not isSectionEmpty:
                self.form.getSection(sectionName, pageName).showSection()
            else:
                self.form.getSection(sectionName, pageName).hideSection()

            # add child sections
            self.addSection(pageName, section.getSections(), insertAfter)

    # Create the list of all the questions by recursively looping through the statements and adding them to te dictionairy
    def getQuestions(self, block):
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
                    self.getQuestions(ifblock.block)
                    visited = True

                # check elif block
                if (not visited):
                    elifBlocks = statement.getElIf()
                    for elifBlock in elifBlocks:
                        elif_exp = elifBlock.getExpression()
                        if (elif_exp.evaluate()):
                            self.getQuestions(elifBlock.block)
                            visited = True
                            break

                # check else block
                elseBlock = statement.getElse()
                if (elseBlock and not visited):
                    self.getQuestions(elseBlock)

    def getVarDict(self):
        return self.varDict

def printDict(dic):
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(dic)
