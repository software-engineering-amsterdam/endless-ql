# Jordy Bottelier & Dennis Kruidenberg
#
# GUI class;
#
# Each form gets its own frame. Each form_frame consists of a header frame, and a questions frame.
# The header simply contains the title, and the questions frame contains all of the questions defined
# by the programmer.
#
# Each question has 2 frames, namely the label frame and the input frame (text and input)
#
# To add any widget to any class to another class, use the following notation:
# x = ClassToBeAdded(Parent, vars**) / q = Question(content_frame)

from .gui_imports import *
from .form_scroll_frame import ScrollFrameGui
from .form_gui import FormGui
from .form_question import Question


class Gui:

    def __init__(self, ast):
        self.gui = Tk()
        self.mainframe = create_frame(self.gui, background='pink')
        self.mainframe.pack(expand=True, fill='both')
        self.form = None
        self.ast = ast
        self.varDict = ast.varDict
        self.questionsGenerator = Question_Generator(self.varDict, self.ast, self.form)
        # self.questions = self.questionsGenerator.updateQuestions()
        self.form = FormGui(self.mainframe, self.questionsGenerator, self.ast.getName())
        self.questionsGenerator.form = self.form
        self.createForm()
        self.execute()


    # Upon creating a new form, create a new frame which is a child from the mainframe.
    # For every form, create the header frame and questions frame and fill the questions frame
    # with questions
    def createForm(self):
        self.questionsGenerator.updateQuestions(True)
        b = Button(self.mainframe, text="SUBMIT", command=self.collectAnswers)
        b.pack()

    # Execute the GUI
    def execute(self):
        self.gui.geometry("600x400")
        self.gui.mainloop()

    def collectAnswers(self):
        answers = self.form.getAnswers()
        print(answers)
