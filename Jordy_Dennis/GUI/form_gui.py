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
import collections


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

        self.pages = collections.OrderedDict()
        self.buttonFrame = create_frame(self.frame, background='blue')

        if qls:
            self.buttonFrame.pack(side="top", fill="x", expand=False)

        self.contents = create_frame(self.frame)
        self.contents.pack(side="top", fill="both", expand=True)

        self.questions = []
        self.name = header
        self.questionGenerator = questionGenerator

        if qls == False:
            self.addPage()
            self.pages['default'].createSection()

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

    def getPage(self, pageName):
        return self.pages[pageName]

    """
        Checks if question is already on a page
    """

    def isQuestionOnPage(self, varName, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        return page.isQuestionOnPage(varName, sectionName)

    """
        Returns a question object from a section on a page
    """

    def getQuestionFromSection(self, varName, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        return page.getQuestionFromSection(varName, sectionName)

    """
        Deletes question that are no longer valid, i.e. questions in a if, elif or else
    """

    def deleteInvalidQuestions(self, questions, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        page.deleteInvalidQuestions(questions, sectionName)

    def insertQuestion(self, varName, questionText, questionType, value, sectionName='default', pageName='default',
                       insertAfterVarName="", defaults=None):
        page = self.pages[pageName]
        page.addQuestionToSection(sectionName, varName, questionText, questionType, value, insertAfterVarName, defaults)

    def removeQuestionFromSection(self, varName, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        page.removeQuestionFromSection(sectionName, varName)

    def doesSectionExists(self, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        for section in page.sections:
            if section.getName() == sectionName:
                return True
        return False

    def getSection(self, sectionName='default', pageName='default'):
        page = self.pages[pageName]
        for section in page.sections:
            if section.getName() == sectionName:
                return section
        return None

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
