from QLSast import *
import sys

class QLSTypeChecker(object):
	
    def __init__(self, ql_ast, qls_ast):
    	self.ql_ast = ql_ast
    	self.qls_ast = qls_ast
        self.ql_question_variables = {}
        self.qls_question_variables = {}


    def startQLSTypeCheck(self):
        self.retrieveVariables(self.ql_ast.statements, self.ql_question_variables, "ql")
        self.retrieveVariables(self.qls_ast.pages, self.qls_question_variables, "qls")

        print self.ql_question_variables
        print self.qls_question_variables


    # Checks if every question in QL is referenced in QLS.
    def checkReferencesToQL(self):
    	pass


    # Checks whether the types of the questions are compatible with the assigned widgets.
    def checkWidgetQuestionCompatibility(self):
    	pass


    # Check if every question is only placed once in the qls ast.
    def checkQuestionUniqueness(self):
    	pass


    def retrieveVariables(self, elements, question_variables, flag):
        for element in elements:
            nodetype = element.getNodeType()
            if nodetype == "question" or nodetype == "assignment":
                variable_name = element.getVariableName()
                if flag == "ql":
                    variable_type = element.getVariableType()
                    question_variables[variable_name] = variable_type
                elif flag == "qls":
                    variable_widget = element.getWidget()
                    question_variables[variable_name] = variable_widget

            elif nodetype == "if" or nodetype == "elif" or nodetype == "else":
                self.retrieveVariables(element.statements, question_variables, flag)

            elif nodetype == "section":
                self.retrieveVariables(element.sections, question_variables, flag)

        return


    # def retrieveVariablesQLSast(self, iterable):
    #     for element in iterable:
    #         nodetype = element.returnNodeType()
    #         if nodetype == "question":
    #             variable_name = statement.getVariableName()
    #             variable_type = statement.getVariableType()
    #             self.question_variables[variable_name] = variable_type

    #         elif nodetype == "page" or nodetype == "section":
    #             self.retrieveVariablesQLSast()
