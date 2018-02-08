# Jordy Bottelier & Dennis Kruidenberg
# Parse the language and move all generated files neatly to a subfolder

import os
import subprocess


# define grammar filename and python version
grammarName = "QLGrammar"
pythonVersion = "Python3"
destinationFolder = "LexParser"

# Parse Language using antlr
p = subprocess.Popen(["antlr4", "-Dlanguage="+pythonVersion, grammarName + ".g4"], stdout=subprocess.PIPE)
p.communicate()

# move all the generated files to the subdirectory called LexParser
os.rename(grammarName + "Lexer.py", destinationFolder + "/" + grammarName + "Lexer.py")
os.rename(grammarName + "Lexer.tokens", destinationFolder + "/" + grammarName + "Lexer.tokens")
os.rename(grammarName + ".tokens", destinationFolder + "/" + grammarName + ".tokens")
os.rename(grammarName + "Listener.py", destinationFolder + "/" + grammarName + "Listener.py")
os.rename(grammarName + "Parser.py", destinationFolder + "/" + grammarName + "Parser.py")
	