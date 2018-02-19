

from .gui_imports import *

# class that returns the correct widget based on the input type
class InputTypeMap:

	def __init__(self, parent):
		self.parent = parent
		pass

	def getWidget(self, question_type):
		q_types ={
			"bool": self.return_bool,
			"text": self.return_text,
		}
		return q_types[question_type]()

	# return boolean textbox widget
	def return_bool(self):
		var = IntVar()
		button = Checkbutton(self.parent, variable=var, background="white")
		button.pack(fill='x')
		return button, var

	def return_text(self):
		pass