# Jordy Bottelier & Dennis Kruidenberg
# Parse the language and move all generated files neatly to a subfolder

import os
import subprocess



def generateParsers(pythonVersion="Python3", destinationFolder="Lexparser"):
    if (os.path.isdir(destinationFolder) == False):
        os.makedirs(destinationFolder)
    grammarName = "QLGrammar"
    # Parse Language using antlr
    p = subprocess.Popen(["java -jar /usr/local/lib/antlr-4.7.1-complete.jar -Dlanguage="
                          + pythonVersion + " " + grammarName + ".g4" + " -o " + destinationFolder + " -visitor"],
                         stdout=subprocess.PIPE, shell=True)
    p.communicate()

    grammarName = "QLSGrammar"
    # Parse Language using antlr
    p = subprocess.Popen(["java -jar /usr/local/lib/antlr-4.7.1-complete.jar -Dlanguage="
                          + pythonVersion + " " + grammarName + ".g4" + " -o " + destinationFolder + " -visitor"],
                         stdout=subprocess.PIPE, shell=True)
    p.communicate()
