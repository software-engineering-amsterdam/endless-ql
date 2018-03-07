from QLast import *
import sys

BOOLEAN_UNICODE = u'boolean'
INTEGER_UNICODE = u'int'

class TypeChecker(object):    

    def __init__(self, ast):
        self.ast = ast
        self.questions = {}
        self.conditionals = {}
        self.assignments = {}
        self.getVariables()
        # self.checkUndefinedQuestions()

    # Retrieve the variables/questions/etc from the ast and keep track of them.
    def getVariables(self):
        for statement in self.ast.statements:
            if type(statement) is QuestionNode:
                self.checkDuplicateVariables(statement)
                statement.question = self.checkDuplicateQuestions(statement)
                self.questions[statement.question] = [statement.var, statement.vartype]
            
            elif type(statement) is IfNode or type(statement) is ElifNode:
                if type(statement.expression) is UnOpNode:
                    if statement.expression.negate:
                        self.checkNegation(statement)

                    self.checkUndefinedQuestions(statement)
                    self.checkConditionals(statement)

                elif type(statement.expression) is BinOpNode:
                    self.checkInvalidOperations(statement.expression)
                self.conditionals[statement.expression] = statement.statements
            # elif type(statement) is AssignmentNode:
            #     self.assignments[statement.name] = [statement.var, statement.vartype, statement.expression]
        # print self.questions
        return


    # Check for references to undefined question variables.
    def checkUndefinedQuestions(self, statement):
        variable_exists = False
        for key, value in self.questions.iteritems():
            if statement.expression.var in value:
                variable_exists = True
                
            if variable_exists:
                return

        exitProgram("Variable {} is referenced, but does not exist.".format(statement.expression.var))
        return


    # Check for duplicate question declarations with different types.
    def checkDuplicateQuestions(self, statement):
        if statement.question in self.questions.keys():
            # todo: Proper error handling?
            print "Warning: question {} is asked twice.".format(statement.question)
            return statement.question + "dup"
            # if statement.vartype != self.questions[statement.question][1]:
                # print "wooow"
            # sys.exit()
        return statement.question


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

        if type(statement.left) is UnOpNode and type(statement.right) is UnOpNode:
            operator = statement.op
            for key, value in self.questions.iteritems():
                if statement.left.var in value:
                    left_type = value[1]
                if statement.right.var in value:
                    right_type = value[1]

            if operator == "&&" or operator == "||":
                if left_type != BOOLEAN_UNICODE or right_type != BOOLEAN_UNICODE:
                    exitProgram("Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var))

            elif operator == "==" or operator == "!=":
                if left_type != right_type:
                    exitProgram("Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var))

            else:
                if left_type != INTEGER_UNICODE or right_type != INTEGER_UNICODE:
                    exitProgram("Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var))

        return


    # Check for cyclic dependencies between questions.
    def checkCyclicDependencies(self):
        return


    # todo: check negation of binop
    def checkNegation(self, statement):
        for value in self.questions.values():
            if statement.expression.var == value[0] and value[1] != BOOLEAN_UNICODE:
                exitProgram("Negation on variable {} is not allowed.".format(statement.expression.var))
        return


    # Check for duplicate labels.
    def checkDuplicateVariables(self, statement):
        for value in self.questions.values():
            if statement.var == value[0]:
                exitProgram("Variable {} is already declared.".format(statement.var))
        return


# todo: Proper error handling?
def exitProgram(message):
    print message
    sys.exit()