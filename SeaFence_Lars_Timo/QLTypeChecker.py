# Lars Lokhoff, Timo Dobber
# This class defines a complete generic visitor for a parse tree produced by QLParser.

from QLast import *
import sys

BOOLEAN_UNICODE = u"boolean"
INTEGER_UNICODE = u"int"

class QLTypeChecker(object):    

    def __init__(self):
        self.questions = {}
        self.conditionals = {}


    def startQLTypeCheck(self, statements):

        for statement in statements:
            node_type = statement.getNodeType()
            if node_type == "question":
                self.checkQuestionNodes(statement)

            elif node_type == "if" or node_type == "elif":
                self.checkConditionalWithExpressionNodes(statement)

            elif node_type == "else":
                self.checkElseConditionNodes(statement)

            elif node_type == "assignment":
                self.checkAssignmentNodes(statement)

        return
        

    # Do the type check for question nodes and if everything is okay append the question
    # to the dictionary.
    def checkQuestionNodes(self, statement):
        self.checkDuplicateVariables(statement)
        statement.question = self.checkDuplicateQuestions(statement.question)
        self.questions[statement.question] = [statement.variable, statement.variable_type]
        return


    # Do the type check for conditional nodes that depend on an expression and if everything
    # is okay append the conditional to the dictionary and loop through the statements.
    def checkConditionalWithExpressionNodes(self, statement):
        node_type = statement.expression.getNodeType()
        if node_type == "literal":
            print statement.expression
            if statement.expression.variable_type != INTEGER_UNICODE and statement.expression.variable_type != BOOLEAN_UNICODE:
                exitProgram("Condition {} is not of type boolean.".format(statement.expression))

        elif node_type == "unop":
            self.checkConditional(statement)

        elif node_type == "binop":
            conditional_type = self.checkInvalidOperations(statement.expression)
            if conditional_type != BOOLEAN_UNICODE:
                exitProgram("Condition {} is not of type boolean.".format(statement.expression))

        self.conditionals[statement.expression] = statement.statements
        self.startQLTypeCheck(statement.statements)
        return


    # Do the type check for else condition nodes and if everything is okay append the
    # conditional to the dictionary and loop through the statements.
    def checkElseConditionNodes(self, statement):
        self.conditionals["else"] = statement.statements
        self.startQLTypeCheck(statement.statements)
        return


    # Do the type check for assignment nodes and if everything is okay append the 
    # assignment to the questions.
    def checkAssignmentNodes(self, statement):
        node_type = statement.expression.getNodeType()
        if node_type == "unop":
            assignment_type = self.getVariableTypes(statement.expression)

        elif node_type == "literal":
            assignment_type = statement.expression.variable_type

        elif node_type is "binop":
            assignment_type = self.checkInvalidOperations(statement.expression)

        if assignment_type != statement.variable_type:
            exitProgram("Assignment expression type does not match variable type at {}".format(statement))

        statement.name = self.checkDuplicateQuestions(statement.name)
        
        self.questions[statement.name] = [statement.variable, statement.variable_type, statement.expression]
 
        return


    # Check for references to undefined question variables.
    def checkUndefinedVariables(self, statement):
        variable_exists = False
        for key, value in self.questions.iteritems():
            if statement.variable in value:
                variable_exists = True
                
            if variable_exists:
                return

        exitProgram("Variable {} is referenced, but does not exist.".format(statement.variable))
        return


    # Check for duplicate question declarations with different types.
    def checkDuplicateQuestions(self, question):
        if question in self.questions.keys():
            print "Warning: question {} is asked twice.".format(question)
            return question + "dup"

        return question


    # Check for conditional that is not of the type boolean.
    def checkConditional(self, statement):
        for key, value in self.questions.iteritems():
            if statement.expression.variable in value and (value[1] != BOOLEAN_UNICODE and value[1] != INTEGER_UNICODE):
                exitProgram("Condition {} is not of type boolean or integer.".format(statement.expression.variable))
                
        return


    def checkInvalidOperations(self, statement):
        operator = statement.op

        left_type = self.getStatementType(statement.left)
        right_type = self.getStatementType(statement.right)

        self.checkNegation(statement.left, left_type)
        self.checkNegation(statement.right, right_type)

        overall_type = self.checkOperation(statement, left_type, right_type, operator)
        self.checkNegation(statement, overall_type)

        return overall_type


    def getStatementType(self, statement):
        node_type = statement.getNodeType()
        if node_type is "binop":
            statement_type = self.checkInvalidOperations(statement)

        elif node_type is "unop":
            self.checkUndefinedVariables(statement)
            statement_type = self.getVariableTypes(statement)

        elif node_type is "literal":
            if statement.literal.isdigit():
                statement_type = INTEGER_UNICODE

        return statement_type


    def checkNegation(self, statement, variable_type):
        if statement.negate and variable_type == INTEGER_UNICODE:
            exitProgram("Negation on {} is not allowed.".format(statement))
        return


    def checkDuplicateVariables(self, statement):
        for value in self.questions.values():
            if statement.variable == value[0]:
                exitProgram("Variable {} is already declared.".format(statement.variable))
        return


    def getVariableTypes(self, statement):
        for key, value in self.questions.iteritems():
            if statement.variable in value:
                variable_type = value[1]

        return variable_type


    # Check if the operation has correct input and returns the output type.
    def checkOperation(self, statement, left_type, right_type, operator):
        if operator == "&&" or operator == "||":
            if left_type != BOOLEAN_UNICODE or right_type != BOOLEAN_UNICODE:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        elif operator == "==" or operator == "!=":
            if left_type != right_type:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        elif operator == "<" or operator == ">" or operator == "<=" or operator == ">=":
            if left_type != INTEGER_UNICODE or right_type != INTEGER_UNICODE:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        else:
            if left_type != INTEGER_UNICODE or right_type != INTEGER_UNICODE:
                exitProgram("Operation ({}) has invalid types.".format(statement))
            return INTEGER_UNICODE

        return BOOLEAN_UNICODE


def exitProgram(message):
    print message
    sys.exit()