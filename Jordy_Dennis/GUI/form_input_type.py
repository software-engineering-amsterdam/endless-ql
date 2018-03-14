"""
Class that controls the widgets as well as the tracing of the widgets.
It returns the widget based on the requested type, and checks these widgets for correct input
"""


from .gui_imports import *

class InputTypeMap:

    """
        Varname, vardict and question generator are necessary when changing the values in the AST
    """
    def __init__(self, parent, questionGenerator, varName, value):
        self.parent = parent
        self.oldValue = None
        self.value = value
        self.questionGenerator = questionGenerator
        self.varDict = self.questionGenerator.getVarDict()
        self.varName = varName

    """
        Map the correct widget method to the correct type
    """
    def getWidget(self, questionType):
        qTypes = {
            bool: self.returnBool,
            str: self.returnText,
            int: self.returnInt,
            float: self.returnDecimal
        }
        return qTypes[questionType]()

    """
        Return boolean textbox widget
    """
    def returnBool(self):
        var = BooleanVar()
        var.set(self.value)
        var.trace('w', lambda nm, idx, mode, var=var: self.validateBool(var))
        button = Checkbutton(self.parent, variable=var, background="white")
        button.pack(fill='x')
        return button, var


    """
        Return string textbox widget
    """
    def returnText(self):
        var = StringVar()
        var.trace('w', lambda nm, idx, mode, var=var: self.validateString(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    """
        Return integer textbox widget (same as string but a different validation method)
    """
    def returnInt(self):
        var = StringVar()
        var.set(self.value)
        self.oldValue = 0
        var.trace('w', lambda nm, idx, mode, var=var: self.validateInt(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    """
        Return float textbox widget (same as string but a different validation method)
    """
    def returnDecimal(self):
        var = StringVar()
        var.set(self.value)
        self.oldValue = 0.0
        var.trace('w', lambda nm, idx, mode, var=var: self.validateFloat(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    

    """ Validation and tracing methods ------------------------------------------------------"""

    """
        Update the boolean value in the AST, and update the questions
    """
    def validateBool(self, var):
        newVal = var.get()

        # save value in vardict
        varNode = self.varDict[self.varName]['node']
        varNode.setVar(newVal)
        self.setNodeValue(newVal)
        # update_questions
        self.questionGenerator.updateQuestions()

        self.oldValue = newVal

    """
        Update the string value in the AST, and update the questions
    """
    def validateString(self, var):
        newVal = var.get()

        # save value in vardict
        varNode = self.varDict[self.varName]['node']
        varNode.setVar(newVal)
        self.setNodeValue(newVal)
        # update_questions
        self.questionGenerator.updateQuestions()

        self.oldValue = newVal

    """
        Update the Int value in the AST, and update the questions, also validate if the
        input is a correct integer, if it is not, do not change the value
    """
    def validateInt(self, var):
        newVal = var.get()
        try:
            newVal == '' or int(newVal)
            if(newVal == ''):
                newVal = 0
            newVal = int(newVal)
            # save value in vardict
            varNode = self.varDict[self.varName]['node']
            varNode.setVar(newVal)
            self.setNodeValue(newVal)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.oldValue = newVal
        except:
            var.set(self.oldValue)

    """
        Update the float value in the AST, and update the questions, also validate if the
        input is a correct float, if it is not, do not change the value
    """
    def validateFloat(self, var):
        newVal = var.get()
        try:
            newVal == '' or float(newVal)
            if(newVal == ''):
                newVal = 0.0
            newVal = float(newVal)
            # save value in vardict
            varNode = self.varDict[self.varName]['node']
            varNode.setVar(newVal)
            self.setNodeValue(newVal)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.oldValue = newVal
        except:
            var.set(self.oldValue)

    """
        Set the value of all the nodes in the varDict nodelist to the new value, this
        is done in order to have the correct values in the AST
    """
    def setNodeValue(self, value):
        for varNode in self.varDict[self.varName]["node_list"]:
            varNode.setVar(value)