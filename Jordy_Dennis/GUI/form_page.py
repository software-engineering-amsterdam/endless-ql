"""
    Pages can be added to a parent class and consist of a scrollFrame (contents), to which
    questions can be added or removed.

    We track a list of questions, in case questions need to be removed or added
"""
from .gui_imports import *
from .form_question import Question
from .form_scroll_frame import ScrollFrameGui
from .form_question import Question

class Page():

    def __init__(self, parent, questionGenerator, page_header='Page1', color='green'):
        self.frame = create_frame(parent, color)
        self.frame.pack(expand=True, fill='both')
        self.sfg = ScrollFrameGui(self.frame)
        self.contents = self.sfg.get_contents()
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
    def addQuestion(self, varName, questionText="Default Question", questionType=bool, value=False):
        q = Question(self.contents, self.questionGenerator, varName, questionText, questionType, value)
        self.questions.append(q)

    """
        Remove a question if it exists in our questions
    """
    def removeQuestion(self, varName):
        for question in self.questions:
            if question.getVarName == varName:
                question.emptyFrame()

    def getFrame(self):
        return self.frame

    def getContents(self):
        return self.contents

    def emptyFrame(self):
        f = self.sfg.getFrame()
        f.destroy()
        self.sfg = ScrollFrameGui(self.frame)
        self.contents = self.sfg.getContents()
        return self.contents
