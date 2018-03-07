from QLast import *
import sys

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
            if statement.expression.var in value and value[1] != u'boolean':
                # todo: Proper error handling?
                print "Condition {} is not of type boolean.".format(statement.expression.var)
                
        return


    # Check for operands of invalid type with regard to operators.
    def checkInvalidOperations(self):
        return


    # Check for cyclic dependencies between questions.
    def checkCyclicDependencies(self):
        return


    # Check for duplicate labels.
    def checkDuplicateLabels(self):
        return
