"""
    Any form GUI object is a frame that contains a header (the title of the entire form),
    a button frame, and a contents frame.

    The button frame contains buttons for the pages, if there is no QLS, the button frame
    will contain a single button called 'default', but the menu will be hidden

    The contents frame contains all of the pages that can be defined in QLS, as a button is
    pressed, the correct page will be lifted for display
"""

from .gui_imports import *
from .form_scroll_frame import ScrollFrameGui
from .form_question import Question
from .form_page import Page
import copy

class FormGui:

    """
        Create the header, buttonframe, and content frame, also set the question generator.

        If QLS is enabled, a menu for the buttons will be created, if it is disabled, a default frame
        will be created
    """
    def __init__(self, parent, questionGenerator, header="No Header Text", color="orange", qls=False):
        self.frame = create_frame(parent, color)
        self.frame.pack(expand=True, fill='both')
        self.headerFrame = None
        self.createHeader(header, parent=self.frame)

        self.pages = {}
        self.buttonFrame = create_frame(self.frame, background='blue')

        if qls:
            self.buttonFrame.pack(side="top", fill="x", expand=False)

        self.contents = create_frame(self.frame, background='black')
        self.contents.pack(side="top", fill="both", expand=True)

        self.questions = []
        self.name = header
        self.questionGenerator = questionGenerator

        if qls == False:
            self.addPage()

    """
        Create the header with possible QLS formatting
    """
    def createHeader(self, header, parent=None, boxWidth=200, boxHeight=2, fontType='Arial', fontSize=15,
                      fontColor='blue'):
        headerFrame = create_frame(parent)
        text = Text(parent, height=boxHeight, width=boxWidth)
        headerFont = Font(family=fontType, size=fontSize, weight='bold')
        text.tag_configure('header_conf', font=headerFont)
        text.insert(INSERT, header, 'header_conf')
        text.config(state=DISABLED)
        text.pack(anchor=NW)
        self.headerFrame = headerFrame

    """
        Add a new page to the content frame and place a button within the button frame. 
        The new page is also added to our dictionairy of pages (used for adding and removing question)
    """
    def addPage(self, header='default', color='green'):
        new_page = Page(self.contents, self.questionGenerator, color=color)
        new_page.place(self.contents, x=0, y=0, relwidth=1, relheight=1)
        button = Button(self.buttonFrame, text=header, command=new_page.show)
        button.pack(side="left")
        self.pages[header] = new_page
        return new_page

    def doesPageExist(self, pageName):
        if pageName in self.pages:
            return True
        return False

    """
        Add questions to a given page, if no page is given (QL), the default page will be used
    """
    def addQuestionToPage(self, varName, questionText="Default Question", questionType=bool, value=False, pageName='default'):
        page = self.pages[pageName]
        page.addQuestion(varName, questionText, questionType, value)

    """
        Remove questions from a given page, if no page is given (QL), the default page will be used
    """
    def removeQuestionFromPage(self, varName, pageName='default'):
        page = self.pages[pageName]
        page.removeQuestion(varName)

    """
        Checks if question is already on a page
    """
    def isQuestionOnPage(self, varName, pageName='default'):
        page = self.pages[pageName]
        for question in page.questions:
            if question.varName == varName:
                return True
        return False

    """
        Returns a question object from a page
    """
    def getQuestionFromPage(self, varName, pageName='default'):
        page = self.pages[pageName]
        for question in page.questions:
            if question.varName == varName:
                return question
        return None

    """
        Deletes question that are no longer valid, i.e. questions in a if, elif or else
    """
    def deleteInvalidQuestions(self, questions, pageName='default'):
        page = self.pages[pageName]
        for questionOnPage in page.questions:
            if (questionOnPage.varName not in questions):
                self.removeQuestionFromPage(questionOnPage.varName)

    def insertQuestion(self, insertAfterVarName, varName, questionText="Default Question", questionType=bool, value=False, pageName='default'):
        print("insert", varName, " after:",insertAfterVarName)
        questionsAtPage = copy.copy(self.pages[pageName].questions)
        if insertAfterVarName != "":
            for question in self.pages[pageName].questions:
                if question.getVarName() == insertAfterVarName:
                    questionsAtPage.remove(question)
                    break
                else:
                    questionsAtPage.remove(question)
            # delete questions after insert
            for question in questionsAtPage:
                # print("QUESTION THAT HAS TO BE DELETED:", question.getVarName())
                self.removeQuestionFromPage(question.getVarName(), pageName)
        # insert question
        self.addQuestionToPage(varName, questionText, questionType, value, pageName)

        if insertAfterVarName != "":
            # restore old questions
            for question in questionsAtPage:
                tmpVarName = question.getVarName()
                tmpQuestionText = question.questionText
                tmpQuestionType = question.questionType
                tmpValue = question.value
                self.addQuestionToPage(tmpVarName, tmpQuestionText, tmpQuestionType, tmpValue, pageName)

    """
        Get all of the answers (and assignments) from the varDict, and download them
    """
    def getAnswers(self):
        answers = {}
        varDict = self.questionGenerator.getVarDict()
        for varName in varDict:
            answers[varName] = varDict[varName]['node'].evaluate()
        return answers

    """
        Usefull getter and setter functions
    """
    def getHeader(self):
        return self.headerFrame

    def getFrame(self):
        return self.frame

    def getContents(self):
        return self.contents

    def getText(self):
        return self.name
