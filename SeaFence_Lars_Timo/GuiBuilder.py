from gui import Gui
from QLast import *

class GuiBuilder():
    def __init__(self, ast):
        self.gui = Gui()
        self.ast = ast
        self.parseForm()


    def parseForm(self):
        # print self.ast

        form_name = self.ast.name
        for statement in self.ast.statements:
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)
            elif type(statement) is AssignmentNode:
                self.parseAssignment(statement)

        return

    def parseQuestion(self, statement):
        self.gui.addLabel(statement.var, statement.question)
        if statement.vartype == "boolean":
            self.gui.addYesNoRadioButtons(statement.var, "Yes", "No")
        elif statement.vartype == "int":
            self.gui.addTextBox(statement.var, 1, 10)
        return

    def parseAssignment(self, statement):
        
        return