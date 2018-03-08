"""
An expression can be a single variable. Or a combination of a variable, an operator (negation) and multiple other expressions

All of the types are comparable with boolean operators:
If the variable is 0 or unset, the variable will be converted to a boolean False, True otherwise (just like python does it)

Only numerical values will be accepted for comparison operations such as >, <, <=, etc, Mathematical operations are can also only
be performed on numbers (so no + for strings), the exception to this rule is the != and ==, which can be applied to any child as long
as they have the same type

An error will be thrown if the types are incomparible
"""

from .ast_methods import *

""" Expressions with a left and right side, all of the operators that cause this node to exist are listed in the constructor """


class BinaryNode:
    def __init__(self, left, right, op, line):
        self.left = left
        self.right = right
        self.op = op
        self.line = line
        self.numOps = ["<", "<=", ">", ">="]
        self.arithmeticOps = ["+", "-", "/", "*"]
        self.allOps = ["!=", "=="]
        self.boolOps = ["&&", "||"]

    # check the actual expression type
    def checkTypes(self):
        leftType = self.left.checkTypes()
        rightType = self.right.checkTypes()

        # Compare for the numOp (check that both types are floats/ints), we always return a bool in this case
        if (self.op in self.numOps):
            goodType, expType = self.typeCompareNumOp(leftType, rightType)
            if (goodType):
                return bool
            else:
                errorstring = "Incomparible types: " + str(leftType) + " and " + str(rightType) + "; at line " + str(
                    self.line)
                throwError(errorstring)

        # Check if both children are of a numerical type, and return the type
        elif (self.op in self.arithmeticOps):
            goodType, expType = self.typeCompareNumOp(leftType, rightType)
            if (goodType):
                return expType
            else:
                errorstring = "Incomparible types: " + str(leftType) + " and " + str(rightType) + "; at line " + str(
                    self.line)
                throwError(errorstring)


        # Boolean operators can always be compared
        # (unset or set is converted to True and False) so we do not need a function to check the types serperately
        elif (self.op in self.boolOps):
            return bool

        # check it for == and !=, which are boolean operators
        elif (self.op in self.allOps):
            return self.typeCompareAllOp(leftType, rightType)

        else:
            errorstring = "Unknown operator at line " + str(self.line)
            throwError(errorstring)

    # Return string representation of expression
    def getName(self):
        return str(self.left.getName()) + self.op + str(self.right.getName())

    # check if both types are numerical (int or float), return true, and if they are not of the same type,
    # return float (small conversion)
    def typeCompareNumOp(self, leftType, rightType):
        if (leftType == float and rightType == float):
            return True, float
        elif (leftType == float and rightType == int):
            return True, float
        elif (leftType == int and rightType == float):
            return True, float
        elif (leftType == int and rightType == int):
            return True, int
        else:
            return False, None

    # Only return the the bool if they are not the same, otherwise throw an error, the only exception are numericals
    # since we can compare an converted int to a float
    def typeCompareAllOp(self, leftType, rightType):
        goodType, _ = self.typeCompareNumOp(leftType, rightType)
        if (goodType):
            return bool
        elif (leftType == rightType):
            return bool
        else:
            errorstring = "Incomparible types: " + str(leftType) + " and " + str(rightType) + "; at line " + str(
                self.line)
            throwError(errorstring)

    # Call linkVars for children
    def linkVars(self, varDict):
        self.left.linkVars(varDict)
        self.right.linkVars(varDict)

    def evaluate(self):
        left_exp = self.left.evaluate()
        right_exp = self.right.evaluate()
        return eval(str(left_exp) + self.op + str(right_exp))

    def __repr__(self):
        return "Binop: {} {} {}".format(self.left, self.op, self.right)


""" Class for expressions with the unary operator ! """


class UnaryNode:
    def __init__(self, left, op, line):
        self.left = left
        self.op = op
        self.line = line

    # Negation of a variable is always a bool, a set variable will be True and an unset variable is false
    def checkTypes(self):
        self.left.checkTypes()
        # If this is all correct, return a bool
        return bool

    # Call linkVars for children
    def linkVars(self, varDict):
        self.left.linkVars(varDict)

    # Return string representation of expression
    def getName(self):
        return self.op + str(self.left.getName())

    def evaluate(self):
        left_exp = self.left.evaluate()
        return eval("not " + str(left_exp))

    def __repr__(self):
        return "Monop: {} {}".format(self.op, self.left)


""" Class for a literal value like 4, or 'apples' """


class LiteralNode:
    def __init__(self, value, _type, line):
        self.value = value
        self.line = line
        self.type = _type

    # return the type for type checking the expression
    def checkTypes(self):
        return self.type

    # We do not have to modify the dict here, so we can pass this method
    def linkVars(self, varDict):
        pass

    # Return string representation of expression
    def getName(self):
        return str(self.value)

    def evaluate(self):
        return self.value

    def __repr__(self):
        return "literal: {}({}) ".format(self.value, self.type)


""" Class for a variable created during an assignment or question operation, all values have a default value """


class VarNode:
    def __init__(self, varname, _type, line, assign=False):
        self.varname = varname
        self.line = line
        self.type = _type
        self.value = None
        if assign:
            self.value = self.getDefaultValue()

    # return the type for type checking the expression
    def checkTypes(self):
        return self.type

    def getVarname(self):
        return self.varname

    def getLine(self):
        return self.line

    # Check if the variable actually exists, if so, set our own type, and now this node will be the node used in the dictionary
    def linkVars(self, varDict):
        if (self.varname in varDict):
            self.type = varDict[self.varname]['type']
            self.value = self.getDefaultValue()

            # we finally link the node of the assignment (or question) to this varNode, in order to get the correct value
            # into the node at the moment of an assignment. It does not matter if a variable is used more than once
            # since we only use the value of the varNode in the assignment node after this.
            varDict[self.varname]['assign'].varNode = self
            varDict[self.varname]['node'] = self
        else:
            errorstring = "Undeclared variable '" + self.varname + "' at line " + str(self.line)
            throwError(errorstring)

    def getDefaultValue(self):
        default_values = {
            int: 0,
            str: "",
            bool: False,
            float: 0.0
        }
        try:
            return default_values[self.type]
        except KeyError:
            errorstring = "Invalid default type: " + str(self.type) + "; at line " + str(self.line)
            throwError(errorstring)

    # Return string representation of expression
    def getName(self):
        return self.varname

    def evaluate(self):
        return self.value

    def __repr__(self):
        return "VarNode: {} {}".format(self.varname, self.type)
