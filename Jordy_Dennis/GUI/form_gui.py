# Jordy Bottelier & Dennis Kruidenberg

from .gui_imports import *
from .form_scroll_frame import ScrollFrameGui
from .form_question import Question
from .form_page import Page


# Any formGUI consists of a main frame, a header within this frame, and a scroll frame.
# The scroll frame can not be changed, only the contents within.
class FormGui:

    def __init__(self, parent, questionGenerator, header="No Header Text", color="orange"):
        self.frame = create_frame(parent, color)
        self.frame.pack(expand=True, fill='both')
        self.headerFrame = None
        self.createHeader(header, parent=self.frame)

        self.pages = []
        self.buttonFrame = create_frame(self.frame, background='blue')
        self.buttonFrame.pack(side="top", fill="x", expand=False)
        self.contents = create_frame(self.frame, background='black')
        self.contents.pack(side="top", fill="both", expand=True)
        self.addPage("Hallo", questionGenerator)

        # self.sfg = ScrollFrameGui(self.frame)
        # self.contents = self.sfg.get_contents()
        
        self.questions = []
        self.name = header
        self.questionGenerator = questionGenerator

    # Create the header according to the specified layout
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

    def addPage(self, header, questionGenerator, color='green'):
        new_page = Page(self.contents, questionGenerator, color=color)
        new_page.place(self.contents, x=0, y=0, relwidth=1, relheight=1)
        button = Button(self.buttonFrame, text=header, command=new_page.show)
        button.pack(side="left")
        self.pages.append([header, new_page])
        return new_page

    def add_question(self, varName, question_text="Hi mom", question_type=bool, value=False):
        q = Question(self.contents, self.questionGenerator, varName, question_text, question_type, value)
        self.questions.append(q)

    def remove_question(self, varName):
        for question in self.questions:
            if question.getVarName == varName:
                question.empty_frame()

    def getHeader(self):
        return self.headerFrame

    def getFrame(self):
        return self.frame

    def getContents(self):
        return self.contents

    def getText(self):
        return self.name

    def getAnswers(self):
        answers = {}
        varDict = self.questionGenerator.getVarDict()
        for varName in varDict:
            assignNode = varDict[varName]['assign']
            answers[varName] = varDict[varName]['node'].evaluate()
        return answers
