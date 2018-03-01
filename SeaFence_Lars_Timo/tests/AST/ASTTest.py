import unittest
import sys
from os import path
sys.path.insert(0, '../..')
from QLast import *

# Form node testing class
class TestForm(unittest.TestCase):
    def testFormNodeCorrectInput(self):
        node = FormNode("test")
        self.assertEqual(node.name, "test")
        self.assertEqual(node.statements, [])

    def testFormNodeIncorrectInput(self):
        node = FormNode("test")
        self.assertNotEqual(node.name, 4)

# Question node testing class
class TestQuestionNode(unittest.TestCase):
    def testQuestionNodeCorrectInput(self):
        node = QuestionNode("question?", u"variable", u"boolean")
        self.assertEqual(node.question, "question?")
        self.assertEqual(node.var, u"variable")
        self.assertEqual(node.vartype, u"boolean")

    def testQuestionNodeIncorrectQuestion(self):
        node = QuestionNode(4, u"variable", u"boolean")
        self.assertNotEqual(node.question, "5")
        self.assertNotEqual(node.var, u"boolean")
        self.assertNotEqual(node.vartype, u"variable")

# Assignment node testing class 
class TestAssignmentNode(unittest.TestCase):
    def testQuestionNodeCorrectInput(self):
        unop = UnOpNode(u"variable")
        node = AssignmentNode("name", u"variable", u"integer", unop)
        self.assertEqual(node.name, "name")
        self.assertEqual(node.var, u"variable")
        self.assertEqual(node.vartype, u"integer")
        self.assertEqual(node.expression, unop)

    def testQuestionNodeIncorrectInput(self):
        unop = UnOpNode(u"variable")
        unop2 = UnOpNode(u"variable")
        node = AssignmentNode("name", u"variable", u"integer", unop)
        self.assertNotEqual(node.name, "name2")
        self.assertNotEqual(node.var, u"variable2")
        self.assertNotEqual(node.vartype, u"boolean")
        self.assertNotEqual(node.expression, unop2)

# Binary operator node testing class
class TestBinOpNode(unittest.TestCase):
    def testBinOpNodeCorrectInput(self):
        left = UnOpNode(u"variable")
        right = UnOpNode(u"variable")
        node = BinOpNode(left, "+", right, True)
        self.assertEqual(node.left, left)
        self.assertEqual(node.right, right)
        self.assertEqual(node.op, "+")
        self.assertEqual(node.negate, True)

    def testBinOpNodeIncorrectInput(self):
        left = UnOpNode(u"variable")
        right = UnOpNode(u"variable")
        node = BinOpNode(left, "+", right, True)
        self.assertNotEqual(node.left, right)
        self.assertNotEqual(node.right, left)
        self.assertNotEqual(node.op, "-")
        self.assertNotEqual(node.negate, False)

# Unary operator node testing class
class TestUnopNode(unittest.TestCase):
    def testUnopNodeCorrectInput(self):
        node = UnOpNode(u"variable", True)
        self.assertEqual(node.var, u"variable")
        self.assertEqual(node.negate, True)

    def testUnopNodeIncorrectInput(self):
        node = UnOpNode(u"variable", True)
        self.assertNotEqual(node.var, u"variable2")
        self.assertNotEqual(node.negate, False)

# If node testing class
class TestIfNode(unittest.TestCase):
    def testIfNodeCorrectInput(self):
        unop = UnOpNode(u"variable")
        node = IfNode(unop)
        self.assertEqual(node.expression, unop)
        self.assertEqual(node.statements, [])

    def testIfNodeIncorrectInput(self):
        unop = UnOpNode(u"variable")
        unop2 = UnOpNode(u"variable")        
        node = IfNode(unop)
        self.assertNotEqual(node.expression, unop2)  
        self.assertNotEqual(node.statements, ["test"])

# If node testing class
class TestElifNode(unittest.TestCase):
    def testElseIfNodeCorrectInput(self):
        unop = UnOpNode(u"variable")
        node = ElifNode(unop)
        self.assertEqual(node.expression, unop)
        self.assertEqual(node.statements, [])

    def testElseIfNodeIncorrectInput(self):
        unop = UnOpNode(u"variable")
        unop2 = UnOpNode(u"variable")        
        node = ElifNode(unop)
        self.assertNotEqual(node.expression, unop2)  
        self.assertNotEqual(node.statements, ["test"])

# Else node testing class
class TestElseNode(unittest.TestCase):
    def testElseNodeCorrectInput(self):
        node = ElseNode()
        self.assertEqual(node.statements, [])


    def testElseNodeIncorrectInput(self):
        node = ElseNode()
        self.assertEqual(node.statements, [])
 


if __name__ == '__main__':
    unittest.main()