# Jordy Bottelier & Dennis Kruidenberg
#
# GUI class; 
#
# Each form gets its own frame. Each form_frame consists of a header frame, and a questions frame. 
# The header simply contains the title, and the questions frame contains all of the questions defined
# by the programmer. 
#
# Each question has 2 frames, namely the label frame and the input frame (text and input)

from .gui_imports import *
from .scroll_frame_gui import ScrollFrameGui
from .form_gui import FormGui
from .form_questions import Question


class Gui:

	def __init__(self):
		self.gui = Tk()
		self.mainframe = create_frame(self.gui, background='pink')
		self.mainframe.pack(expand=True, fill='both')
		self.form_frames = []

	# Upon creating a new form, create a new frame which is a child from the mainframe.
	# For every form, create the header frame and questions frame and fill the questions frame
	# with questions
	def create_form(self, header="No Header Text", questions=["Wie is je moeder", "sup"]):
		form = FormGui(self.mainframe, header)
		content_frame = form.get_contents()
		# sfg = ScrollFrameGui(content_frame)

		q = Question(content_frame)

	# Execute the GUI
	def execute(self):
		self.gui.geometry("1500x1000")
		self.gui.mainloop()


# w = Button(gui, height=2, text ="Hello")
# w.pack()