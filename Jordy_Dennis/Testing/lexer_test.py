from test_methods import *


class QLLexerTest(unittest.TestCase):
    def testGoodFilesLexer(self):
        path = 'Testing/test_files/ql/lexer_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            blockPrint()
            lexer_str = getLexerFromString(inputText)
            enablePrint()
            self.assertEqual(lexer_str, outputText, filename)

    def testErrorFilesLexer(self):
        path = 'Testing/test_files/ql/lexer_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()
            file_object.close()
            blockPrint()
            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            enablePrint()

class QLSLexerTest(unittest.TestCase):
    def testGoodFilesQLSLexer(self):
        # Testing / test_files / qls / lexer_test_files / correct_test / correct_1
        path = 'Testing/test_files/qls/lexer_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            lexer_str = getLexerFromStringQLS(inputText)

            self.assertEqual(lexer_str, outputText, filename)

    def testErrorFilesQLSLexer(self):
        path = 'Testing/test_files/qls/lexer_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()
            file_object.close()
            blockPrint()
            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            enablePrint()


if __name__ == '__main__':
    unittest.main()
