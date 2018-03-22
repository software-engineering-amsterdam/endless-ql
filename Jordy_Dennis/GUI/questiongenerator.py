"""
    Generate the questions from the varDict and Ast
"""

from AST import *
from QLS import *


class QuestionGenerator:

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

    # Update QL questions
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

                    questionInGUI = self.form.getQuestionFromSection(varName)
                    # question already in gui
                    if questionInGUI:
                        # check if assignment node, only show evaluated value
                        # don't let the value of an assignment node be changed, only use evaluated data
                        if type(self.questions[varName]) == AssignmentNode:
                            questionInGUI.setValue(value)
                    # Question not in GUI, insert question
                    else:
                        # it is not the initial setup process
                        if not initial:
                            # delete every question that is under the to be inserted if-question
                            for varNameToBeDeleted in toBeDeleteQuestions:
                                self.form.removeQuestionFromSection(varNameToBeDeleted)

                        # insert new question into the GUI
                        self.form.insertQuestion(varName, label, var_type, value)

                    # delete question from the to be deleted list
                    del toBeDeleteQuestions[varName]

                    # remove if question is no longer valid
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
            self.addSection(pageName, pages[page].getSection(), pages[page].defaults)

        # show first page on start-up
        if (initial):
            self.form.getPage(next(iter(pages))).show()

    def addSection(self, pageName, sections, pageDefaults, insertAfter=""):
        page = self.form.getPage(pageName)
        defaultDict = {}
        self.updateDefaults(defaultDict, pageDefaults)
        current_default = pageDefaults
        for section in sections:
            # if section has defaults, set them as net defaults
            if section.defaults:
                self.updateDefaults(defaultDict, section.defaults)
            sectionName = section.getName()
            isSectionEmpty = True
            if not self.form.doesSectionExists(sectionName, pageName):
                page.createSection(sectionName)

            for question in section.getQuestions():
                varName = question.getVarName()
                if varName in self.questions:
                    isSectionEmpty = False

                    if question.default:
                        self.updateDefaults(defaultDict, [question.default])

                    # get data of question
                    label = self.questions[varName].getQuestion()
                    var_type = self.varDict[varName]['node'].checkTypes()
                    value = self.varDict[varName]['node'].evaluate()

                    # Is question already in GUI
                    questionInGUI = self.form.getQuestionFromSection(varName, sectionName, pageName)
                    if questionInGUI:
                        # update assignment node with new evaluated data
                        if type(self.questions[varName]) == AssignmentNode:
                            questionInGUI.setValue(value)
                    # Question not in GUI, Add question to GUI
                    else:
                        if question.widgetType in ['spinbox', 'slider', 'radio']:
                            self.form.insertQuestion(varName, label, var_type, value, sectionName, pageName,
                                                     insertAfter,
                                                     current_default, question.widgetType,
                                                     minVal=question.widget.minVal, maxVal=question.widget.maxVal)
                        else:
                            self.form.insertQuestion(varName, label, var_type, value, sectionName, pageName,
                                                     insertAfter,
                                                     current_default, question.widgetType)
                        # disable input if it is an assigmentNode
                        if type(self.questions[varName]) == AssignmentNode:
                            self.form.getQuestionFromSection(varName, sectionName, pageName).disableWidget()
                    # keep track of where to insert a potential new question
                    insertAfter = varName
                # delete question, it is no longer valid
                else:
                    self.form.removeQuestionFromSection(varName, sectionName, pageName)

            # Show section when there are question inside
            if not isSectionEmpty:
                self.form.getSection(sectionName, pageName).showSection()
            else:
                self.form.getSection(sectionName, pageName).hideSection()

            # add child sections
            self.addSection(pageName, section.getSections(), pageDefaults, insertAfter)
    def updateDefaults(self, defaultDict, newDefaults):
        for default in newDefaults:
            defaultType = default.type
            defaultWidgetType = default.widgetType
            if defaultType in defaultDict and defaultWidgetType in defaultDict[defaultType]:
                # teoveogen
            else:
                defaultDict[defaultType] = {}
                defaultDict[defaultType][defaultWidgetType] = default


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