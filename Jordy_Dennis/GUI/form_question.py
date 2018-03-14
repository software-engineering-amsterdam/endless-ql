from .gui_imports import *
from .form_input_type import InputTypeMap


# A question is a frame which consists of a label (question text) and the input_user all packed in one frame
class Question:

    def __init__(self, parent, questionGenerator, varName, questionText, questionType, value, fontType='Arial', fontSize=15):
        self.frame = create_frame(parent)
        self.questionGenerator = questionGenerator
        self.frame.pack(fill="both", anchor=NW, expand=True)
        self.varName = varName
        self.map = InputTypeMap(self.frame, self.questionGenerator, varName, value)

        self.answer = None
        self.value = value
        self.questionType = questionType

        self.varName = varName
        self.fontSize = fontSize
        self.fontType = fontType

        self.createHeaderLabel(questionText, self.fontType, self.fontSize)
        self.createInputUser(questionType)

        self.questionText = questionText

    # Create the text of the question
    def createHeaderLabel(self, questionText, fontType, fontSize):
        label = Label(self.frame)
        label.config(text=questionText)
        label.config(font=(fontType, fontSize))
        label.pack()

    # The frame that contains the input_user depending on the type of question
    def createInputUser(self, questionType):
        widget, self.answer = self.map.getWidget(questionType)

    def emptyFrame(self):
        self.frame.destroy()

    def getVarName(self):
        return self.varName

    def getFrame(self):
        return self.frame

    def getAnswer(self):
        if((self.questionType == float or self.questionType == int) and (self.answer.get() == "")):
            return 0
        else:
            return self.answer.get()

    def getText(self):
        return self.questionText

    def setValue(self, value):
        self.answer.set(value)
