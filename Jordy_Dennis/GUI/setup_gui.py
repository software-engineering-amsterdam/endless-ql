# Jordy Bottelier & Dennis Kruidenberg
#
# GUI class; 
#
# Each form gets its own frame. Each form_frame consists of a header frame, and a questions frame. 
# The header simply contains the title, and the questions frame contains all of the questions defined
# by the programmer. 
#
# Each question has 2 frames, namely the label frame and the input frame (text and input)

from tkinter import *
from tkinter.font import *
from functools import partial

def sel(label, var):
	selection = "You selected the option " + str(var.get())
	label.config(text = selection)

class Gui:

	def __init__(self):
		self.gui = Tk()
		self.mainframe = self.create_frame(self.gui, background='pink')
		self.mainframe.pack(expand=True, fill='both')
		self.form_frames = []

	# Create question and put it inside the parent, it consists of the label and the input
	def create_question(self, q_text, q_type, parent=None):
		question_frame = self.create_frame(parent, 'red')
		question_frame.pack( fill="both", anchor=NW)
		var = IntVar()
		label = Label(question_frame)
		label.config(text=q_text)
		label.pack(anchor=NW)
		fun = partial(sel, label, var)
		r1 = Radiobutton(question_frame, text="Option 1", variable=var, value=1)
		r1.pack(anchor=NW)
		r2 = Radiobutton(question_frame, text="Option 2", variable=var, value=2)
		r2.pack(anchor=NW)
		r3 = Radiobutton(question_frame, text="Option 3", variable=var, value=3)
		r3.pack(anchor=NW)


	def create_scrollFrame(self, parent):
		canvas = Canvas(parent)
		frame = Frame(canvas)
		myscrollbar=Scrollbar(myframe,orient="vertical",command=canvas.yview)
		canvas.configure(yscrollcommand=myscrollbar.set)
		

	# Upon creating a new form, create a new frame which is a child from the mainframe.
	# For every form, create the header frame and questions frame and fill the questions frame
	# with questions
	def create_form(self, header="Test", questions=["Wie is je moeder", "Steek ik haar neer?", "3"]):
		form_frame = self.create_frame(self.mainframe, "green")
		form_frame.pack(expand=True, fill='both')
		self.create_header(header, parent=form_frame)

		questions_frame = self.create_frame(form_frame, "blue")
		questions_frame.pack(expand=True, fill='both')
		for x in range(0,10):
			for q in questions:
				self.create_question(q, 'radio', parent=questions_frame)
				# break

	# Create the header according to the specified layout
	def create_header(self, header, parent=None ,box_width=200, box_height=5, font_type='Arial', font_size=15, font_color='blue'):
		header_frame = self.create_frame(parent)
		text = Text(parent, height=box_height, width=box_width)
		header_font = Font(family=font_type, size=font_size, weight='bold')
		text.tag_configure('header_conf', font=header_font)
		text.insert(INSERT, header, 'header_conf')
		text.config(state=DISABLED)
		text.pack(anchor=NW)

	# simply create a frame of which the background can be set
	def create_frame(self, parent, background='white'):
		frame = Frame(parent, background=background)
		return frame

	# Execute the GUI
	def execute(self):
		self.gui.geometry("1500x1000")
		self.gui.mainloop()


# w = Button(gui, height=2, text ="Hello")
# w.pack()