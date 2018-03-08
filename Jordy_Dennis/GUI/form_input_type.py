from .gui_imports import *


# class that returns the correct widget based on the input type
class InputTypeMap:

    def __init__(self, parent, questionGenerator, varName):
        self.parent = parent
        self.old_value = None
        self.questionGenerator = questionGenerator
        self.varDict = self.questionGenerator.getVarDict()
        self.varName = varName

    def getWidget(self, question_type):
        q_types = {
            bool: self.return_bool,
            str: self.return_text,
            int: self.return_int,
            float: self.return_decimal
        }
        return q_types[question_type]()

    # return boolean textbox widget
    def return_bool(self):
        var = IntVar()
        button = Checkbutton(self.parent, variable=var, background="white")
        button.pack(fill='x')
        return button, var

    def return_text(self):
        var = StringVar()
        e = Entry(self.parent, textvariable=var)
        e.pack(fill='x')
        return e, var

    def return_int(self):
        sv = StringVar()
        self.old_value = 0
        sv.trace('w', lambda nm, idx, mode, var=sv: self.validateInt(var))
        e = Entry(self.parent, textvariable=sv)
        e.pack(fill='x')
        return e, sv

    def return_decimal(self):
        sv = StringVar()
        self.old_value = 0
        sv.trace('w', lambda nm, idx, mode, var=sv: self.validateFloat(var))
        e = Entry(self.parent, textvariable=sv)
        e.pack(fill='x')
        return e, sv

    def validateInt(self, var):
        new_val = var.get()
        try:
            new_val == '' or int(new_val)

            # save value in vardict
            varNode = self.varDict[self.var]['node']
            varNode.setVar(new_val)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.old_value = new_val
        except:
            print("EXCEPT")
            var.set(self.old_value)

    def validateFloat(self, var):
        new_val = var.get()
        try:
            new_val == '' or float(new_val)

            # save value in vardict
            varNode = self.varDict[self.var]['node']
            varNode.setVar(new_val)
            # update_questions
            self.questionGenerator.updateQuestions()

            self.old_value = new_val
        except:
            var.set(self.old_value)
