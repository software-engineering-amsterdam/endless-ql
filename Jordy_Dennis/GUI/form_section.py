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

class Section():

    def __init__(self, parent, questionGenerator, page_header='default', color='green'):
        self.questionGenerator = questionGenerator
        self.name = page_header
        scrollFrame = ScrollFrameGui(parent)
        self.contents = scrollFrame.get_contents()

        headerFrame = create_frame(scrollFrame.get_contents())
        text = Text(scrollFrame.get_contents(), height=1, width=100)
        headerFont = Font(family="Arial", size=15, weight='bold')
        text.tag_configure('header_conf', font=headerFont)
        text.insert(INSERT, page_header, 'header_conf')
        text.config(state=DISABLED)
        text.pack(anchor=NW, expand=True, fill="both")
        self.headerFrame = headerFrame
        self.questions = []

    """
        Lifts the frame in case the menu button of the frame is pressed
    """
    def show(self):
        self.frame.lift()

    """
        Place the frame inside parent frame
    """
    def place(self, parent, x=0, y=0, relwidth=1, relheight=1):
        self.frame.place(in_=parent, x=x, y=y, relwidth=relwidth, relheight=relheight)

    """
        Add a question from the question generator
    """
    def addQuestion(self, questionGenerator, varName, questionText, questionType, value):
        q = Question(self.contents, questionGenerator, varName, questionText, questionType, value)
        print("ADDDD QUESTION")
        self.questions.append(q)


    def insertQuestion(self, prev, questionGenerator, varName, questionText, questionType, value):
        print("INSERT QUESTION")
        tmpQuestions = copy.copy(self.questions)
        #first question
        print("PREV",prev)
        if prev == "":
            print("HIERRR")
            self.addQuestion(questionGenerator, varName, questionText, questionType, value)
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
            self.addQuestion(questionGenerator, varName, questionText, questionType, value)


            # restore old questions
            for question in tmpQuestions:
                tmpVarName = question.getVarName()
                tmpQuestionText = question.questionText
                tmpQuestionType = question.questionType
                tmpValue = question.value
                self.addQuestion(questionGenerator, tmpVarName, tmpQuestionText, tmpQuestionType, tmpValue)

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
