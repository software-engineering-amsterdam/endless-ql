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
                self.checkDuplicateQuestions(statement)
                self.questions[statement.question] = [statement.var, statement.vartype]
            elif type(statement) is IfNode or type(statement) is ElifNode:
                if type(statement.expression) is UnOpNode:
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

        # todo: Proper error handling?
        print "Variable {} is referenced, but does not exist.".format(statement.expression.var)
        return


    # Check for duplicate question declarations with different types.
    def checkDuplicateQuestions(self, statement):
        if statement.question in self.questions.keys():
            # todo: Proper error handling?
            print "Question {} already exists.".format(statement.question)
            sys.exit()
        return


    # Check for conditions that are not of the type boolean.
    def checkConditionals(self, statement):
        for key, value in self.questions.iteritems():
            if statement.expression.var in value and value[1] != BOOLEAN_UNICODE:
                # todo: Proper error handling?
                print "Condition {} is not of type boolean.".format(statement.expression.var)
                
        return


    # Check for operands of invalid type with regard to operators.
    def checkInvalidOperations(self, statement):
        left_type = ""
        right_type = ""
        # print "Checking operation: {}".format(statement)
        if type(statement.left) is UnOpNode and type(statement.right) is UnOpNode:
            operator = statement.op
            for key, value in self.questions.iteritems():
                if statement.left.var in value:
                    left_type = value[1]
                if statement.right.var in value:
                    right_type = value[1]

            if operator == "&&" or operator == "||":
                if left_type != BOOLEAN_UNICODE or right_type != BOOLEAN_UNICODE:
                    # todo: Proper error handling?
                    print "Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var)
                    sys.exit()

            elif operator == "==" or operator == "!=":
                if left_type != right_type:
                    # todo: Proper error handling?
                    print "Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var)
                    sys.exit()

            else:
                if left_type != INTEGER_UNICODE or right_type != INTEGER_UNICODE:
                    # todo: Proper error handling?
                    print "Operation ({} {} {}) has invalid types.".format(statement.left.var, statement.op, statement.right.var)
                    sys.exit()

        return


    # Check for cyclic dependencies between questions.
    def checkCyclicDependencies(self):
        return


    # Check for duplicate labels.
    def checkDuplicateLabels(self):
        return
