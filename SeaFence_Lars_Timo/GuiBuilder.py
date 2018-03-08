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
                print statement
                if self.checkExpressionValues(statement.expression):                 
                    if statement.expression not in self.trueExpressions:
                        self.trueExpressions[statement.expression] = self.gui.setCurrentStatementFrame()
                    self.parseStatements(statement, False)

                elif not self.checkExpressionValues(statement.expression) and statement.expression in self.trueExpressions:
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
        if type(statement.expression) is BinOpNode:
            left = self.parseBinOpAssignment(statement.expression.left)
            right = self.parseBinOpAssignment(statement.expression.right)
            result = self.get_operator(statement.expression.op)(left, right)

        if(type(statement.expression) is UnOpNode):
            result = self.gui.getValue(statement.var, "int")

        if statement.var in self.values:
            self.gui.updateText(statement.var, result)
        else:
            self.values.append(statement.var)
            self.gui.addAssignment(statement.var, statement.name, result)

    def parseBinOpAssignment(self, statement):
        if type(statement) is BinOpNode:
            left = self.parseBinOpAssignment(statement.left, assigmentFrame)
            right = self.parseBinOpAssignment(statement.right, assigmentFrame)
            return self.get_operator(statement.expression.op)(left, right)

        if type(statement) is UnOpNode:
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

    # Function that check if the expression variables match the needed values to show the block
    def checkExpressionValues(self, expression):
        if type(expression) is BinOpNode:
            if expression.op == "&&":
                if self.checkExpressionValues(expression.left) and self.checkExpressionValues(expression.right):
                    return True

            if expression.op == "||":
                if self.checkExpressionValues(expression.left) or self.checkExpressionValues(expression.right):
                    return True

        if type(expression) is UnOpNode:
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
