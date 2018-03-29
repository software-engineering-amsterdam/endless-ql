# Lars Lokhoff, Timo Dobber
# Gui class that holds all gui related functions to add widgets to the windows

import Tkinter as tk
from Tkinter import *
from ttk import *
class Gui():
    def __init__(self):
        self.window = tk.Tk()
        
        self.frame = None
        self.notebook = None
        self.frames = {}
        self.widget_settings = {}
        self.values = {}
        self.pages = {}

        self.notebook_set = False
        self.current_page = None

    def addBooleanQuestion(self, widget_variable, question, text1, text2, render_frame, color="#000000", width=300, font="Times", fontsize="12"):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')
        
        font_options = font + " " + fontsize
        label = tk.Label(frame, text=question, height=2, fg=color, font=font_options)
        label.pack(side=LEFT)
        
        variable = self.values[widget_variable]
        radioButton1 = tk.Radiobutton(frame, text=text1, variable=variable, value=0, height=2)
        radioButton2 = tk.Radiobutton(frame, text=text2, variable=variable, value=1, height=2)
        radioButton1.pack(side=LEFT)
        radioButton2.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addIntQuestion(self, widget_variable, question, render_frame, color="#000000", width=30, font="Times", fontsize="12"):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')

        font_options = font + " " + fontsize
        label = tk.Label(frame, text=question, height=2, font=font_options, fg=color)
        label.pack(side=LEFT)

        variable = self.values[widget_variable]
        entry = tk.Entry(frame, textvariable=variable, font=font_options, fg=color)
        entry.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addAssignment(self, widget_variable, assignment_variable, result, render_frame):
        frame = tk.Frame(render_frame, height=2)
        frame.pack(expand=False, fill='both')

        label = tk.Label(frame, text=assignment_variable, height=2)
        label.pack(side=LEFT)

        variable = self.values[widget_variable]
        label = tk.Label(frame, height=2, textvariable=variable)
        label.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addSpinBoxQuestion(self, widget_variable, question, render_frame, color="#000000", width=30, font="Times", fontsize="12", min=0, max=100):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')

        font_options = font + " " + fontsize
        variable = self.values[widget_variable]
        label = tk.Label(frame, text=question, height=2, font=font_options, fg=color)
        label.pack(side=LEFT)

        spin_box = tk.Spinbox(frame, from_=min, to=max, font=font_options, fg=color, textvariable=variable)
        spin_box.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addSliderQuestion(self, widget_variable, question, render_frame, color="#000000", width=30, font="Times", fontsize="12", min=0, max=100):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')
        
        font_options = font + " " + fontsize
        label = tk.Label(frame, text=question, height=2, fg=color, font=font_options)
        label.pack(side=LEFT)

        variable = self.values[widget_variable]
        slider = tk.Scale(frame, from_=min, to=max, font=font_options, fg=color, variable=variable, orient=HORIZONTAL, width=5)
        slider.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addBooleanDropdownQuestion(self, widget_variable, question, render_frame, color="#000000", width=30, font="Times", fontsize="12"):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')

        font_options = font + " " + fontsize
        label = tk.Label(frame, text=question, height=2, font=font_options, fg=color)
        label.pack(side=LEFT)

        variable = self.values[widget_variable]
        dropdown = tk.OptionMenu(frame, variable, "Yes", "No")
        dropdown.config(font=font_options, fg=color)
        dropdown.pack(side=LEFT)

        self.frames[widget_variable] = frame

    def addCheckboxQuestion(self, widget_variable, question, render_frame, color="#000000", width=30, font="Times", fontsize="12"):
        frame = tk.Frame(render_frame, height=2, width=width)
        frame.pack(expand=False, fill='both')

        font_options = font + " " + fontsize
        label = tk.Label(frame, text=question, height=2, font=font_options, fg=color)
        label.pack(side=LEFT)

        variable = self.values[widget_variable]
        checkbutton = tk.Checkbutton(frame, variable=variable, text="Yes")
        checkbutton.config(font=font_options, fg=color)
        checkbutton.pack(side=LEFT)

        self.frames[widget_variable] = frame
        
    def getValue(self, variable_name, type):
        if type == "int":
            value = self.values[variable_name].get()
            if value == '':
                return 0
            else:
                return int(value) 
        else:
            return self.values[variable_name].get()

    def updateValue(self, variable_name, text):
        if variable_name in self.values:
            self.values[variable_name].set(text)

    def validateForm(self, function):
        function()

    def createTKNoTraceVariable(self, variable_key, value):
        variable = tk.StringVar()
        variable.set(str(value))
        self.values[variable_key] = variable

    def createTKTraceVariable(self, variable_key, variable_type, update_function):
        if variable_type is not "boolean":
            variable = tk.StringVar()
            variable.trace('w', lambda nm, idx, mode, var=variable: self.validateForm(update_function))

        else:
            variable = tk.IntVar()
            variable.trace("w", update_function)

        self.values[variable_key] = variable

    def addPage(self, page_name):
        if not self.notebook_set:
            master = Frame(self.window)
            master.pack(fill="x")
            self.notebook = Notebook(master)
            self.notebook.pack(fill="x", padx=2, pady=3)
            self.notebook_set = True

        self.current_page = Frame(self.notebook)
        self.notebook.add(self.current_page, text=page_name)

        self.pages[page_name] = self.current_page

    def addSection(self, section_name, parent_frame=None):
        if not parent_frame:
            parent_frame = self.current_page

        labelframe = LabelFrame(parent_frame, text=section_name)
        labelframe.pack(fill="x", expand="no")

        return labelframe

    def removeFrame(self, variable_frame):
        if variable_frame in self.frames:
            self.frames[variable_frame].destroy()

    def removeFrames(self, frame_list):
        for variable_frame in frame_list:
            self.removeFrame(variable_frame)
    