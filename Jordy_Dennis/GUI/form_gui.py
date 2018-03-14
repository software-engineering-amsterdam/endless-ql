# Jordy Bottelier & Dennis Kruidenberg

from .gui_imports import *
from .scroll_frame_gui import ScrollFrameGui
from .form_question import Question


# Any formGUI consists of a main frame, a header within this frame, and a scroll frame.
# The scroll frame can not be changed, only the contents within.
class FormGui:

    def __init__(self, parent, questionGenerator, header="No Header Text", color="orange"):
        self.frame = create_frame(parent, color)
        self.frame.pack(expand=True, fill='both')
        self.header_frame = None
        self.create_header(header, parent=self.frame)

        self.sfg = ScrollFrameGui(self.frame)
        self.contents = self.sfg.get_contents()
        self.questions = []
        self.name = header
        self.questionGenerator = questionGenerator

    # Create the header according to the specified layout
    def create_header(self, header, parent=None, box_width=200, box_height=5, font_type='Arial', font_size=15,
                      font_color='blue'):
        header_frame = create_frame(parent)
        text = Text(parent, height=box_height, width=box_width)
        header_font = Font(family=font_type, size=font_size, weight='bold')
        text.tag_configure('header_conf', font=header_font)
        text.insert(INSERT, header, 'header_conf')
        text.config(state=DISABLED)
        text.pack(anchor=NW)
        self.header_frame = header_frame

    def add_question(self, varName, question_text="Hi mom", question_type=bool, value=False):
        q = Question(self.contents, self.questionGenerator, varName, question_text, question_type, value)
        self.questions.append(q)

    def empty_frame(self):
        f = self.sfg.get_frame()
        f.destroy()
        self.sfg = ScrollFrameGui(self.frame)
        self.contents = self.sfg.get_contents()
        return self.contents

    def remove_question(self, varName):
        for question in self.questions:
            if question.getVarName == varName:
                question.empty_frame()

    def get_header(self):
        return self.header_frame

    def get_frame(self):
        return self.frame

    def get_contents(self):
        return self.contents

    def get_text(self):
        return self.name

    def get_answers(self):
        answers = {}
        vardict = self.questionGenerator.getVarDict()
        printDict(vardict)
        for q in self.questions:
            answers[q.get_text()] = q.get_answer()
        return answers
