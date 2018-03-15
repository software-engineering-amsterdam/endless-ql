# Lars Lokhoff, Timo Dobber
# This class holds all functions related to building the gui according to the AST
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
        self.frames = {}
        self.frameOrder = []
        self.frameCounter = 0

        self.parseStatements(ast)

    # Update the form if a value in the form has changed
    def updateForm(self, name='', index='', mode=''):
        self.frameCounter = 0
        self.parseStatements(self.ast)

    # Walk the AST and render gui items
    def parseStatements(self, form):  
        for statement in form.statements:
            print statement
            if type(statement) is QuestionNode:
                print self.frameCounter
                print self.frameOrder
                if len(self.frameOrder) > 0  and self.frameCounter < len(self.frameOrder):
                    print self.frameOrder[self.frameCounter][0], statement.var
                    if self.frameOrder[self.frameCounter][0] is not statement.var:                    
                        self.removeFrames(self.frameOrder[self.frameCounter:])
                        self.frameOrder = self.frameOrder[:self.frameCounter]
                        self.frameCounter = len(self.frameOrder)

                self.parseQuestion(statement)

            elif type(statement) is AssignmentNode:
                print statement.var, self.frameOrder[self.frameCounter][0]
                if len(self.frameOrder) > 0 and self.frameCounter <= len(self.frameOrder) and self.frameOrder[self.frameCounter][0] is not statement.var:
                    self.removeFrames(self.frameOrder[self.frameCounter:])
                    self.frameOrder = self.frameOrder[:self.frameCounter]
                    self.frameCounter = len(self.frameOrder)

                self.parseAssignment(statement)

            elif type(statement) is IfNode:
                if self.checkExpressionValues(statement.expression):
                    condionalShown = True
                    self.parseStatements(statement)
                else:
                    condionalShown = False

            elif type(statement) is ElifNode and not condionalShown:
                if self.checkExpressionValues(statement.expression):
                    condionalShown = True
                    self.parseStatements(statement)
                else:
                    condionalShown = False

            elif type(statement) is ElseNode and not condionalShown:
                self.parseStatements(statement)

    # Parse a question statement and render it
    def parseQuestion(self, statement):
        if statement.var not in self.frames:
            if statement.vartype == "boolean":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes", self.updateForm)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes", self.updateForm, self.gui.values[statement.var])

                self.frameOrder.append((statement.var, []))

            elif statement.vartype == "int":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addIntQuestion(statement.var, statement.question, self.updateForm)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addIntQuestion(statement.var, statement.question, self.updateForm, self.gui.values[statement.var])
                
                self.frameOrder.append((statement.var, []))

        self.frameCounter += 1

    # Parse an assignment and render it according to filled in values
    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement)

        if statement.var in self.values:
            self.gui.updateText(statement.var, result)
        else:
            self.values.append(statement.var)
            self.gui.addAssignment(statement.var, statement.name, result)

            self.frames[statement.var] = self.gui.setCurrentStatementFrame()
            self.frameOrder.append((statement.var, []))
            self.frameCounter += 1

    # Parse an assignment and return its value
    def parseBinOpAssignment(self, statement):
        if type(statement) is BinOpNode:
            left = self.parseBinOpAssignment(statement.left)
            right = self.parseBinOpAssignment(statement.right)
            return self.get_operator(statement.op)(left, right)

        if type(statement) is UnOpNode:
            return self.gui.getValue(statement.var, "int")

    # Remove a frame and its content
    def removeFrame(self, expression, statements):
        if expression in self.frames:
            self.frames[expression].destroy()
            del self.frames[expression]

        for stmnt in statements:
            if stmnt.var in self.values:
                self.values.remove(stmnt.var)

    # Remove a list of frames
    def removeFrames(self, frameList):
        for frame in frameList:
            self.removeFrame(frame[0], frame[1])

    # Function that checks if the expression variables match the needed values to show the block
    def checkExpressionValues(self, expression):
        if type(expression) is BinOpNode:
            if expression.op == "&&":
                if self.checkExpressionValues(expression.left) and self.checkExpressionValues(expression.right):
                    return True

            if expression.op == "||":
                if self.checkExpressionValues(expression.left) or self.checkExpressionValues(expression.right):
                    return True

            else:
                left = self.parseBinOpAssignment(expression.left)
                right = self.parseBinOpAssignment(expression.right)
                result = self.get_operator(expression.op)(left, right)

        if type(expression) is UnOpNode:
            if not expression.negate and self.gui.values[expression.var].get() == 1:
                return True
            elif expression.negate and self.gui.values[expression.var].get() == 0:
                return True

        return False

    # Function to operate on expressions
    def get_operator(self, operator):
        return {
            '+' : op.add,
            '-' : op.sub,
            '*' : op.mul,
            '/' : op.div,
            '%' : op.mod,
            '^' : op.xor,
            '<' : op.lt,
            '>' : op.gt,
            '<=': op.le,
            '>=': op.ge
            }[operator]
