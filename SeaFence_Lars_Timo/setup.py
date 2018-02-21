import os

def setup():
	if(os.path.isdir("antlr_files") == False):
		os.makedirs("antlr_files")

	os.system("java -Xmx500M -cp ""/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"" org.antlr.v4.Tool -Dlanguage=Python3 QL.g4 -o antlr_files -visitor")

if __name__ == '__main__':
    setup()