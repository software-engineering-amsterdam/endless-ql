import unittest
import sys
from os import path
sys.path.insert(0, '../..')
from QLSast import *

# Stylesheet node testing class
class TestStylesheet(unittest.TestCase):
    def testStylesheetNodeCorrectInput(self):
        node = StylesheetNode("test")
        self.assertEqual(node.name, "test")
        self.assertEqual(node.pages, [])


    def testStylesheetNodeIncorrectInput(self):
        node = StylesheetNode("test")
        self.assertNotEqual(node.name, 4)
        self.assertNotEqual(node.pages, ["test"])


# Page node testing class
class TestPageNode(unittest.TestCase):
    def testPageNodeCorrectInput(self):
        node = PageNode("name")
        self.assertEqual(node.name, "name")
        self.assertEqual(node.sections, [])
        self.assertEqual(node.default_style_widgets, [])


    def testPageNodeIncorrectQuestion(self):
        node = PageNode("name")
        self.assertNotEqual(node.name, 5)
        self.assertNotEqual(node.sections, None)
        self.assertNotEqual(node.default_style_widgets, None)


# Section node testing class 
class TestSectionNode(unittest.TestCase):
    def testSectionNodeCorrectInput(self):
        node = SectionNode("name")
        self.assertEqual(node.name, "name")
        self.assertEqual(node.sections, [])
        self.assertEqual(node.questions, [])
        self.assertEqual(node.default_style_widgets, [])


    def testSectionNodeIncorrectInput(self):
        node = SectionNode("name")
        self.assertNotEqual(node.name, 5)
        self.assertNotEqual(node.sections, None)
        self.assertNotEqual(node.questions, None)
        self.assertNotEqual(node.default_style_widgets, None)


# Question node testing class
class TestQuestionNode(unittest.TestCase):
    def testQuestionNodeCorrectInput(self):
        node = QuestionNode("variable")
        self.assertEqual(node.variable, "variable")
        self.assertEqual(node.widget, None)


    def testQuestionNodeIncorrectInput(self):
        node = QuestionNode("variable")
        self.assertNotEqual(node.variable, 5)
        self.assertNotEqual(node.widget, "slider")


# Widget node testing class
class TestWidgetNode(unittest.TestCase):
    def testWidgetNodeCorrectInput(self):
        node = WidgetNode("slider")
        self.assertEqual(node.widget, "slider")
        self.assertEqual(node.options, None)
        self.assertEqual(node.min_value, 0)
        self.assertEqual(node.max_value, 0)


    def testWidgetNodeIncorrectInput(self):
        node = WidgetNode("slider")
        self.assertNotEqual(node.widget, 5)
        self.assertNotEqual(node.options, "None")
        self.assertNotEqual(node.min_value, 1)
        self.assertNotEqual(node.max_value, 1)


# Style options testing class
class TestStyleOptionsNode(unittest.TestCase):
    def testStyleOptionsNodeCorrectInput(self):
        node = StyleOptionsNode("variable_type")
        self.assertEqual(node.variable_type, "variable_type")
        self.assertEqual(node.options, None)


    def testStyleOptionsNodeIncorrectInput(self):
        node = StyleOptionsNode("variable_type")
        self.assertNotEqual(node.variable_type, None)
        self.assertNotEqual(node.options, "None")


if __name__ == '__main__':
    unittest.main()