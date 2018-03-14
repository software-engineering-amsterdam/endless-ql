"""
    This class defines the overall gui. The created mainframe is the root of our application.
    
    We can create a form, and add questions to the pages of the form. The questions are generated
    by the QuestionGenerator, which uses the AST to evaluate expressions and list all of the questions
    that need rendering.

    If you need to know more about the question generator, the questions or the forms, please navigate to
    their respecting files and look in the comments, everything is explained. 
    
"""

from .gui_imports import *
from .form_scroll_frame import ScrollFrameGui
from .form_gui import FormGui
from .form_question import Question

class Gui:

    """
        Initialize the GUI, create the question generator based on the AST and the VarDict.
        The question generator is initialized first and then later set after the form is created.
        This is done because their is a circular dependency.
    """
    def __init__(self, ast, qls=False):
        self.gui = Tk()
        self.mainframe = create_frame(self.gui, background='pink')
        self.mainframe.pack(expand=True, fill='both')
        self.form = None
        self.ast = ast
        self.varDict = ast.varDict
        self.questionsGenerator = Question_Generator(self.varDict, self.ast, self.form)
        self.form = FormGui(self.mainframe, self.questionsGenerator, self.ast.getName(), qls=qls)
        self.questionsGenerator.form = self.form
        self.createForm()
        self.execute()

    """
        Create the questions for the form based on the Question Generator,
        and add a submit button which collects the answers
    """
    def createForm(self):
        # self.questionsGenerator.updateQuestions(True)
        b = Button(self.mainframe, text="SUBMIT", command=self.collectAnswers)
        b.pack()

    """
        Execute the GUI
    """
    def execute(self):
        self.gui.geometry("600x400")
        self.gui.mainloop()

    """
        Collect the answers from the form varDict
    """
    def collectAnswers(self):
        answers = self.form.getAnswers()
        printDict(answers)
