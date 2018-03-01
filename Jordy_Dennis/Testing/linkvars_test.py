from test_methods import *


class LinkVarsTest(unittest.TestCase):
    def testGoodFilesLinkvars(self):
        path = 'Testing/test_files/linkvars_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            ast = getAstFromString(inputText)
            varDict = ast.linkVars()
            self.assertEqual(str(varDict), outputText, filename)

    # def testErrorFilesLinkvars(self):
    #     path = 'Testing/test_files/linkvars_test_files/fail_test'
    #     for filename in os.listdir(path):
    #         file_object = open(path + "/" + filename, "r")
    #         inputText = file_object.read()
    #         ast = getAstFromString(inputText)

    #         self.assertRaises(Exception, ast.linkVars)
    #         file_object.close()


if __name__ == '__main__':
    unittest.main()
