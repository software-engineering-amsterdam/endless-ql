
from .gui_imports import *
from .form_question import Question


class Page():

	def __init__(self, parent, questionGenerator, page_header='Page1', color='green'):
		self.frame = create_frame(parent, color)



	def show(self):
        self.frame.lift()
