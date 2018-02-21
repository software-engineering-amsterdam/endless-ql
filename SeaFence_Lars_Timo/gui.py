import Tkinter as tk

class Gui():
	def __init__(self):
		self.window = tk.Tk()
		self.window.minsize(width=200, height=200)
		self.labels = {}
		self.checkBoxes = {}
		self.checkBoxValues = {}

    #add the label to the dict for destroying/changing later and then pack it in the main frame
	def addLabel(self, labelName, text):
	    self.labels[labelName] = tk.Label(self.window, text=text)
	    self.labels[labelName].pack()

    #add checkbox and keep track of its variable/name (use lambda for command later)
	def addCheckBox(self, checkBoxName):
	 	var1 = tk.IntVar()
	 	self.checkBoxValues[checkBoxName] = var1
		self.checkBoxes[checkBoxName] = tk.Checkbutton(self.window, text="male", variable=var1)
		self.checkBoxes[checkBoxName].pack()

	def showWindow(self):
		self.window.mainloop()


