from gui import Gui
from QLast import *

class GuiBuilder():
    def __init__(self, ast):
        self.gui = Gui()
        self.ast = ast
        self.form_variable = {}
        self.labels = {}
        self.textBoxes = {}
        self.yesNoButtons = {}
        self.yesNoButtonsValues = {}
        self.parseForm()


    # def updateVariable():

    #     return

    def parseForm(self):
        # print self.ast

        form_name = self.ast.name
        for statement in self.ast.statements:
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)
            # elif type(statement) is AssignmentNode:
            #     self.parseAssignment(statement)

        return

    def parseQuestion(self, statement):
        label = self.gui.addLabel(statement.question)
        self.labels[statement.var] = label
        if statement.vartype == "boolean":
            buttons, intvar = self.gui.addYesNoRadioButtons("Yes", "No", lambda: self.notifyClickRadioButton(statement.var, self.yesNoButtonsValues))
            self.yesNoButtons[statement.var] = buttons
            self.yesNoButtonsValues[statement.var] = intvar
            self.form_variable[statement.var] = intvar.get()
            print self.form_variable

        elif statement.vartype == "int":
            textBox, var = self.gui.addTextFrame(lambda: self.notifyChangeTextBox("name", "vars"))
            # def notifyChangeTextBox(*args):
                # selection = "You selected the option " + str(entryVariable2.get())
                # label.config(text = selection)
                # print "change"
            # textBox.pack()
            # var.trace("w", notifyChangeTextBox)
            # textFrame.pack()
            # var.trace("w", notifyChangeTextBox)
            # textFrame.pack()
            self.textBoxes[statement.var] = textBox

        return

    def notifyClickRadioButton(self, name, vars):
        print str(vars[name].get()) + str(vars[name])
        self.form_variable[name] = vars[name].get()
        print self.form_variable

# def notifyChangeTextBox(*args):
#     # selection = "You selected the option " + str(entryVariable2.get())
#     # label.config(text = selection)
#     print "change"
