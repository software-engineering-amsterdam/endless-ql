import os
import sys
from tkinter import *

Path = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, Path + '/../')


def sel(label, var):
    selection = "You selected the option " + str(var.get())
    label.config(text=selection)


# simply create a frame of which the background can be set
def create_frame(parent, background=None):
    frame = Frame(parent, background=background)
    return frame

# Throw an exception without printing the python stacktrace
def throwError(text):
    print("GUI error:")
    print(text)
    sys.exit(1)