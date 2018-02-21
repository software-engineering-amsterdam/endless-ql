
from .gui_imports import *
from .form_input_type import InputTypeMap

# A question is a frame which consists of a label (question text) and the input_user all packed in one frame
class Question:

    def __init__(self, parent, question_text="Hi mom", question_type="bool" ,input_user=[]):
        self.frame = create_frame(parent)
        self.frame.pack(fill="both", anchor=NW, expand=True)
        self.map = InputTypeMap(self.frame)
        self.create_header_label(question_text)
        self.answer = None
        self.create_input_user(question_type)
        
        self.question_text = question_text
        
        
    # Create the text of the question
    def create_header_label(self, question_text):
        label = Label(self.frame)
        label.config(text=question_text)
        label.pack()

    # The frame that contains the input_user depending on the type of question
    def create_input_user(self, question_type):
        widget, self.answer = self.map.getWidget(question_type)

    def get_frame(self):
        return self.frame

    def get_answer(self):
        return self.answer.get()

    def get_text(self):
        return self.question_text

    def 
