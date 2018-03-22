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

        # Used for the tracing of variables that get errors when the original trace calls the question generator.
        # This happends with the spinbox and slider
        self.init = True

    """
        Map the correct widget method to the correct type
    """
    def getWidget(self, questionType, widgetType='default', **kwargs):
        qTypes = {
            bool: self.returnBool,
            str: self.returnText,
            int: self.returnInt,
            float: self.returnFloat
        }
        return qTypes[questionType](widgetType, **kwargs)

    """
        Return boolean textbox widget,
        in case of radio, kwargs must contain: minVal, maxVal
    """
    def returnBool(self, widgetType, **kwargs):
        var = BooleanVar()
        var.set(self.value)
        var.trace('w', lambda nm, idx, mode, var=var: self.validateBool(var))
        button = None
        if widgetType == 'default' or widgetType == 'checkbox':
            button = Checkbutton(self.parent, variable=var, background="white")
            button.pack(fill='x')
        elif widgetType == 'radio':
            button = Radiobutton(self.parent, text=kwargs['minVal'], variable=var, value=True)
            button.pack(anchor=W)
            Radiobutton(self.parent, text=kwargs['maxVal'], variable=var, value=False).pack(anchor=W)
        elif widgetType == 'dropdown':
            button = OptionMenu(self.parent, var, 'true', 'false')
            button.pack()
        else:
            throwError("Unknown widget type")
        return button, var

    """
        Return string textbox widget
    """
    def returnText(self, widgetType, **kwargs):
        var = StringVar()
        var.trace('w', lambda nm, idx, mode, var=var: self.validateString(var))
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    """
        Return integer textbox widget (same as string but a different validation method)
        in case of spinbox and slider, kwargs must contain: minVal, maxVal
    """
    def returnInt(self, widgetType, **kwargs):
        var = None
        w = None
        if widgetType == "text" or widgetType == "default":
            var = StringVar()
            var.set(self.value)
            self.oldValue = 0
            var.trace('w', lambda nm, idx, mode, var=var: self.validateInt(var))
            w = Entry(self.parent, textvariable=var)
        elif widgetType == "spinbox":
            var = IntVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateSpinbox(var))
            w = Spinbox(self.parent, from_=kwargs['minVal'], to=kwargs['maxVal'], textvariable=var)
        elif widgetType == "slider":
            var = IntVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateSpinbox(var))
            w = Scale(self.parent, from_=kwargs['minVal'], to=kwargs['maxVal'], variable=var, orient=HORIZONTAL)
        else:
            throwError("Unknown widget type: " + widgetType)
        w.pack(fill='x')
        return w, var

    """
        Return float textbox widget (same as string but a different validation method)
    """
    def returnFloat(self, widgetType, **kwargs):
        var = None
        w = None
        if widgetType == "text" or widgetType == "default":
            var = StringVar()
            var.set(self.value)
            self.oldValue = 0
            var.trace('w', lambda nm, idx, mode, var=var: self.validateFloat(var))
            w = Entry(self.parent, textvariable=var)
        elif widgetType == "spinbox":
            var = DoubleVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateSpinbox(var))
            w = Spinbox(self.parent, from_=kwargs['minVal'], to=kwargs['maxVal'], textvariable=var)
        elif widgetType == "slider":
            var = DoubleVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateSpinbox(var))
            w = Scale(self.parent, from_=kwargs['minVal'], to=kwargs['maxVal'], \
                variable=var, orient=HORIZONTAL, digits = 4, resolution = 0.2)
        else:
            throwError("Unknown widget type: " + widgetType)
        w.pack(fill='x')
        return w, var

    """ Validation and tracing methods ------------------------------------------------------"""

    """
        Update the boolean value in the AST, and update the questions
    """

    def validateBool(self, var):
        newVal = var.get()
        if type(newVal) != bool:
            newVal = bool(newVal)
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
        Validation for spinbox and slider which can hold an integer or float, we do not have to check anything since these
        are limited, they can directly be inserted into the AST
    """
    def validateSpinbox(self, var):
        newVal = var.get()
        varNode = self.varDict[self.varName]['node']
        varNode.setVar(newVal)
        self.setNodeValue(newVal)
        # update_questions
        if(self.init == False):
            self.questionGenerator.updateQuestions()
        else:
            self.init = True

    """
        Update the Int value in the AST, and update the questions, also validate if the
        input is a correct integer, if it is not, do not change the value
    """

    def validateInt(self, var):
        newVal = var.get()
        try:
            newVal == '' or newVal == '-' or int(newVal)
            if (newVal == '-'):
                newVal = -0.0
            if (newVal == ''):
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
        print(newVal)
        try:
            newVal == '' or newVal == '-' or float(newVal)
            if (newVal == '-'):
                newVal = -0.0
            elif (newVal == ''):
                newVal = 0
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
