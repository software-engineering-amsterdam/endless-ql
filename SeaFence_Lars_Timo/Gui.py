# Lars Lokhoff, Timo Dobber
# Gui class that holds all gui related functions to add widgets to the windows
import Tkinter as tk
from Tkinter import *
from ttk import *
class Gui():
    def __init__(self):
        self.window = tk.Tk()
        self.window.geometry('%sx%s' % (self.window.winfo_screenwidth()/3, self.window.winfo_screenheight()))
        # self.window.maxsize(self.window.winfo_screenwidth()/3, self.window.winfo_screenheight())
   
        self.frame = None
        self.notebook = None
        self.frames = {}
        self.values = {}

        self.notebook_set = False
        self.current_page = None

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

    def addBooleanQuestion(self, widget_var, question, text1, text2, render_frame):
        frame = tk.Frame(render_frame, height=2)
        frame.pack(expand=False, fill='both')
        
        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)
        
        var = self.values[widget_var]
        radioButton1 = tk.Radiobutton(frame, text=text1, variable=var, value=0, height=2)
        radioButton2 = tk.Radiobutton(frame, text=text2, variable=var, value=1, height=2)
        radioButton1.pack(side=LEFT)
        radioButton2.pack(side=LEFT)

        self.frames[widget_var] = frame

    def addIntQuestion(self, widget_var, question, render_frame):
        frame = tk.Frame(render_frame, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=question, height=2)
        label.pack(side=LEFT)

        var = self.values[widget_var]
        entry = tk.Entry(frame, textvariable=var)
        entry.pack(side=LEFT)

        self.frames[widget_var] = frame

    def setCurrentStatementFrame(self):
        frame = tk.Frame(self.window)
        frame.pack(expand=False, fill='both')
        self.frame = frame
        return frame

    def addAssignment(self, widget_var, assignment_var, result):
        frame = tk.Frame(self.window, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=assignment_var, height=2)
        label.pack(side=LEFT)

        var = self.values[widget_var]
        label = tk.Label(frame, height=2, textvariable=var)
        label.pack(side=LEFT)

        self.frames[widget_var] = frame
        

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

    def createTKNoTraceVariable(self, var_key, value):
        var = tk.StringVar()
        var.set(str(value))
        self.values[var_key] = var

    def createTKTraceVariable(self, var_key, var_type, update_function):
        if var_type is not "boolean":
            var = tk.StringVar()
            var.trace('w', lambda nm, idx, mode, var=var: self.validateForm(update_function))

        else:
            var = tk.IntVar()
            var.trace("w", update_function)

        self.values[var_key] = var

    def addPage(self, page_name):
        if not self.notebook_set:
            master = Frame(self.window)
            master.pack(fill=BOTH)
            self.notebook = Notebook(master)
            self.notebook.pack(fill=BOTH, padx=2, pady=3)
            self.notebook_set = True

        self.current_page = Frame(self.notebook)
        self.notebook.add(self.current_page, text=page_name)

    def addSection(self, section_name):
        labelframe = LabelFrame(self.current_page, text=section_name)
        labelframe.pack(fill="both", expand="yes")
        left = Label(labelframe, text=section_name)
        left.pack()

        return labelframe

