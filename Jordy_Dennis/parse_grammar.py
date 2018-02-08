# Jordy Bottelier & Dennis Kruidenberg
# Parse the language and move all generated files neatly to a subfolder

import os
import subprocess

# define grammar filename and python version
grammarName = "QLGrammar"
pythonVersion = "Python3"
destinationFolder = "LexParser"

def main_parser(grammarName, pythonVersion, destinationFolder):
	if(os.path.isdir(destinationFolder) == False):
		os.makedirs(destinationFolder)

	# Parse Language using antlr
	p = subprocess.Popen(["java -jar /usr/local/lib/antlr-4.7.1-complete.jar -Dlanguage="
						+ pythonVersion + " " + grammarName+".g4" + " -o " + destinationFolder + " -visitor"],
		stdout=subprocess.PIPE, shell=True)

	p.communicate()

	