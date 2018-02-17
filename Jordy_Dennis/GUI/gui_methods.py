
from tkinter import *

def sel(label, var):
	selection = "You selected the option " + str(var.get())
	label.config(text = selection)

# simply create a frame of which the background can be set
def create_frame(parent, background=None):
	frame = Frame(parent, background=background)
	return frame
