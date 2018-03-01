

def getInputOutput(path, filename):
    file_object = open(path + "/" + filename, "r")
    data = file_object.read().split("---\n")
    file_object.close()
    inputText = data[0].strip("\n")
    outputText = data[1].strip("\n")
    return inputText, outputText