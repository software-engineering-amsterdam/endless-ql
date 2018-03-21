# Lars Lokhoff, Timo Dobber
# This class holds all functions related to building the gui according to the AST
from Gui import Gui
from QLast import *
import operator as op
from Tkinter import *

class GuiBuilder():
    def __init__(self, ql_ast, qls_ast=None):
        self.gui = Gui()
        self.ql_ast = ql_ast
        self.form_name = ql_ast.name

        self.values = []
        self.frames = {}
        self.new_frame_order = []
        self.rendered_frame_order = []
        self.frame_counter = 0

        # First determine the items we need to render, so we traverse AST
        self.parseQLAST(ql_ast)
        self.renderQLWidgets()
        self.rendered_frame_order = self.new_frame_order
        self.new_frame_order = []

    # Update the form if a value in the form has changed
    def updateForm(self, name='', index='', mode=''):
        print "Updating form"
        self.frame_counter = 0
        self.parseQLAST(self.ql_ast)
        print "New frame order: ", self.new_frame_order
        print "Old frame order: ", self.rendered_frame_order
        self.renderQLWidgets()
        self.rendered_frame_order = self.new_frame_order
        self.new_frame_order = []

    def renderQLWidgets(self):
        for widget_var in self.new_frame_order:
            widget_info = self.frames[widget_var]
            widget_type = widget_info[0]

            if widget_type is "question" and self.checkRenderWidget(widget_var):           
                self.renderQuestion(widget_var, widget_info)

            elif widget_type is "assignment" and self.checkRenderWidget(widget_var):           
                self.renderAssignment(widget_var, widget_info)

            self.frame_counter += 1
    
    def parseQLAST(self, ast):
        for node in ast.statements:
            node_type = node.getNodeType()
            if node_type is "question":
                self.parseQuestion(node)

            elif node_type is "assignment":
                self.parseAssignment(node)

            elif node_type is "if":
                if self.checkExpressionvalues(node.expression):
                    condional_shown = True
                    self.parseQLAST(node)
                else:
                    condional_shown = False

            elif node_type is "elif" and not condional_shown:
                if self.checkExpressionvalues(node.expression):
                    condional_shown = True
                    self.parseQLAST(node)
                else:
                    condional_shown = False

            elif node_type is "else" and not condional_shown:
                self.parseQLAST(node)

    # walk the widget list that need to be renderend and render each item accordingly
    def renderStatements(self):  
        pass

    def checkRenderWidget(self, var):
        if len(self.rendered_frame_order) > 0  and self.frame_counter < len(self.rendered_frame_order) and self.rendered_frame_order[self.frame_counter] is not var:
            print self.rendered_frame_order[self.frame_counter], var
            self.removeFrames(self.rendered_frame_order[self.frame_counter:])
            self.rendered_frame_order = self.rendered_frame_order[:self.frame_counter]
            return True
        elif len(self.rendered_frame_order) <= self.frame_counter:
            return True

        return False

    def parseQuestion(self, statement):
        if statement.var not in self.gui.values: 
            self.gui.createTKTraceVariable(statement.var, statement.vartype, self.updateForm) 
           
        # type, type of question, question text
        self.frames[statement.var] = ("question", statement.vartype, statement.question) 
        self.new_frame_order.append(statement.var)

    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement.expression)
        
        if statement.var not in self.gui.values:
            self.gui.createTKNoTraceVariable(statement.var, statement.vartype)
        else:
            self.gui.updateValue(statement.var, result) 

        # type, type of assignment, name, result to show
        self.frames[statement.var] = ("assignment", statement.name, result)
        self.new_frame_order.append(statement.var)

    # render a question statement
    def renderQuestion(self, widget_var, widget_info):
        if widget_info[1] == "boolean":
            self.gui.addBooleanQuestion(widget_var, widget_info[2], "Yes", "No")
        
        elif widget_info[1] == "int":
            self.gui.addIntQuestion(widget_var, widget_info[2])

    # render an assignment
    def renderAssignment(self, widget_var, widget_info):
        self.gui.addAssignment(widget_var, widget_info[1], widget_info[2])

    # render an assignment and return its value
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
            self.gui.frames[var_frame].destroy()

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
            if expression.negate == False and self.gui.values[expression.var].get() == "0":
                return True
            elif expression.negate and self.gui.values[expression.var].get() == "1":
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
