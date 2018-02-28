
import sys
# Throw an exception without printing the python stacktrace
def throwError(text):
    print("QLS Interpreter error:")
    print(text)
    sys.exit(1)