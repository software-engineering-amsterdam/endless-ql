from test_methods import *


class LinkVarsTest(unittest.TestCase):
    def testGoodFilesLinkvars(self):
        path = 'Testing/test_files/linkvars_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            blockPrint()
            ast = getAstFromString(inputText)
            varDict = ast.linkVars()
            enablePrint()

            self.assertEqual(str(varDict), outputText, filename)

    def testErrorFilesLinkvars(self):
        path = 'Testing/test_files/linkvars_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()
            file_object.close()
            blockPrint()
            ast = getAstFromString(inputText)

            with self.assertRaises(SystemExit) as cm:
                ast.linkVars()
            enablePrint()
            self.assertEqual(cm.exception.code, 1)


if __name__ == '__main__':
    unittest.main()
