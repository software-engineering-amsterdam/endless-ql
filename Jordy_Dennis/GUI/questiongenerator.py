"""
    Generate the questions from the QLast and QLSast
"""

import copy

from QL import *
from QLS import *


class QuestionGenerator:
    def __init__(self, varDict, qlAst, qlsAst, form):
        self.varDict = varDict
        self.qlAst = qlAst
        self.qlsAst = qlsAst
        self.questions = collections.OrderedDict()
        self.form = form

    def updateQuestions(self, initial=False):
        if self.qlsAst:
            self.updateQls(initial)
        else:
            self.updateQl()

    # Update QL questions
    def updateQl(self):
        # get question to add to GUI
        self.questions = collections.OrderedDict()
        self.getQuestions(self.qlAst.form.block)
        insertAfter = ""

        # for every question that is evaluated
        for varName in list(self.questions.keys()):
            # get information of the question
            label = self.questions[varName].getQuestion()
            var_type = self.varDict[varName]['node'].checkTypes()
            value = self.varDict[varName]['node'].evaluate()

            # question already in gui
            questionInGUI = self.form.getQuestionFromSection(varName)
            if questionInGUI:
                # check if assignment node, only show evaluated value
                if type(self.questions[varName]) == AssignmentNode:
                    questionInGUI.setValue(value)

            # Question not in GUI, insert question
            else:
                self.form.insertQuestion(varName, label, var_type, value, insertAfterVarName=insertAfter)
                # disable assignment node input
                if type(self.questions[varName]) == AssignmentNode:
                    self.form.getQuestionFromSection(varName).disableWidget()

            # remove if question is no longer valid
            self.form.deleteInvalidQuestions(self.questions)

            # keep track of where to insert the question
            insertAfter = varName

    """
        Setup QLS
    """

    def updateQls(self, initial):
        # Get questions that need to be in the GUI
        self.questions = collections.OrderedDict()
        self.getQuestions(self.qlAst.form.block)

        # update all pages
        pages = self.qlsAst.getPages()
        for pageKey in pages:
            page = pages[pageKey]
            pageName = page.getName()
            # Create pages
            if not self.form.doesPageExist(pageName):
                self.form.addPage(pageName)

            # initialize page defaults
            defaultDict = {}
            self.updateDefaults(defaultDict, page.defaults)

            # add sections to page
            self.addSection(pageName, page.getSection(), page.defaults, defaultDict)

        # show first page on start-up
        if initial:
            self.form.getPage(next(iter(pages))).show()

    """
        Add Section to a page
    """

    def addSection(self, pageName, sections, pageDefaults, defaultDict, insertAfter=""):
        page = self.form.getPage(pageName)
        for section in sections:
            sectionName = section.getName()

            # create section if not in GUI
            if not self.form.doesSectionExists(sectionName, pageName):
                page.createSection(sectionName)

            # if section has defaults, set them as new defaults
            if section.defaults:
                self.updateDefaults(defaultDict, section.defaults)

            # add or remove questions from gui
            for question in section.getQuestions():
                varName = question.getVarName()
                # If question should be in GUI

                if varName in self.questions:
                    # add question to the section
                    self.addQuestion(question, insertAfter, defaultDict, sectionName, pageName)

                    # keep track of where to insert a potential new question
                    insertAfter = varName
                # delete question, it is no longer valid
                else:
                    self.form.removeQuestionFromSection(varName, sectionName, pageName)

            # Show section when there are question inside
            self.showHideSection(sectionName, pageName)

            # add child sections
            self.addSection(pageName, section.getSections(), pageDefaults, defaultDict, insertAfter)

    """
        Add questions to a Section
    """

    def addQuestion(self, question, insertAfter, defaultDict, sectionName, pageName):
        # copy dict so it can be used only for this question
        defaultDict = copy.copy(defaultDict)

        # apply optional question default
        if question.default:
            self.updateDefaults(defaultDict, [question.default])

        # get question information
        varName = question.getVarName()
        label = self.questions[varName].getQuestion()
        var_type = self.varDict[varName]['node'].checkTypes()
        value = self.varDict[varName]['node'].evaluate()

        # Is question already in GUI
        questionInGUI = self.form.getQuestionFromSection(varName, sectionName, pageName)
        if questionInGUI:
            # update assignment node with new evaluated data
            if type(self.questions[varName]) == AssignmentNode:
                questionInGUI.setValue(value)
        # add new question to GUI
        else:
            # send minVal and maxVal of widget to the question
            if question.widgetType in ['spinbox', 'slider', 'radio']:
                self.form.insertQuestion(varName, label, var_type, value, sectionName, pageName,
                                         insertAfter,
                                         defaultDict, question.widgetType,
                                         minVal=question.widget.minVal, maxVal=question.widget.maxVal)
            else:
                self.form.insertQuestion(varName, label, var_type, value, sectionName, pageName,
                                         insertAfter,
                                         defaultDict, question.widgetType)

            # disable input if it is an assignmentNode
            if type(self.questions[varName]) == AssignmentNode:
                self.form.getQuestionFromSection(varName, sectionName, pageName).disableWidget()

    """
        Show or hide a section depending if a section has questions
    """

    def showHideSection(self, sectionName, pageName):
        section = self.form.getSection(sectionName, pageName)
        if section.questions:
            section.showSection()
        else:
            section.hideSection()

    """
        Update defaults. For example; A bool section default should overwrite a bool page default
        but default that are not overwritten should remain
    """

    def updateDefaults(self, defaultDict, newDefaults):
        for default in newDefaults:
            # get default types
            defaultType = default.type
            defaultWidgetType = default.widgetType
            # If type and widget already exits in defaultDict -> overwrite them
            if defaultType in defaultDict and defaultWidgetType in defaultDict[defaultType]:
                defaultDict[defaultType][defaultWidgetType] = self.getDefaultAttributes(default,
                                                                                        defaultDict[defaultType][
                                                                                            defaultWidgetType])
            # Not yet in defaultDict, initialize them
            else:
                defaultDict[defaultType] = {}
                defaultDict[defaultType][defaultWidgetType] = self.getDefaultAttributes(default)

    def getDefaultAttributes(self, default, attributeDict=None):
        if attributeDict is None:
            attributeDict = {
                'width': 40,
                'font': 'Arial',
                'fontSize': 15,
                'color': '#000000'
            }
        for attribute in default.attributes:
            if attribute.getAttributeType() != 'widget':
                attributeDict[attribute.getAttributeType()] = attribute.getValue()
        return attributeDict

    """
    Create the list of all the questions by recursively looping through the statements and adding them to the dictionary
    """

    def getQuestions(self, block):
        for statement in block:
            # add question
            if type(statement) == QuestionNode:
                self.questions[statement.getVarName()] = statement
            # add assignment
            elif type(statement) == AssignmentNode:
                statement.evaluate(self.varDict)
                self.questions[statement.getVarName()] = statement

            # Check which conditional block to add
            elif type(statement) == ConditionalNode:
                blockVisited = False
                # check if block
                ifBlock = statement.getIf()
                ifExpression = ifBlock.getExpression()
                if ifExpression.evaluate():
                    self.getQuestions(ifBlock.block)
                    blockVisited = True

                # check elif blocks
                if not blockVisited:
                    elifBlocks = statement.getElIf()
                    for elifBlock in elifBlocks:
                        elifExpression = elifBlock.getExpression()
                        if elifExpression.evaluate():
                            self.getQuestions(elifBlock.block)
                            blockVisited = True
                            break

                # check else block
                elseBlock = statement.getElse()
                if elseBlock and not blockVisited:
                    self.getQuestions(elseBlock)

    def getVarDict(self):
        return self.varDict
