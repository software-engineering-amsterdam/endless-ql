from test_methods import *
import unittest


class AstTest(unittest.TestCase):
    def testGoodFilesAst(self):
        path = 'Testing/test_files/ql/ast_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            ast = getAstFromString(inputText)
            self.assertEqual(str(ast), outputText, filename)

    def testErrorFilesAst(self):
        path = 'Testing/test_files/ql/ast_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()

            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            file_object.close()


if __name__ == '__main__':
    unittest.main()
