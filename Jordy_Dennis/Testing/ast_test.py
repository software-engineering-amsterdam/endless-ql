import unittest
import sys
import os


Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')

from parser import *

class TestAst(unittest.TestCase):
    def testUpper(self):
        input = FileStream('Testing/temp_test')

        node = getAstFromFile(input)
        print(node == "FORMS: [Form: taxOfficeExample, block: []]")
        print( sys.stdout)
        self.assertTrue(node == 'FORMS: [Form: taxOfficeExample, block: []]')

if __name__ == '__main__':
    unittest.main()



