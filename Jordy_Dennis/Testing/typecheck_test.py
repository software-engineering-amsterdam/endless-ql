
from test_methods import *

class TypeCheckTest(unittest.TestCase):
    def testGoodFilesTypecheck(self):
        path = 'Testing/test_files/typecheck_test_files/correct_test'
        for filename in os.listdir(path):
            inputText, outputText = getInputOutput(path, filename)
            ast = getAstFromString(inputText)
            ast.linkVars()
            typeCheck_output = ast.checkTypes()
            self.assertEqual(str(typeCheck_output), outputText, filename)

    def testErrorFilesTypecheck(self):
        path = 'Testing/test_files/typecheck_test_files/fail_test'
        for filename in os.listdir(path):
            file_object = open(path + "/" + filename, "r")
            inputText = file_object.read()
            file_object.close()
            blockPrint()
            file_object.close()
            ast = getAstFromString(inputText)
            ast.linkVars()

            with self.assertRaises(SystemExit) as cm:
                ast.checkTypes()
            self.assertEqual(cm.exception.code, 1)
            enablePrint()



if __name__ == '__main__':
    unittest.main()