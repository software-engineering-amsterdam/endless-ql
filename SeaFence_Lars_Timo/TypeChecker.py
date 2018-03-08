from QLast import *
import sys

BOOLEAN_UNICODE = u"boolean"
INTEGER_UNICODE = u"int"

class TypeChecker(object):    

    def __init__(self, ast):
        self.ast = ast
        self.questions = {}
        self.conditionals = {}
        self.getVariables(self.ast.statements)
        # self.checkUndefinedVariables()

    # Retrieve the variables/questions/etc from the ast and keep track of them.
    def getVariables(self, statements):
        for statement in statements:
            if type(statement) is QuestionNode:
                self.checkDuplicateVariables(statement)
                statement.question = self.checkDuplicateQuestions(statement.question)
                self.questions[statement.question] = [statement.var, statement.vartype]
            
            elif type(statement) is IfNode or type(statement) is ElifNode:
                if type(statement.expression) is UnOpNode:
                    # if statement.expression.negate:
                    #     self.checkNegation(statement)

                    self.checkConditionals(statement)

                elif type(statement.expression) is BinOpNode:
                    conditional_type = self.checkInvalidOperations(statement.expression)
                    if conditional_type != BOOLEAN_UNICODE:
                        exitProgram("Condition {} is not of type boolean.".format(statement.expression))

                self.conditionals[statement.expression] = statement.statements
                self.getVariables(statement.statements)

            elif type(statement) is ElseNode:
                self.conditionals["else"] = statement.statements
                self.getVariables(statement.statements)

            elif type(statement) is AssignmentNode:
                assignment_type = self.checkInvalidOperations(statement.expression)
                statement.name = self.checkDuplicateQuestions(statement.name)

                if assignment_type != statement.vartype:
                    exitProgram("Assignment expression type does not match variable type at {}".format(statement))
                
                self.questions[statement.name] = [statement.var, statement.vartype, statement.expression]
        
        print len(self.questions)
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
            # todo: Proper error handling?
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
            # self.checkNegation(statement.left, left_type)

        if type(statement.right) is BinOpNode:
            right_type = self.checkInvalidOperations(statement.right)

        elif type(statement.right) is UnOpNode:
            self.checkUndefinedVariables(statement.right)
            right_type = self.getVariableTypes(statement.right)
            # self.checkNegation(statement.right, right_type)

        self.checkNegation(statement.left, left_type)
        self.checkNegation(statement.right, right_type)
        self.checkNegation(statement, left_type)

        self.checkOperation(statement, left_type, right_type, operator)

        if operator == "<" or operator == ">" or operator == "<=" or operator == ">=" or operator == "==" or operator == "!=":
            return BOOLEAN_UNICODE

        return left_type


    # Check for cyclic dependencies between questions.
    def checkCyclicDependencies(self):
        return


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


# todo: Proper error handling?
def exitProgram(message):
    print message
    sys.exit()