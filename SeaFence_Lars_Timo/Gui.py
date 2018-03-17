# Lars Lokhoff, Timo Dobber
# Gui class that holds all gui related functions to add widgets to the windows
import Tkinter as tk
from Tkinter import *

class Gui():
    def __init__(self):
        self.window = tk.Tk()
        self.window.geometry('%sx%s' % (self.window.winfo_screenwidth()/3, self.window.winfo_screenheight()))
        # self.window.maxsize(self.window.winfo_screenwidth()/3, self.window.winfo_screenheight())
   
        self.frame = None
        self.setCurrentStatementFrame
        self.frames = {}
        self.values = {}

    #add slider with given min and max values
    def addSlider(self, name, minVal, maxVal, orientation):
        slider = tk.Scale(self.window, from_=minVal, to=maxVal, orient=orientation)
        self.sliders[name] = slider
        slider.pack()

    def removeSlider(self, name):
        if name in self.sliders:
            self.sliders[name].destroy()
            del self.sliders[name]

    def addSpinbox(self, name, minVal, maxVal):
        spinbox = tk.Spinbox(self.window, from_=minVal, to=maxVal)
        self.spinBoxes[name] = spinBox
        spinbox.pack()

    def removeSpinbox(self, name):
        if name in self.spinboxes:
            self.spinBoxes[name].destroy()
            del self.spinBoxes[name]

    def addDropdown(self, name, items):
        var = tk.StringVar(self.window)
        var.set(items[0])
        dropdown = apply(tk.OptionMenu, (self.window, var) + tuple(items))
        self.dropdowns[name] = dropDown
        dropDown.pack()

    def removeDropdown(self, name):
        if name in self.dropDowns:
            self.dropdowns[name].destroy()
            del self.dropdowns[name]

    def addBooleanQuestion(self, name, question, text1, text2, updateFunction, var=None):
        if not var:
            var = tk.IntVar()
            var.trace("w", updateFunction)

        frame = tk.Frame(self.window, height=2)
        frame.pack(expand=False, fill='both')
        
        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)
        
        radioButton1 = tk.Radiobutton(frame, text=text1, variable=var, value=0, height=2)
        radioButton2 = tk.Radiobutton(frame, text=text2, variable=var, value=1, height=2)
        radioButton1.pack(side=LEFT)
        radioButton2.pack(side=LEFT)
        
        self.values[name] = var
        self.frames[name] = frame

        return frame

    def addIntQuestion(self, name, question, update_function, var=None):
        if not var:
            var = tk.StringVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateForm(update_function))
        
        frame = tk.Frame(self.window, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)

        entry = tk.Entry(frame, textvariable=var)
        entry.pack(side=LEFT)

        self.values[name] = var
        self.frames[name] = frame

        return frame

    def setCurrentStatementFrame(self):
        frame = tk.Frame(self.window)
        frame.pack(expand=False, fill='both')
        self.frame = frame
        return frame

    def addAssignment(self, var_name, name, result, var=None):
        if not var:
            var = tk.StringVar()
            self.values[var_name] = var
            var.set(str(result))
        
        frame = tk.Frame(self.window, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=name, height=2)
        label.pack(side=LEFT)

        label = tk.Label(frame, height=2, textvariable=var)
        label.pack(side=LEFT)
        
        return frame

    def getValue(self, var_name, type):
        if type == "int":
            value = self.values[var_name].get()
            if value == '':
                return 0
            else:
                return int(value) 
        else:
            return self.values[var_name].get()

    def updateValue(self, var_name, text):
        if var_name in self.values:
            self.values[var_name].set(text)

    def validateForm(self, function):
        function()
