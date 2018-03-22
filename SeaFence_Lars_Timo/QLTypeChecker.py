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

        # print self.questions
        return
        

    # Do the type check for question nodes and if everything is okay append the question
    # to the dictionary.
    def checkQuestionNodes(self, statement):
        self.checkDuplicateVariables(statement)
        statement.question = self.checkDuplicateQuestions(statement.question)
        self.questions[statement.question] = [statement.var, statement.vartype]
        return


    # Do the type check for conditional nodes that depend on an expression and if everything
    # is okay append the conditional to the dictionary and loop through the statements.
    def checkConditionalWithExpressionNodes(self, statement):
        # todo: implement 1 and 0 for boolean?
        node_type = statement.expression.getNodeType()
        if node_type == "literal":
            exitProgram("Condition {} is not of type boolean.".format(statement.expression))

        elif node_type == "unop":
            self.checkConditionals(statement)

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
            assignment_type = statement.expression.vartype

        elif node_type is "binop":
            assignment_type = self.checkInvalidOperations(statement.expression)

        if assignment_type != statement.vartype:
            exitProgram("Assignment expression type does not match variable type at {}".format(statement))

        statement.name = self.checkDuplicateQuestions(statement.name)
        
        self.questions[statement.name] = [statement.var, statement.vartype, statement.expression]
 
        return


    # Check for references to undefined question variables.
    def checkUndefinedVariables(self, statement):
        variable_exists = False
        for key, value in self.questions.iteritems():
            if statement.var in value:
                variable_exists = True
                
            if variable_exists:
                return

        exitProgram("Variable {} is referenced, but does not exist.".format(statement.var))
        return


    # Check for duplicate question declarations with different types.
    def checkDuplicateQuestions(self, question):
        if question in self.questions.keys():
            print "Warning: question {} is asked twice.".format(question)
            return question + "dup"

        return question


    # Check for conditionals that are not of the type boolean.
    def checkConditionals(self, statement):
        for key, value in self.questions.iteritems():
            if statement.expression.var in value and value[1] != BOOLEAN_UNICODE:
                exitProgram("Condition {} is not of type boolean.".format(statement.expression.var))
                
        return


    # Check for operands of invalid type with regard to operators.
    def checkInvalidOperations(self, statement):
        left_type = ""
        right_type = ""
        operator = statement.op

        if type(statement.left) is BinOpNode:
            left_type = self.checkInvalidOperations(statement.left)

        elif type(statement.left) is UnOpNode:
            self.checkUndefinedVariables(statement.left)
            left_type = self.getVariableTypes(statement.left)

        elif type(statement.left) is LiteralNode:
            if statement.left.literal.isdigit():
                left_type = INTEGER_UNICODE

        if type(statement.right) is BinOpNode:
            right_type = self.checkInvalidOperations(statement.right)

        elif type(statement.right) is UnOpNode:
            self.checkUndefinedVariables(statement.right)
            right_type = self.getVariableTypes(statement.right)

        elif type(statement.right) is LiteralNode:
            if statement.right.literal.isdigit():
                right_type = INTEGER_UNICODE

        self.checkNegation(statement.left, left_type)
        self.checkNegation(statement.right, right_type)
        self.checkNegation(statement, left_type)

        self.checkOperation(statement, left_type, right_type, operator)

        if operator == "<" or operator == ">" or operator == "<=" or operator == ">=" or operator == "==" or operator == "!=":
            return BOOLEAN_UNICODE

        return left_type


    # Check if negation on a given node is allowed.
    def checkNegation(self, statement, variable_type):
        if statement.negate and variable_type == INTEGER_UNICODE:
            exitProgram("Negation on {} is not allowed.".format(statement))
        return


    # Check for duplicate labels.
    def checkDuplicateVariables(self, statement):
        for value in self.questions.values():
            if statement.var == value[0]:
                exitProgram("Variable {} is already declared.".format(statement.var))
        return


    def getVariableTypes(self, statement):
        for key, value in self.questions.iteritems():
            if statement.var in value:
                variable_type = value[1]

        return variable_type


    # Check if the operation has correct input.
    def checkOperation(self, statement, left_type, right_type, operator):
        if operator == "&&" or operator == "||":
            if left_type != BOOLEAN_UNICODE or right_type != BOOLEAN_UNICODE:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        elif operator == "==" or operator == "!=":
            if left_type != right_type:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        else:
            if left_type != INTEGER_UNICODE or right_type != INTEGER_UNICODE:
                exitProgram("Operation ({}) has invalid types.".format(statement))

        return


def exitProgram(message):
    print message
    sys.exit()