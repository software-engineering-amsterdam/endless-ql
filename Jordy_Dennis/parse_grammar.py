# Jordy Bottelier & Dennis Kruidenberg
# Parse the language and move all generated files neatly to a subfolder

import os
import subprocess

# define grammar filename and python version
grammarName = "QLGrammar"
pythonVersion = "Python3"
destinationFolder = "LexParser"

# get the difference between two lists
def diff(first, second):
	second = set(second)
	return [item for item in first if item not in second]


if __name__ == '__main__':
	if(os.path.isdir(destinationFolder) == False):
		os.makedirs(destinationFolder)

	# Parse Language using antlr
	p = subprocess.Popen(["java -jar /usr/local/lib/antlr-4.7.1-complete.jar -Dlanguage="
						+ pythonVersion + " " + grammarName+".g4" + " -o " + destinationFolder],
		stdout=subprocess.PIPE, shell=True)

	p.communicate()

	