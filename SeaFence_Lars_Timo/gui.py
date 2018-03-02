import Tkinter as tk
from Tkinter import *

class Gui():
    def __init__(self):
        self.window = tk.Tk()
        self.window.minsize(width=500, height=300)
        self.frame = None
        self.frames = {}
        self.labels = {}
        self.checkBoxes = {}
        self.checkBoxValues = {}
        self.sliders = {}
        self.spinBoxes = {}
        # self.textBoxes = {}
        self.radioButtons = {}
        self.radioButtonValues = {}
        self.dropDowns = {}
        self.questions = {}
        self.entryBoxes = {}
        self.formVariables = {}

    #add the label to the dict for destroying/changing later and then pack it in the main frame
    def addLabel(self, name, text):
        if self.frame: window = self.frame
        else: window = self.window

        self.labels[name] = tk.Label(window, text=text)
        self.labels[name].pack()

    def removeLabel(self, name):
        if name in self.labels:
            self.labels[name].destroy()
            del self.labels[name]

    #add checkbox and keep track of its variable/name (use lambda for command later)
    def addCheckBox(self, name):
        var = tk.IntVar()
        self.checkBoxValues[name] = var
        self.checkBoxes[name] = tk.Checkbutton(self.window, text="male", variable=var)
        self.checkBoxes[name].pack()

    def removeCheckBox(self, name):
        if name in self.checkBoxes:
            self.checkBoxes[name].destroy()
            del self.checkBoxes[name]

        if name in self.checkBoxValues:
            del self.checkBoxValues[name]

    #add slider with given min and max values
    def addSlider(self, name, minVal, maxVal, orientation):
        slider = tk.Scale(self.window, from_=minVal, to=maxVal, orient=orientation)
        self.sliders[name] = slider
        slider.pack()

    def removeSlider(self, name):
        if name in self.sliders:
            self.sliders[name].destroy()
            del self.sliders[name]

    def addSpinBox(self, name, minVal, maxVal):
        spinBox = tk.Spinbox(self.window, from_=minVal, to=maxVal)
        self.spinBoxes[name] = spinBox
        spinBox.pack()

    def removeSpinBox(self, name):
        if name in self.spinBoxes:
            self.spinBoxes[name].destroy()
            del self.spinBoxes[name]

    def addEntryBox(self, name, text):
        entryBox = tk.Entry(self.window, text=text)
        self.entryBoxes[name] = entryBox
        entryBox.pack()

    def removeEntryBox(self, name):
        if name in self.entryBoxes:
            self.entryBoxes[name].destroy()
            del self.entryBoxes[name]

    def addTextBox(self, name, lines, width):
        textBox = tk.Text(self.window, height=lines, width=width)
        # self.textBoxes[name] = textBox
        textBox.pack()
        return textBox

    # def addTextFrame(self, function):
    #     var = tk.StringVar()
    #     # textFrame = tk.Frame(self.window)
    #     textBox = tk.Entry(self.window, textvariable=var)
    #     # self.textBoxes[name] = textBox
    #     def notifyChangeTextBox(*args):
    #         # selection = "You selected the option " + str(entryVariable2.get())
    #         # label.config(text = selection)
    #         print "change"
    #     textBox.pack()
    #     var.trace("w", notifyChangeTextBox)
    #     # textBox.pack()
    #     return textBox, var

    def removeTextBox(self, name):
        if name in self.textBoxes:
            self.textBoxes[name].destroy()
            del self.textBoxes[name]

    def addRadioButton(self, name, text, var, value):
        radioButton = tk.Radiobutton(self.window, text=text, variable=var, value=value)
        self.radioButtons[name] = radioButton
        self.radioButtonValues[name] = var
        radioButton.pack()

    def removeRadioButton(self, name):
        if name in self.radioButtons:
            self.radioButtons[name].destroy()
            del self.radioButtons[name]

        if name in self.radioButtonValues:
            del self.radioButtonValues[name]

    def addDropDown(self, name, items):
        var = tk.StringVar(self.window)
        var.set(items[0])
        dropDown = apply(tk.OptionMenu, (self.window, var) + tuple(items))
        self.dropDowns[name] = dropDown
        dropDown.pack()

    def removeDropDown(self, name):
        if name in self.dropDowns:
            self.dropDowns[name].destroy()
            del self.dropDowns[name]

    def addBooleanQuestion(self, name, question, text1, text2):
        var = tk.IntVar()
        
        frame = tk.Frame(self.frame, height=2)
        frame.pack(expand=False, fill='both')
        
        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)
        
        radioButton1 = tk.Radiobutton(frame, text=text1, variable=var, value=0, height=2)
        radioButton2 = tk.Radiobutton(frame, text=text2, variable=var, value=1, height=2)
        radioButton1.pack(side=LEFT)
        radioButton2.pack(side=LEFT)
        
        self.questions[name] = var
        self.frames[name] = frame

    def addIntQuestion(self, name, question):
        var = tk.StringVar()

        frame = tk.Frame(self.frame, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)

        entry = tk.Entry(frame, textvariable=var)
        entry.pack(side=LEFT)

        self.questions[name] = var
        self.frames[name] = frame

    def removeQuestion(self, name):
        if name in self.frames:
            self.frames[name].destroy()
            del self.frames[name]

        if name in self.questions:
            del self.questions[name]

    def setCurrentStatementFrame(self):
        frame = tk.Frame(self.window)
        frame.pack(expand=False, fill='both')
        self.frame = frame
        return

    def addFormButton(self):
        frame = tk.Frame(self.frame)
        frame.pack()

        button = tk.Button(frame, text="Submit")
        button.config(command= lambda: self.getVariables())
        button.pack()

        return

    def showWindow(self):
        self.window.mainloop()

    # Loop through current questions and save the values on clicking the button if the input type is correct.
    # The current questions have to be overwritten with the questions of the new frame if we do this...
    def getVariables(self):
        for question in self.questions:
            if type(self.questions[question].get()) is int:
                self.formVariables[question] = self.questions[question].get()

            # todo: better error handling...
            elif type(self.questions[question].get()) is str and self.questions[question].get().isdigit():
                self.formVariables[question] = self.questions[question].get()

        print self.formVariables
        return

def notifyClick(name, vars):
    print vars[name].get()

def printUpdatedText(name, vars):
    print vars[name].get()
