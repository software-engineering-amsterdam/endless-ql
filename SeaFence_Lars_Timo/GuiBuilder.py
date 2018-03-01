from Gui import Gui
from QLast import *

class GuiBuilder():
    def __init__(self, ast):
        self.gui = Gui()
        self.ast = ast
        self.form_name = ast.name

        self.parseStatements(ast)
        self.form_variable = {}


    def parseStatements(self, form):
        # print self.ast
        for statement in form.statements:
            # print type(statement)
            if type(statement) is QuestionNode:
                self.parseQuestion(statement)
            elif type(statement) is IfNode:
                #hier ergens kijken naar variabeles in waar de if statement naar kijkt
                #als die niet oke zijn, dan gaan we gewoon verder, anders kunnen we ook deze block in de parsestatements gooien
                #de parsestatemetns functie moet eigenlijk ook de ast doorlopen als de knop na elke "block van questions" ingedrukt word
                #dus hier zou ook een knop toegevoegd moeten worden die het herlopen van de ast triggered om zo te kijken of we latere if statemetns nu wel kunnen doen
                self.parseIfNode(statement)
            elif type(statement) is AssignmentNode:
                self.parseAssignment(statement)

        return

    def parseQuestion(self, statement):
        if statement.vartype == "boolean":
            self.gui.addBooleanQuestion(statement.var, statement.question, "No", "Yes")
        elif statement.vartype == "int":
            self.gui.addIntQuestion(statement.var, statement.question)
        return

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
        if type(statement.expression) == UnOpNode:
            print statement.expression
            
        if statement.statements != []:
                self.parseStatements(statement)
        return

    def parseBinOp(self, statement):
        # if type(statement) == BinOpNode:
        #     self.parseBinOp(statement.left)
        #     self.gui.addLabel(statement.left.var, statement.op)
        #     self.parseBinOp(statement.right)

        # if type(statement) == UnOpNode:
        #     self.parseUnOp(statement)
        return

    def parseUnOp(self, statement):
        # print statement
        # self.gui.addLabel(statement.var, statement.var)
        return
