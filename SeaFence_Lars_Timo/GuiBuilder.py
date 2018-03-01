from gui import Gui
from QLast import *
import Tkinter as tk

class GuiBuilder():
    def __init__(self):
        self.gui = Gui()
        self.ast = ast
        self.parseForm()


    def parseForm(self):
        print self.ast

        form_name = self.ast.name
        for statement in self.ast.statements:
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)

        return

    def parseQuestion(self, statement):
        self.gui.addLabel(statement.var, statement.question)
        if statement.vartype == "boolean":
            var = tk.IntVar()
            self.gui.addRadioButton(statement.var, "Yes", var, 1)
            self.gui.addRadioButton(statement.var, "No", var, 2)
        elif statement.vartype == "int":
            self.gui.addTextBox(statement.var, 1, 10)
        return