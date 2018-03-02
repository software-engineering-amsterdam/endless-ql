import Tkinter as tk

class Gui():
    def __init__(self):
        self.window = tk.Tk()
        self.window.minsize(width=500, height=300)
        # self.labels = {}
        self.checkBoxes = {}
        self.checkBoxValues = {}
        self.sliders = {}
        self.spinBoxes = {}
        # self.textBoxes = {}
        self.radioButtons = {}
        self.radioButtonValues = {}
        self.dropDowns = {}
        # self.yesNoButtons = {}
        # self.yesNoButtonsValues = {}

    #add the label to the dict for destroying/changing later and then pack it in the main frame
    def addLabel(self, text):
        # self.labels[name] = tk.Label(self.window, text=text)
        # self.labels[name].pack()
        label = tk.Label(self.window, text=text)
        label.pack()
        return label

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

    def addTextBox(self, lines, width):
        textBox = tk.Text(self.window, height=lines, width=width)
        # self.textBoxes[name] = textBox
        textBox.pack()
        return textBox

    def addTextFrame(self, function):
        var = tk.StringVar()
        # textFrame = tk.Frame(self.window)
        textBox = tk.Entry(self.window, textvariable=var)
        # self.textBoxes[name] = textBox
        def notifyChangeTextBox(*args):
            # selection = "You selected the option " + str(entryVariable2.get())
            # label.config(text = selection)
            print "change"
        textBox.pack()
        var.trace("w", notifyChangeTextBox)
        # textBox.pack()
        return textBox, var

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

    def addYesNoRadioButtons(self, text1, text2, command):
        var = tk.IntVar()
        radioButton1 = tk.Radiobutton(self.window, text=text1, variable=var, value=1, command= command)
        radioButton2 = tk.Radiobutton(self.window, text=text2, variable=var, value=0, command= command)
        radioButton1.pack()
        radioButton2.pack()
        return [radioButton1, radioButton2], var
        # self.yesNoButtons[name] = [radioButton1, radioButton2]
        # self.yesNoButtonsValues[name] = var

    def removeYesNoButtons(self, name):
        if name in self.yesNoButtons:
            self.yesNoButtons[name][0].destroy()
            self.yesNoButtons[name][1].destroy()
            del self.yesNoButtons[name]

        if name in self.yesNoButtonsValues:
            del self.yesNoButtonsValues[name]

    def showWindow(self):
        self.window.mainloop()

# def notifyChangeTextBox(*args):
#     # selection = "You selected the option " + str(entryVariable2.get())
#     # label.config(text = selection)
#     print "change"
