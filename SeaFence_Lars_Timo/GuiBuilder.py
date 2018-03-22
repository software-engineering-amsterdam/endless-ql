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

        self.parseStatements(ast)

    # Update the form if a value in the form has changed
    def updateForm(self, name='', index='', mode=''):
        self.frame_counter = 0
        self.parseStatements(self.ast)

    # Walk the AST and render gui items
    def parseStatements(self, form):  
        for statement in form.statements:
            getType = statement.getNodeType()
            print getType
            if type(statement) is QuestionNode:
                self.checkWidgetPosition(statement.var)
                self.parseQuestion(statement)

            elif type(statement) is AssignmentNode:
                self.checkWidgetPosition(statement.var)
                self.parseAssignment(statement)

            elif type(statement) is IfNode:
                if self.checkExpressionvalues(statement.expression):
                    condional_shown = True
                    self.parseStatements(statement)
                else:
                    condional_shown = False

            elif type(statement) is ElifNode and not condional_shown:
                if self.checkExpressionvalues(statement.expression):
                    condional_shown = True
                    self.parseStatements(statement)
                else:
                    condional_shown = False

            elif type(statement) is ElseNode and not condional_shown:
                self.parseStatements(statement)

    def checkWidgetPosition(self, var):
        if len(self.frame_order) > 0  and self.frame_counter < len(self.frame_order) and self.frame_order[self.frame_counter] is not var:
            self.removeFrames(self.frame_order[self.frame_counter:])
            self.frame_order = self.frame_order[:self.frame_counter]

    # Parse a question statement and render it
    def parseQuestion(self, statement):
        if statement.var not in self.frames:
            if statement.vartype == "boolean":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes", self.updateForm)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes", self.updateForm, self.gui.values[statement.var])

                self.frame_order.append(statement.var)

            elif statement.vartype == "int":
                if statement.var not in self.values:        
                    self.frames[statement.var] = self.gui.addIntQuestion(statement.var, statement.question, self.updateForm)
                    self.values.append(statement.var)
                else:
                    self.frames[statement.var] = self.gui.addIntQuestion(statement.var, statement.question, self.updateForm, self.gui.values[statement.var])
                
                self.frame_order.append(statement.var)

        self.frame_counter += 1

    # Parse an assignment and render it according to filled in values
    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement.expression)
        if statement.var not in self.frames:
            if statement.var not in self.values:
                self.values.append(statement.var)
                self.frames[statement.var] = self.gui.addAssignment(statement.var, statement.name, result)
            else:
                self.frames[statement.var] = self.gui.addAssignment(statement.var, statement.name, result, self.gui.values[statement.var])
            
            self.frame_order.append(statement.var)
        else:
            self.gui.updateValue(statement.var, result)

        self.frame_counter += 1

    # Parse an assignment and return its value
    def parseBinOpAssignment(self, statement):
        if type(statement) is BinOpNode:
            left = self.parseBinOpAssignment(statement.left)
            right = self.parseBinOpAssignment(statement.right)
            return self.getOperator(statement.op)(left, right)

        if type(statement) is UnOpNode:
            return self.gui.getValue(statement.var, "int")

        if type(statement) is LiteralNode:
            return int(statement.literal)

    # Remove a frame and its content
    def removeFrame(self, var_frame):
        if var_frame in self.frames:
            self.frames[var_frame].destroy()
            del self.frames[var_frame]

    # Remove a list of frames
    def removeFrames(self, frame_list):
        print "Removing frames: ", frame_list
        for var_frame in frame_list:
            self.removeFrame(var_frame)

    # Function that checks if the expression variables match the needed values to show the block
    def checkExpressionvalues(self, expression):
        if type(expression) is BinOpNode:
            if expression.op == "&&":
                if self.checkExpressionvalues(expression.left) and self.checkExpressionvalues(expression.right):
                    return True

            if expression.op == "||":
                if self.checkExpressionvalues(expression.left) or self.checkExpressionvalues(expression.right):
                    return True

            else:
                left = self.parseBinOpAssignment(expression.left)
                right = self.parseBinOpAssignment(expression.right)
                result = self.getOperator(expression.op)(left, right)
                return result


        if type(expression) is UnOpNode:
            if not expression.negate and self.gui.values[expression.var].get() == 1:
                return True
            elif expression.negate and self.gui.values[expression.var].get() == 0:
                return True


        if type(expression) is LiteralNode:
            if expression.vartype == u"int":
                if not expression.negate and int(expression.literal) == 1:
                    return True
                elif expression.negate and int(expression.literal) == 0:
                    return True

            elif expression.vartype == u"boolean":
                if not expression.negate and expression.literal == "true":
                    return True
                elif expression.negate and expression.literal == "false":
                    return True

        return False

    # Function to operate on expressions
    def getOperator(self, operator):
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
