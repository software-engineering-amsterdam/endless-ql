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

        if qls_ast:
            self.QLSBuilder = QLSGuiBuilder(self.gui, qls_ast)
            self.frame_order = self.QLSBuilder.parseQLSAST(self.frame_order)

        self.renderWidgets()
        self.rendered_frame_order = self.frame_order
        self.frame_order = []

    def updateForm(self, name='', index='', mode=''):
        if not self.rendering:
            self.frame_counter = 0
            self.parseQLAST(self.ql_ast)
        
            if self.qls_ast:
                self.frame_order = self.QLSBuilder.parseQLSAST(self.frame_order)

            self.renderWidgets()
            self.rendered_frame_order = self.frame_order
            self.frame_order = []
            self.rendering = False

    def parseQLAST(self, ast):
        for node in ast.statements:
            node_type = node.getNodeType()
            if node_type is "question":
                self.parseQLQuestion(node)

            elif node_type is "assignment":
                self.parseAssignment(node)

            elif node_type is "if":
                print node.expression
                if self.checkExpressionValues(node.expression):
                    condional_shown = True
                    self.parseQLAST(node)
                else:
                    condional_shown = False

            elif node_type is "elif" and not condional_shown:
                if self.checkExpressionValues(node.expression):
                    condional_shown = True
                    self.parseQLAST(node)
                else:
                    condional_shown = False

            elif node_type is "else" and not condional_shown:
                self.parseQLAST(node)

    def parseQLQuestion(self, statement):
        if statement.variable not in self.gui.values: 
            self.gui.createTKTraceVariable(statement.variable, statement.variable_type, self.updateForm) 
           
        self.gui.widget_settings[statement.variable] = ["question", statement.variable_type, statement.question, self.gui.window]
        self.frame_order.append(statement.variable)
    
    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement.expression)

        if statement.variable not in self.gui.values:
            self.gui.createTKNoTraceVariable(statement.variable, 0)
        else:
            self.gui.updateValue(statement.variable, result) 

        self.gui.widget_settings[statement.variable] = ["assignment", statement.name, result, self.gui.window]
        self.frame_order.append(statement.variable)

    def checkExpressionValues(self, expression):
        if type(expression) is BinOpNode:
            if expression.op == "&&":
                print self.checkExpressionValues(expression.left)
                if self.checkExpressionValues(expression.left) and self.checkExpressionValues(expression.right):
                    return True

            elif expression.op == "||":
                if self.checkExpressionValues(expression.left) or self.checkExpressionValues(expression.right):
                    return True

            else:
                left = self.parseBinOpAssignment(expression.left)
                right = self.parseBinOpAssignment(expression.right)

                if expression.op != "/" or (expression.op == "/" and right != 0):
                    result = self.getOperator(expression.op)(left, right)
                else:
                    result = 0

                return result

        if type(expression) is UnOpNode and expression.variable in self.gui.values:
            if expression.negate == False and (self.gui.values[expression.variable].get() == "1" or self.gui.values[expression.variable].get() == "Yes"):
                return True
            elif expression.negate and (self.gui.values[expression.variable].get() == "0" or self.gui.values[expression.variable].get() == "No"):
                return True
            print self.gui.values[expression.variable].get()
        if type(expression) is LiteralNode and expression.variable in self.gui.values:
            if expression.variable_type == u"int":
                if not expression.negate and int(expression.literal) == 1:
                    return True
                elif expression.negate and int(expression.literal) == 0:
                    return True

            elif expression.variable_type == u"boolean":
                if not expression.negate and expression.literal == "true":
                    return True
                elif expression.negate and expression.literal == "false":
                    return True

        return False

    def parseBinOpAssignment(self, statement):
        if type(statement) is BinOpNode:
            left = self.parseBinOpAssignment(statement.left)
            right = self.parseBinOpAssignment(statement.right)
            return self.getOperator(statement.op)(left, right)

        if type(statement) is UnOpNode and statement.variable in self.gui.values:
            return self.gui.getValue(statement.variable, "int")

        if type(statement) is LiteralNode:
            return int(statement.literal)

        return 0

    def renderWidgets(self):
        self.rendering = True

        for widget_variable in self.frame_order:
            widget_info = self.gui.widget_settings[widget_variable]
            widget_type = widget_info[0]

            if (widget_type == "question" or widget_type == "radio") and self.checkRenderWidget(widget_variable):
                self.renderQuestion(widget_variable, widget_info)
            if widget_type == "checkbox" and self.checkRenderWidget(widget_variable):
                self.renderCheckboxQuestion(widget_variable, widget_info)
            elif widget_type == "assignment" and self.checkRenderWidget(widget_variable):   
                self.renderAssignment(widget_variable, widget_info)
            elif widget_type == "spinbox" and self.checkRenderWidget(widget_variable):
                self.renderSpinBoxQuestion(widget_variable, widget_info)
            elif widget_type == "slider" and self.checkRenderWidget(widget_variable):
                self.renderSliderQuestion(widget_variable, widget_info)
            elif widget_type == "dropdown" and self.checkRenderWidget(widget_variable):
                self.renderDropdownQuestion(widget_variable, widget_info)

            self.frame_counter += 1

        self.removeExcessWidgets()
        self.rendering = False

    def renderQuestion(self, widget_variable, widget_info):
        if widget_info[1] == "boolean":
            if len(widget_info) > 4:
                self.gui.addBooleanQuestion(widget_variable, widget_info[2], "Yes", "No", widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])
            else:
                self.gui.addBooleanQuestion(widget_variable, widget_info[2], "Yes", "No", widget_info[3])
        
        elif widget_info[1] == "int" or widget_info[1] == "money":
            if len(widget_info) > 4:
                self.gui.addIntQuestion(widget_variable, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])
            else:
                self.gui.addIntQuestion(widget_variable, widget_info[2], widget_info[3])

    def renderAssignment(self, widget_variable, widget_info):
        self.gui.addAssignment(widget_variable, widget_info[1], widget_info[2], widget_info[3])

    def renderSpinBoxQuestion(self, widget_variable, widget_info):
        self.gui.addSpinBoxQuestion(widget_variable, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7], widget_info[8], widget_info[9])

    def renderSliderQuestion(self, widget_variable, widget_info):
        self.gui.addSliderQuestion(widget_variable, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7], widget_info[8], widget_info[9])

    def renderDropdownQuestion(self, widget_variable, widget_info):
        self.gui.addBooleanDropdownQuestion(widget_variable, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

    def renderCheckboxQuestion(self, widget_variable, widget_info):
        self.gui.addCheckboxQuestion(widget_variable, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])
    
    def checkRenderWidget(self, var):
        if len(self.rendered_frame_order) > 0 and len(self.rendered_frame_order) > self.frame_counter  and self.rendered_frame_order[self.frame_counter] is not var:
            self.gui.removeFrames(self.rendered_frame_order[self.frame_counter:])
            self.rendered_frame_order = self.rendered_frame_order[:self.frame_counter]
            return True
        elif len(self.rendered_frame_order) <= self.frame_counter:
            return True

        return False

    def removeExcessWidgets(self):
        if self.frame_counter < len(self.rendered_frame_order):
            self.gui.removeFrames(self.rendered_frame_order[self.frame_counter:])

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
