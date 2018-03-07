from test_methods import *


class LexerTest(unittest.TestCase):
    def testGoodFilesLexer(self):
        path = 'Testing/test_files/lexer_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            blockPrint()
            lexer_str = getLexerFromString(inputText)
            enablePrint()
            self.assertEqual(lexer_str, outputText, filename)

    def testErrorFilesLexer(self):
        path = 'Testing/test_files/lexer_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()
            file_object.close()
            blockPrint()
            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            enablePrint()



if __name__ == '__main__':
    unittest.main()
