from Gui import Gui
from QLast import *

class GuiBuilder():
    def __init__(self, ast):
        self.gui = Gui()
        self.ast = ast
        self.form_name = ast.name

        self.values = []
        self.submitButtonShown = False

        self.labels = {}
        self.textBoxes = {}
        self.yesNoButtons = {}
        self.yesNoButtonsValues = {}
        self.parseStatements(ast)

    # def updateVariable():

    #     return

    def parseStatements(self, form, setSubmitButton=True):
        # print self.ast
        for statement in form.statements:
            print type(statement)
            # if self.checkIfVariableExists(statement):
            #     return
            self.gui.setCurrentStatementFrame()
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)
            elif type(statement) is AssignmentNode:
                self.parseAssignment(statement)
            elif type(statement) is IfNode:
                if(self.checkVariableStatus(statement.expression)):
                    print statement
                    self.parseStatements(statement, False)
                else:
                    print "nee"
                #self.parseIfNode(statement)

        self.gui.addFormButton(self.reEvaluateForm, self.ast)
        self.submitButtonShown = True
        return

    # This method is called from the gui class when pressing the submit button, remove the old button and 
    # start the re-evaluation of the form based on new variable features
    def reEvaluateForm(self, form, submitButton):
        submitButton.destroy()
        self.submitButtonShown = False
        self.parseStatements(form)

    def parseQuestion(self, statement):
        print statement
        if statement.vartype == "boolean" and statement.var not in self.values:
            self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes")
            self.values.append(statement.var)
        elif statement.vartype == "int" and statement.var not in self.values:
            self.gui.addIntQuestion(statement.var, statement.question)
            self.values.append(statement.var)

    # Parsing an assignment, quite the mental struggle
    def parseAssignment(self, statement):
        # self.gui.addLabel(statement.name, statement.var)

        # if type(statement.expression) == BinOpNode:
        #     self.parseBinOp(statement.expression.left)
        #     self.gui.addLabel(statement.name, statement.expression.op)
        #     self.parseBinOp(statement.expression.right)

        # if(type(statement.expression) == UnOpNode):
        #     self.parseUnOp(statement.expression)
        return

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

    def parseBinOp(self, statement):
        # if type(statement) == BinOpNode:
        #     self.parseBinOp(statement.left)
        #     self.gui.addLabel(statement.left.var, statement.op)
        #     self.parseBinOp(statement.right)

        # if type(statement) == UnOpNode:
        #     self.parseUnOp(statement)
        return

    def parseUnOp(self, expression):
        print expression
        negate = expression.negate
        var = expression.var
        print "Looping for variables"
        for key, value in self.gui.formVariables.iteritems():
            print key
            print value
        # self.gui.addLabel(statement.var, statement.var)
        return

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
# def notifyChangeTextBox(*args):
#     # selection = "You selected the option " + str(entryVariable2.get())
#     # label.config(text = selection)
#     print "change"
