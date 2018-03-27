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
        self.qls_ast = qls_ast
        self.form_name = ql_ast.name
        self.values = []
        self.frames = {}
        self.ql_frame_order = []
        self.qls_frame_order = []
        self.rendered_frame_order = []
        self.frame_counter = 0
        self.sections = {}
        self.pages = {}
        self.done_rendering = False
        # First determine the items we need to render, so we traverse AST
        self.parseQLAST(ql_ast)
        print "New QL frame order: ", self.ql_frame_order
        if qls_ast:
            self.parseQLSAST(qls_ast)
            self.ql_frame_order = self.qls_frame_order
            print "New QLS frame order: ", self.qls_frame_order

        self.renderQLWidgets()
        self.rendered_frame_order = self.ql_frame_order
        self.ql_frame_order = []

    # Update the form if a value in the form has changed
    def updateForm(self, name='', index='', mode=''):
        if not self.rendering:
            print name, index, mode
            print "Updating form"
            self.frame_counter = 0
            self.parseQLAST(self.ql_ast)
            print "New QL frame order: ", self.ql_frame_order
            print "Old frame order: ", self.rendered_frame_order
            if self.qls_ast:
                self.qls_frame_order = []
                self.parseQLSAST(self.qls_ast)
                self.ql_frame_order = self.qls_frame_order
                print "New QLS frame order: ", self.qls_frame_order

            self.renderQLWidgets()
            self.rendered_frame_order = self.ql_frame_order
            self.ql_frame_order = []
            self.rendering = False

    def renderQLWidgets(self):
        self.rendering = True

        for widget_var in self.ql_frame_order:
            widget_info = self.frames[widget_var]
            widget_type = widget_info[0]

            if widget_type == "question" and self.checkRenderWidget(widget_var):
                self.renderQuestion(widget_var, widget_info)
            elif widget_type == "assignment" and self.checkRenderWidget(widget_var):   
                self.renderAssignment(widget_var, widget_info)
            elif widget_type == "spinbox" and self.checkRenderWidget(widget_var):
                self.renderSpinBoxQuestion(widget_var, widget_info)
            elif widget_type == "slider" and self.checkRenderWidget(widget_var):
                self.renderSliderQuestion(widget_var, widget_info)
            elif widget_type == "dropdown" and self.checkRenderWidget(widget_var):
                self.renderDropdownQuestion(widget_var, widget_info)

            self.frame_counter += 1

        self.rendering = False
    
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

    def parseQLSAST(self, ast):
        for page in ast.pages:

            if page.name not in self.pages:
                current_page = self.gui.addPage(page.name)
                self.pages[page.name] = current_page
            else:
                self.gui.current_page = self.pages[page.name]

            render_frame = self.gui.current_page
            default_page_int, default_page_text, default_page_bool = self.getDefaultStyleWidgets(page.default_style_widgets)
            print "Page defaults: ", default_page_int, default_page_text, default_page_bool
            for section in page.sections:
                if self.showOrRemoveSection(section):
                    if section.name not in self.sections:
                        render_frame = self.gui.addSection(section.name)
                        self.sections[section.name] = render_frame
                    else:
                        render_frame = self.sections[section.name]

                    default_section_int, default_section_text, default_section_bool = self.getDefaultStyleWidgets(section.default_style_widgets, default_page_int, default_page_text, default_page_bool)
                    print "section1 defaults: ", default_page_int, default_page_text, default_page_bool
                    for question in section.questions:
                        if question.var in self.ql_frame_order:
                            self.parseQLSQuestion(question, render_frame, default_section_int, default_section_text, default_section_bool)

                    for nested_section in section.sections:
                        if self.showOrRemoveSection(nested_section):
                            if not nested_section.name in self.sections:
                                nested_render_frame = self.gui.addSection(nested_section.name, render_frame)
                                self.sections[nested_section.name] = nested_render_frame
                            else:
                                nested_render_frame = self.sections[nested_section.name]    

                            default_section_int, default_section_text, default_section_bool = self.getDefaultStyleWidgets(nested_section.default_style_widgets, default_section_int, default_section_text, default_section_bool)
                            print "section2 defaults: ", default_page_int, default_page_text, default_page_bool
                            for question in nested_section.questions:
                                if question.var in self.ql_frame_order:
                                    self.parseQLSQuestion(question, nested_render_frame, default_section_int, default_section_text, default_section_bool)

    def showOrRemoveSection(self, section):
        for question in section.questions:
            if question.var in self.ql_frame_order:
                return True

        if section.name in self.sections:
            self.sections[section.name].destroy()
            del self.sections[section.name]

        return False

    def checkRenderWidget(self, var):
        if len(self.rendered_frame_order) > 0 and len(self.rendered_frame_order) > self.frame_counter  and self.rendered_frame_order[self.frame_counter] is not var:
            self.rendered_frame_order[self.frame_counter] is not var
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
        self.frames[statement.var] = ["question", statement.vartype, statement.question, self.gui.window]
        self.ql_frame_order.append(statement.var)

    def parseQLSQuestion(self, question, question_section, default_style_int, default_style_text, default_style_bool):
        old_question = self.frames[question.var] 
        widget, change_needed = self.checkForDefaultWidget(old_question, question_section, default_style_int, default_style_text, default_style_bool)

        if change_needed:
            self.frames[question.var] = widget
            self.qls_frame_order.append(question.var)
        else:
            self.frames[question.var][3] = question_section
            self.qls_frame_order.append(question.var)

    def checkForDefaultWidget(self, old_question, question_section, default_style_int, default_style_text, default_style_bool):
        question_vartype = old_question[1]

        if question_vartype == "boolean" and default_style_bool:
            if "radio" in default_style_bool.widget:
                return [old_question[0], question_vartype, old_question[2], question_section, default_style_bool.options.options["color"], default_style_bool.options.options["width"], default_style_bool.options.options["font"], default_style_bool.options.options["fontsize"]], True
            elif "dropdown" in default_style_bool.widget:
                return ["dropdown", question_vartype, old_question[2], question_section, default_style_bool.options.options["color"], default_style_bool.options.options["width"], default_style_bool.options.options["font"], default_style_bool.options.options["fontsize"]], True

        elif question_vartype == "int" and old_question[0] != "assignment" and default_style_int:
            if default_style_int.widget == "text":
                return [old_question[0], question_vartype, old_question[2], question_section, default_style_int.options.options["color"], default_style_int.options.options["width"], default_style_int.options.options["font"], default_style_int.options.options["fontsize"]], True
            elif default_style_int.widget == "slider":
                return ["slider", question_vartype, old_question[2], question_section, default_style_int.options.options["color"], default_style_int.options.options["width"], default_style_int.options.options["font"], default_style_int.options.options["fontsize"]], True
            elif default_style_int.widget == "spinbox":
                return ["spinbox", question_vartype, old_question[2], question_section, default_style_int.options.options["color"], default_style_int.options.options["width"], default_style_int.options.options["font"], default_style_int.options.options["fontsize"]], True

        return old_question, False


    def parseAssignment(self, statement):
        result = self.parseBinOpAssignment(statement.expression)

        if statement.var not in self.gui.values:
            self.gui.createTKNoTraceVariable(statement.var, statement.vartype)
        else:
            self.gui.updateValue(statement.var, result) 

        # type, type of assignment, name, result to show
        self.frames[statement.var] = ["assignment", statement.name, result, self.gui.window]
        self.ql_frame_order.append(statement.var)

    # render a question statement
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

    # render an assignment
    def renderAssignment(self, widget_var, widget_info):
        self.gui.addAssignment(widget_var, widget_info[1], widget_info[2], widget_info[3])

    def renderSpinBoxQuestion(self, widget_var, widget_info):
        self.gui.addSpinBoxQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

    def renderSliderQuestion(self, widget_var, widget_info):
        self.gui.addSliderQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

    def renderDropdownQuestion(self, widget_var, widget_info):
        self.gui.addBooleanDropdownQuestion(widget_var, widget_info[2], widget_info[3], widget_info[4], widget_info[5], widget_info[6], widget_info[7])

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

    def getDefaultStyleWidgets(self, default_style_widgets, old_default_int=None, old_default_text=None, old_default_bool=None):
        default_int = old_default_int
        default_text = old_default_text
        default_bool = old_default_bool

        for default_style in default_style_widgets:
            if default_style.options.vartype == "int":
                default_int = default_style
            elif default_style.options.vartype == "text":
                default_text = default_style
            elif default_style.options.vartype == "boolean":
                default_bool = default_style

        return default_int, default_text, default_bool

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
