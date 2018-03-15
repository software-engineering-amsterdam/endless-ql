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
        self.frame_order = []
        self.frame_counter = 0

        self.parsestatements(ast)

    # Update the form if a value in the form has changed
    def updateform(self, name='', index='', mode=''):
        self.frame_counter = 0
        self.parsestatements(self.ast)

    # Walk the AST and render gui items
    def parsestatements(self, form):  
        for statement in form.statements:
            print statement
            if type(statement) is QuestionNode:
                self.checkwidgetposition(statement.var)
                self.parsequestion(statement)

            elif type(statement) is AssignmentNode:
                self.checkwidgetposition(statement.var)
                self.parseassignment(statement)

            elif type(statement) is IfNode:
                if self.checkexpressionvalues(statement.expression):
                    condional_shown = True
                    self.parsestatements(statement)
                else:
                    condional_shown = False

            elif type(statement) is ElifNode and not condional_shown:
                if self.checkexpressionvalues(statement.expression):
                    condional_shown = True
                    self.parsestatements(statement)
                else:
                    condional_shown = False

            elif type(statement) is ElseNode and not condional_shown:
                self.parsestatements(statement)

    def checkwidgetposition(self, var):
        if len(self.frame_order) > 0  and self.frame_counter < len(self.frame_order) and self.frame_order[self.frame_counter][0] is not var:
            self.removeframes(self.frame_order[self.frame_counter:])
            self.frame_order = self.frame_order[:self.frame_counter]

    # Parse a question statement and render it
    def parsequestion(self, statement):
        if statement.var not in self.frames:
            if statement.vartype == "boolean":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addbooleanquestion(statement.var, statement.question, "No", "Yes", self.updateform)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addbooleanquestion(statement.var, statement.question, "No", "Yes", self.updateform, self.gui.values[statement.var])

                self.frame_order.append((statement.var, []))

            elif statement.vartype == "int":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addintquestion(statement.var, statement.question, self.updateform)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addintquestion(statement.var, statement.question, self.updateform, self.gui.values[statement.var])
                
                self.frame_order.append((statement.var, []))

        self.frame_counter += 1

    # Parse an assignment and render it according to filled in values
    def parseassignment(self, statement):
        result = self.parsebinopassignment(statement)

        if statement.var in self.values:
            self.gui.updatetext(statement.var, result)
        else:
            self.values.append(statement.var)
            self.gui.addassignment(statement.var, statement.name, result)

            self.frames[statement.var] = self.gui.setcurrentstatementframe()
            self.frame_order.append((statement.var, []))
            self.frame_counter += 1

    # Parse an assignment and return its value
    def parsebinopassignment(self, statement):
        if type(statement) is BinOpNode:
            left = self.parsebinopassignment(statement.left)
            right = self.parsebinopassignment(statement.right)
            return self.getoperator(statement.op)(left, right)

        if type(statement) is UnOpNode:
            return self.gui.getvalue(statement.var, "int")

    # Remove a frame and its content
    def removeframe(self, expression, statements):
        if expression in self.frames:
            self.frames[expression].destroy()
            del self.frames[expression]

        for stmnt in statements:
            if stmnt.var in self.values:
                self.values.remove(stmnt.var)

    # Remove a list of frames
    def removeframes(self, frameList):
        for frame in frameList:
            self.removeframe(frame[0], frame[1])

    # Function that checks if the expression variables match the needed values to show the block
    def checkexpressionvalues(self, expression):
        if type(expression) is BinOpNode:
            if expression.op == "&&":
                if self.checkexpressionvalues(expression.left) and self.checkexpressionvalues(expression.right):
                    return True

            if expression.op == "||":
                if self.checkexpressionvalues(expression.left) or self.checkexpressionvalues(expression.right):
                    return True

            else:
                left = self.parsebinopassignment(expression.left)
                right = self.parsebinopassignment(expression.right)
                result = self.getoperator(expression.op)(left, right)

        if type(expression) is UnOpNode:
            if not expression.negate and self.gui.values[expression.var].get() == 1:
                return True
            elif expression.negate and self.gui.values[expression.var].get() == 0:
                return True

        return False

    # Function to operate on expressions
    def getoperator(self, operator):
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
