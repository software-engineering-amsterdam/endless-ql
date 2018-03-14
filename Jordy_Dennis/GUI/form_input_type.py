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
        self.old_value = None
        self.value = value
        self.questionGenerator = questionGenerator
        self.varDict = self.questionGenerator.getVarDict()
        self.varName = varName

    """
        Map the correct widget method to the correct type
    """
    def getWidget(self, question_type):
        q_types = {
            bool: self.return_bool,
            str: self.return_text,
            int: self.return_int,
            float: self.return_decimal
        }
        return q_types[question_type]()

    """
        Return boolean textbox widget
    """
    def return_bool(self):
        var = BooleanVar()
        var.set(self.value)
        var.trace('w', lambda nm, idx, mode, var=var: self.validateBool(var))
        button = Checkbutton(self.parent, variable=var, background="white")
        button.pack(fill='x')
        return button, var


    """
        Return string textbox widget
    """
    def return_text(self):
        var = StringVar()
        var.trace('w', lambda nm, idx, mode, var=var: self.validateString(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    """
        Return integer textbox widget (same as string but a different validation method)
    """
    def return_int(self):
        var = StringVar()
        var.set(self.value)
        self.old_value = 0
        var.trace('w', lambda nm, idx, mode, var=var: self.validateInt(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    """
        Return float textbox widget (same as string but a different validation method)
    """
    def return_decimal(self):
        var = StringVar()
        var.set(self.value)
        self.old_value = 0.0
        var.trace('w', lambda nm, idx, mode, var=var: self.validateFloat(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var



    """ Validation and tracing methods ------------------------------------------------------"""

    """
        Update the boolean value in the AST, and update the questions
    """
    def validateBool(self, var):
        new_val = var.get()

        # save value in vardict
        varNode = self.varDict[self.varName]['node']
        varNode.setVar(new_val)
        self.setNodeValue(new_val)
        # update_questions
        self.questionGenerator.updateQuestions()

        self.old_value = new_val

    """
        Update the string value in the AST, and update the questions
    """
    def validateString(self, var):
        new_val = var.get()

        # save value in vardict
        varNode = self.varDict[self.varName]['node']
        varNode.setVar(new_val)
        self.setNodeValue(new_val)
        # update_questions
        self.questionGenerator.updateQuestions()

        self.old_value = new_val

    """
        Update the Int value in the AST, and update the questions, also validate if the
        input is a correct integer, if it is not, do not change the value
    """
    def validateInt(self, var):
        new_val = var.get()
        try:
            new_val == '' or new_val == '-' or int(new_val)
            if(new_val == '-'):
                new_val = -0.0
            if(new_val == ''):
                new_val = 0
            new_val = int(new_val)

            # save value in vardict
            varNode = self.varDict[self.varName]['node']
            varNode.setVar(new_val)
            self.setNodeValue(new_val)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.old_value = new_val
        except:
            var.set(self.old_value)

    """
        Update the float value in the AST, and update the questions, also validate if the
        input is a correct float, if it is not, do not change the value
    """
    def validateFloat(self, var):
        new_val = var.get()
        try:

            new_val == '' or new_val == '-' or float(new_val)
            if(new_val == '-'):
               new_val = -0.0
            elif(new_val == ''):
                new_val = 0
            new_val = float(new_val)

            # save value in vardict
            varNode = self.varDict[self.varName]['node']
            varNode.setVar(new_val)
            self.setNodeValue(new_val)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.old_value = new_val
        except:
            print("expept")
            var.set(self.old_value)

    """
        Set the value of all the nodes in the varDict nodelist to the new value, this
        is done in order to have the correct values in the AST
    """
    def setNodeValue(self, value):
        for varNode in self.varDict[self.varName]["node_list"]:
            varNode.setVar(value)