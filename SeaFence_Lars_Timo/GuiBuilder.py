from Gui import Gui
from QLast import *
import operator as op
from Tkinter import *

class GuiBuilder():
    def __init__(self, ast):
        self.gui = Gui()
        self.ast = ast
        self.form_name = ast.name

        self.values = []
        self.trueExpressions = {}
        self.submitButtonShown = False

        self.labels = {}
        self.textBoxes = {}
        self.yesNoButtons = {}
        self.yesNoButtonsValues = {}
        self.parseStatements(ast)

    def parseStatements(self, form, setSubmitButton=True):
        for statement in form.statements:
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)
            elif type(statement) is AssignmentNode:
                self.parseAssignment(statement)
            elif type(statement) is IfNode:
                if self.checkVariableStatus(statement.expression):                 
                    if statement.expression not in self.trueExpressions:
                        self.trueExpressions[statement.expression] = self.gui.setCurrentStatementFrame()
                    self.parseStatements(statement, False)

                elif not self.checkVariableStatus(statement.expression) and statement.expression in self.trueExpressions:
                    self.removeIfBlock(statement)
                else:
                    continue

        if setSubmitButton:
            self.gui.addFormButton(self.reEvaluateForm, self.ast)
            self.submitButtonShown = True
        return

    def reEvaluateForm(self, form, submitButton):
        submitButton.destroy()
        self.parseStatements(form)

    def parseQuestion(self, statement):
        # print statement
        if statement.vartype == "boolean" and statement.var not in self.values:
            self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes")
            self.values.append(statement.var)
        elif statement.vartype == "int" and statement.var not in self.values:
            self.gui.addIntQuestion(statement.var, statement.question)
            self.values.append(statement.var)

    def parseAssignment(self, statement):
        if type(statement.expression) == BinOpNode:
            left = self.parseBinOpAssignment(statement.expression.left)
            right = self.parseBinOpAssignment(statement.expression.right)
            result = self.get_operator(statement.expression.op)(left, right)

        if(type(statement.expression) == UnOpNode):
            result = self.gui.getValue(statement.var, "int")

        if statement.var in self.values:
            self.gui.updateText(statement.var, result)
        else:
            self.values.append(statement.var)
            self.gui.addAssignment(statement.var, statement.name, result)

    def parseIfNode(self, statement):
        if type(statement.expression) == BinOpNode:
            return
        elif type(statement.expression) == UnOpNode:
            # print statement.expression
            # print statement.expression.var
            rv = self.parseUnOp(statement.expression)
            
        # if statement.statements != []:
        #         self.parseStatements(statement)
        return

    def parseBinOpAssignment(self, statement):
        if type(statement) == BinOpNode:
            left = self.parseBinOpAssignment(statement.left, assigmentFrame)
            right = self.parseBinOpAssignment(statement.right, assigmentFrame)
            return self.get_operator(statement.expression.op)(left, right)

        if type(statement) == UnOpNode:
            return self.gui.getValue(statement.var, "int")

    # def parseUnOp(self, expression, assigmentFrame):
    #     self.gui.addAssignmentEntry(expression.var, assigmentFrame)
    #     negate = expression.negate
    #     return expression.var

    def removeIfBlock(self, statement):
        self.trueExpressions[statement.expression].destroy()
        del self.trueExpressions[statement.expression]

        for stmnt in statement.statements:
            if stmnt.var in self.values:
                self.values.remove(stmnt.var)

    # def checkIfVariableExists(self, statement):
    #     print "Checking questions"
    #     for key in self.gui.formVariables:
    #         if key == statement.var
    #             return True

    #     return False

    # def notifyClickRadioButton(self, name, vars):
    #     print str(vars[name].get()) + str(vars[name])
    #     self.form_variable[name] = vars[name].get()
    #     print self.form_variable

    # Function that check if the expression variables match the needed values to show the block
    # !Currently only working on Unary!
    def checkVariableStatus(self, expression):
        if type(expression) == UnOpNode:
            if not expression.negate and self.gui.values[expression.var].get() == 1:
                return True
            elif expression.negate and self.gui.values[expression.var].get() == 0:
                return True

        return False

    def get_operator(self, operator):
        return {
            '+' : op.add,
            '-' : op.sub,
            '*' : op.mul,
            '/' : op.div,
            '%' : op.mod,
            '^' : op.xor,
            }[operator]

# def notifyChangeTextBox(*args):
#     # selection = "You selected the option " + str(entryVariable2.get())
#     # label.config(text = selection)
#     print "change"
