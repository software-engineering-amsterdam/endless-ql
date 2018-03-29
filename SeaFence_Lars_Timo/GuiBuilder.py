# Lars Lokhoff, Timo Dobber
# This class holds all functions related to building the gui according to the QL AST
from Gui import Gui
from QLast import *
import operator as op
from Tkinter import *
from QLSGuiBuilder import QLSGuiBuilder

class GuiBuilder(object):
    def __init__(self, ql_ast, qls_ast=None):
        self.gui = Gui()
        self.ql_ast = ql_ast
        self.qls_ast = qls_ast
        self.frame_order = []
        self.rendered_frame_order = []
        self.frame_counter = 0
        self.done_rendering = False

        self.parseQLAST(ql_ast)

        print "New QL frame order: ", self.frame_order
        if qls_ast:
            self.QLSBuilder = QLSGuiBuilder(self.gui, qls_ast)
            self.frame_order = self.QLSBuilder.parseQLSAST(self.frame_order)
            print "New QLS frame order: ", self.frame_order

        self.renderQLWidgets()
        self.rendered_frame_order = self.frame_order
        self.frame_order = []

    # Update the form if a value in the form has changed
    def updateForm(self, name='', index='', mode=''):
        if not self.rendering:
            print name, index, mode
            print "Updating form"
            self.frame_counter = 0
            self.parseQLAST(self.ql_ast)
            print "New QL frame order: ", self.frame_order
            print "Old frame order: ", self.rendered_frame_order
            
            if self.qls_ast:
                self.frame_order = self.QLSBuilder.parseQLSAST(self.frame_order)
                print "New QLS frame order: ", self.frame_order

            self.renderQLWidgets()
            self.rendered_frame_order = self.frame_order
            self.frame_order = []
            self.rendering = False

    def renderQLWidgets(self):
        self.rendering = True

        for widget_var in self.frame_order:
            widget_info = self.gui.widget_settings[widget_var]
            widget_type = widget_info[0]

            if (widget_type == "question" or widget_type == "radio") and self.checkRenderWidget(widget_var):
                self.renderQuestion(widget_var, widget_info)
            if widget_type == "checkbox" and self.checkRenderWidget(widget_var):
                self.renderCheckboxQuestion(widget_var, widget_info)
            elif widget_type == "assignment" and self.checkRenderWidget(widget_var):   
                self.renderAssignment(widget_var, widget_info)
            elif widget_type == "spinbox" and self.checkRenderWidget(widget_var):
                self.renderSpinBoxQuestion(widget_var, widget_info)
            elif widget_type == "slider" and self.checkRenderWidget(widget_var):
                self.renderSliderQuestion(widget_var, widget_info)
            elif widget_type == "dropdown" and self.checkRenderWidget(widget_var):
                self.renderDropdownQuestion(widget_var, widget_info)

            self.frame_counter += 1

        self.removeExcessQuestions()
        self.rendering = False
    
    def removeExcessQuestions(self):
        print self.frame_counter, len(self.rendered_frame_order)
        if self.frame_counter < len(self.rendered_frame_order):
            self.removeFrames(self.rendered_frame_order[self.frame_counter:])

    def parseQLAST(self, ast):
        for node in ast.statements:
            node_type = node.getNodeType()
            if node_type is "question":
                self.parseQLQuestion(node)

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

    def checkRenderWidget(self, var):
        if len(self.rendered_frame_order) > 0 and len(self.rendered_frame_order) > self.frame_counter  and self.rendered_frame_order[self.frame_counter] is not var:
            self.removeFrames(self.rendered_frame_order[self.frame_counter:])
            self.rendered_frame_order = self.rendered_frame_order[:self.frame_counter]
            return True
        elif len(self.rendered_frame_order) <= self.frame_counter:
            return True

        return False

    def parseQLQuestion(self, statement):
        if statement.var not in self.gui.values: 
            self.gui.createTKTraceVariable(statement.var, statement.vartype, self.updateForm) 
           
        # type, type of question, question text
        self.gui.widget_settings[statement.var] = ["question", statement.vartype, statement.question, self.gui.window]
        self.frame_order.append(statement.var)
    
    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement.expression)

        if statement.var not in self.gui.values:
            self.gui.createTKNoTraceVariable(statement.var, statement.vartype)
        else:
            self.gui.updateValue(statement.var, result) 

        # type, type of assignment, name, result to show
        self.gui.widget_settings[statement.var] = ["assignment", statement.name, result, self.gui.window]
        self.frame_order.append(statement.var)

    def renderQuestion(self, widget_var, widget_info):
        if widget_info[1] == "boolean":
            if len(widget_info) > 4:
                self.gui.addBooleanQuestion(widget_var, widget_info[2], "Yes", "No", widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])
            else:
                self.gui.addBooleanQuestion(widget_var, widget_info[2], "Yes", "No", widget_info[3])
        
        elif widget_info[1] == "int" or widget_info[1] == "money":
            if len(widget_info) > 4:
                self.gui.addIntQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])
            else:
                self.gui.addIntQuestion(widget_var, widget_info[2], widget_info[3])

    def renderAssignment(self, widget_var, widget_info):
        self.gui.addAssignment(widget_var, widget_info[1], widget_info[2], widget_info[3])

    def renderSpinBoxQuestion(self, widget_var, widget_info):
        print widget_info
        self.gui.addSpinBoxQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7], widget_info[8], widget_info[9])

    def renderSliderQuestion(self, widget_var, widget_info):
        self.gui.addSliderQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7], widget_info[8], widget_info[9])

    def renderDropdownQuestion(self, widget_var, widget_info):
        self.gui.addBooleanDropdownQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

    def renderCheckboxQuestion(self, widget_var, widget_info):
        self.gui.addCheckboxQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

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
        if var_frame in self.gui.frames:
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
            if expression.negate == False and (self.gui.values[expression.var].get() == "0" or self.gui.values[expression.var].get() == "Yes"):
                return True
            elif expression.negate and (self.gui.values[expression.var].get() == "1" or self.gui.values[expression.var].get() == "No"):
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
