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

	# list the original files
	p = subprocess.Popen(["ls"], stdout=subprocess.PIPE)
	original_files = p.communicate()[0].decode("utf-8")
	original_files = original_files.split("\n")

	# Parse Language using antlr
	p = subprocess.Popen(["antlr4", "-Dlanguage="+pythonVersion, grammarName + ".g4"], stdout=subprocess.PIPE)
	p.communicate()

	# get all the generated files
	p = subprocess.Popen(["ls"], stdout=subprocess.PIPE)
	all_files = p.communicate()[0].decode("utf-8")
	all_files = all_files.split("\n")
	gen_files = diff(all_files, original_files)



	# move all the generated files to the subdirectory called LexParser
	for name in gen_files:
		os.rename(name, destinationFolder + "/" + name)
	