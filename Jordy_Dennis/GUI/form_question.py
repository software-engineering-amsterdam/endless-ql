from .gui_imports import *
from .form_input_type import InputTypeMap


# A question is a frame which consists of a label (question text) and the input_user all packed in one frame
class Question:

    def __init__(self, parent, questionGenerator, varName, question_text, question_type, value, fontType='Arial', fontSize=15):
        self.frame = create_frame(parent)
        self.questionGenerator = questionGenerator
        self.frame.pack(fill="both", anchor=NW, expand=True)
        self.varName = varName
        self.map = InputTypeMap(self.frame, self.questionGenerator, varName, value)

        self.answer = None
        self.value = value
        self.question_type = question_type

        self.varName = varName
        self.fontSize = fontSize
        self.fontType = fontType

        self.create_header_label(question_text, self.fontType, self.fontSize)
        self.create_input_user(question_type)

        self.question_text = question_text

    # Create the text of the question
    def create_header_label(self, question_text, fontType, fontSize):
        label = Label(self.frame)
        label.config(text=question_text)
        label.config(font=(fontType, fontSize))
        label.pack()

    # The frame that contains the input_user depending on the type of question
    def create_input_user(self, question_type):
        widget, self.answer = self.map.getWidget(question_type)

    def empty_frame(self):
        self.frame.destroy()

    def getVarName(self):
        return self.varName

    def get_frame(self):
        return self.frame

    def get_answer(self):
        if((self.question_type == float or self.question_type == int) and (self.answer.get() == "")):
            return 0
        else:
            return self.answer.get()

    def get_text(self):
        return self.question_text
