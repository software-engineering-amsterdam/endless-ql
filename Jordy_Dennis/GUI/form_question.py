"""
    A question is a frame which consists of a label (question text) and the question widget
    which is obtained from the InputTypeMap.

    The input type map is an object that can return
    a widget based on its own type, thats why every question gets an instance of this object.
    This is done because there are a lot of self set variables needed for the functions in this class,
    such as the AST and the question generator
"""

from .gui_imports import *
from .form_input_type import InputTypeMap


class Question:
    """
        Get the correct widget according to the specification and pack it inside the question frame.
        The header is also created.
    """

    def __init__(self, parent, questionGenerator, varName, questionText, questionType, value, fontType='Arial',
                 fontSize=15, color='black', width=40):
        self.frame = create_frame(parent)
        self.questionGenerator = questionGenerator
        self.frame.pack(fill="both", anchor=NW)

        self.varName = varName
        self.map = InputTypeMap(self.frame, self.questionGenerator, varName, value)

        self.answer = None
        self.value = value
        self.questionType = questionType

        self.varName = varName

        self.createHeaderLabel(questionText, fontType, fontSize, color)
        self.createInputUser(questionType)

        self.questionText = questionText

    """
        Create header of the question according to possible QLS or default value
    """

    def createHeaderLabel(self, questionText, fontType, fontSize, color):
        label = Label(self.frame)
        label.config(text=questionText)
        label.config(font=(fontType, fontSize))
        label.config(fg=color)
        label.pack()

    """
        Get the widget according to the question type
    """

    def createInputUser(self, questionType):
        self.widget, self.answer = self.map.getWidget(questionType)

    """
        Getters and setters ----------------------------
    """

    def emptyFrame(self):
        self.frame.destroy()

    def getVarName(self):
        return self.varName

    def getFrame(self):
        return self.frame

    def getAnswer(self):
        if ((self.questionType == float or self.questionType == int) and (self.answer.get() == "")):
            return 0
        else:
            return self.answer.get()

    def getText(self):
        return self.questionText

    def disableWidget(self):
        self.widget.config(state='disabled')

    def setValue(self, value):
        self.answer.set(value)
