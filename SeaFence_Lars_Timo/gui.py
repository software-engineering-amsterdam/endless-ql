import Tkinter as tk

class Gui():
	def __init__(self):
		self.window = tk.Tk()
		self.window.minsize(width=200, height=200)
		self.labels = {}
		self.checkBoxes = {}
		self.checkBoxValues = {}
		self.sliders = {}
		self.spinBoxes = {}
		self.textBoxes = {}
		self.radioButtons = {}
		self.radioButtonValues = {}

    #add the label to the dict for destroying/changing later and then pack it in the main frame
	def addLabel(self, name, text):
	    self.labels[name] = tk.Label(self.window, text=text)
	    self.labels[name].pack()

    #add checkbox and keep track of its variable/name (use lambda for command later)
	def addCheckBox(self, name):
	 	var = tk.IntVar()
	 	self.checkBoxValues[name] = var
		self.checkBoxes[name] = tk.Checkbutton(self.window, text="male", variable=var)
		self.checkBoxes[name].pack()

    #add slider with given min and max values
	def addSlider(self, name, minVal, maxVal, orientation):
		slider = tk.Scale(self.window, from_=minVal, to=maxVal, orient=orientation)
		self.sliders[name] = slider
		slider.pack()

	def addSpinBox(self, name, minVal, maxVal):
		spinBox = tk.Spinbox(self.window, from_=minVal, to=maxVal)
		self.spinBoxes[name] = spinBox
		spinBox.pack()

	def addTextBox(self, name, lines, width):
		textBox = tk.Text(self.window, height=lines, width=width)
		self.textBoxes[name] = textBox
		textBox.pack()

	def addRadioButton(self, name, text, value):
		var = tk.IntVar()
		radioButton = tk.Radiobutton(self.window, text=text, variable=var, value=value)
		self.radioButtons[name] = radioButton
		self.radioButtonValues[name] = var
		radioButton.pack()

	def showWindow(self):
		self.window.mainloop()


