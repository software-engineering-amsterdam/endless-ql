# Jordy Bottelier & Dennis Kruidenberg

from .gui_imports import *
from .scroll_frame_gui import ScrollFrameGui

# Any formGUI consists of a main frame, a header within this frame, and a scroll frame.
# The scroll frame can not be changed, only the contents within. 
class FormGui:

	def __init__(self, parent, header="No Header Text", color="orange"):
		self.frame = create_frame(parent, color)
		self.frame.pack(expand=True, fill='both')
		self.header_frame = None
		self.create_header(header, parent=self.frame)

		sfg = ScrollFrameGui(self.frame)
		self.contents = sfg.get_contents()


	# Create the header according to the specified layout
	def create_header(self, header, parent=None ,box_width=200, box_height=5, font_type='Arial', font_size=15, font_color='blue'):
		header_frame = create_frame(parent)
		text = Text(parent, height=box_height, width=box_width)
		header_font = Font(family=font_type, size=font_size, weight='bold')
		text.tag_configure('header_conf', font=header_font)
		text.insert(INSERT, header, 'header_conf')
		text.config(state=DISABLED)
		text.pack(anchor=NW)
		self.header_frame = header_frame

	def get_header(self):
		return self.header_frame

	def get_frame(self):
		return self.frame

	def get_contents(self):
		return self.contents