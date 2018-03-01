

from test_methods import *

class LinkVarsTest(unittest.TestCase):
    def testGoodFilesLinkvars(self):
        path = 'Testing/test_files/linkvars_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            ast = getAstFromString(inputText)
            # self.assertEqual(lexer_str, outputText, filename)

    def testErrorFilesLinkvars(self):
        path = 'Testing/test_files/linkvars_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()

            self.assertRaises(Exception, getLexerFromString, inputText, filename)
            file_object.close()


if __name__ == '__main__':
    unittest.main()