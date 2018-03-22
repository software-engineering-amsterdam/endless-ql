"""
    Pages can be added to a parent class and consist of a scrollFrame (contents), to which
    questions can be added or removed.

    We track a list of questions, in case questions need to be removed or added
"""
from .gui_imports import *
from .form_question import Question
from .form_scroll_frame import ScrollFrameGui
from .form_question import Question
import copy
import QLS


class Section():

    def __init__(self, parent, questionGenerator, page_header='default', color='green'):
        self.questionGenerator = questionGenerator
        self.name = page_header
        scrollFrame = ScrollFrameGui(parent)
        self.contents = scrollFrame.get_contents()

        headerFrame =  create_frame(scrollFrame.get_contents())
        text = Text(scrollFrame.get_contents(), height=2, width=100)
        headerFont = Font(family="Arial", size=15, weight='bold')
        text.tag_configure('header_conf', font=headerFont)
        text.insert(INSERT, page_header, 'header_conf')
        text.config(state=DISABLED)
        text.pack(anchor=NW, expand=True, fill="both")
        self.text = text
        self.scrollFrame = scrollFrame
        self.headerFrame = headerFrame
        self.questions = []

    """
        Lifts the frame in case the menu button of the frame is pressed
    """

    def show(self):
        self.frame.lift()

    def hideSection(self):
        self.scrollFrame.canvas.pack_forget()

    def showSection(self):
        self.scrollFrame.canvas.pack(expand=True, fill='both')

    """
        Place the frame inside parent frame
    """

    def place(self, parent, x=0, y=0, relwidth=1, relheight=1):
        self.frame.place(in_=parent, x=x, y=y, relwidth=relwidth, relheight=relheight)

    """
        Add a question from the question generator
    """
    def addQuestion(self, questionGenerator, varName, questionText, questionType, value, defaults=None, widgetType='default', **kwargs):
        width = 200
        color = 'black'
        font = 'Arial'
        fontSize = '15'
        print(defaults)
        print(questionType)
        if questionType in defaults and widgetType in defaults[questionType]:
                font = defaults[questionType][widgetType]['font']
                fontSize = defaults[questionType][widgetType]['fontSize']
                width = defaults[questionType][widgetType]['width']
                color = defaults[questionType][widgetType]['color']
        elif questionType in defaults:
            if None in defaults[questionType]:
                font = defaults[questionType][None]['font']
                fontSize = defaults[questionType][None]['fontSize']
                width = defaults[questionType][None]['width']
                color = defaults[questionType][None]['color']

        minVal = 0
        maxVal = 1
        if kwargs:
            minVal = kwargs['minVal']
            maxVal = kwargs['maxVal']


        q = Question(self.contents, questionGenerator, varName, questionText, questionType, value, width=width,
                     fontType=font, fontSize=fontSize, color=color, widgetType=widgetType, minVal=minVal, maxVal=maxVal)
        self.questions.append(q)

    def insertQuestion(self, prev, questionGenerator, varName, questionText, questionType, value, defaults=None, widgetType = 'default', **kwargs):
        tmpQuestions = copy.copy(self.questions)
        # first question
        if prev == "":
            self.addQuestion(questionGenerator, varName, questionText, questionType, value, defaults, widgetType, **kwargs)
        else:
            for question in self.questions:
                if question.getVarName() == prev:
                    tmpQuestions.remove(question)
                    break
                else:
                    tmpQuestions.remove(question)

            # delete questions after insert
            for question in tmpQuestions:
                # print("QUESTION THAT HAS TO BE DELETED:", question.getVarName())
                self.removeQuestion(question.getVarName())
            # insert question
            self.addQuestion(questionGenerator, varName, questionText, questionType, value, defaults, widgetType, **kwargs)

            # restore old questions
            for question in tmpQuestions:
                tmpVarName = question.getVarName()
                tmpQuestionText = question.questionText
                tmpQuestionType = question.questionType
                tmpValue = question.value
                self.addQuestion(questionGenerator, tmpVarName, tmpQuestionText, tmpQuestionType, tmpValue, defaults, widgetType, **kwargs)

    """
        Remove a question if it exists in our questions
    """

    def removeQuestion(self, varName):
        for question in self.questions:
            if question.getVarName() == varName:
                self.questions.remove(question)
                question.emptyFrame()

    def emptyFrame(self):
        f = self.sfg.getFrame()
        f.destroy()
        self.sfg = ScrollFrameGui(self.frame)
        self.contents = self.sfg.getContents()
        return self.contents

    def getFrame(self):
        return self.frame

    def getContents(self):
        return self.contents

    def getName(self):
        return self.name

    def getQuestions(self):
        return self.questions
