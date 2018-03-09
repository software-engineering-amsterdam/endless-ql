# Jordy Bottelier & Dennis Kruidenberg

from .gui_imports import *


# Each scrollFrame contains a scrollbar, a canvas, and a contents_frame.
# The contents_frame can contain widgets. 
# The canvas is only used to attach the scrollbar to the contents frame
class ScrollFrameGui:

    def __init__(self, parent):
        self.frame = create_frame(parent, 'black')
        self.frame.pack(expand=True, fill='both')
        self.canvas, self.contents_frame = self.create_scrollCanvas(self.frame)
        self.canvas.pack(expand=True, fill="both")

        # create a window for the contents frame inside the canvas
        self.window = self.canvas.create_window((0, 0), window=self.contents_frame, anchor='nw')

        # binding correct update functions to canvas and contents
        self.contents_frame.bind("<Configure>", self.onConfigureContentFrame)
        self.canvas.bind("<Configure>", self.onConfigureCanvas)

    # create the canvas, together with the frame that will contain the contents
    def create_scrollCanvas(self, parent):
        canvas = Canvas(parent, background="pink")
        contents_frame = create_frame(canvas, "blue")

        scrollbar = Scrollbar(parent, command=canvas.yview)
        scrollbar.pack(side=RIGHT, fill='both')
        canvas.configure(yscrollcommand=scrollbar.set)
        return canvas, contents_frame

    # used to set the window of the canvas to the total width of the canvas
    def onConfigureCanvas(self, event):
        canvas_width = event.width
        self.canvas.itemconfig(self.window, width=canvas_width)

    # Making sure the scroller stays on the canvas and doesnt allow to scroll to infinity
    def onConfigureContentFrame(self, event):
        self.canvas.configure(scrollregion=self.canvas.bbox('all'))

    # Return contents so widgets can be added
    def get_contents(self):
        return self.contents_frame

    def get_frame(self):
        return self.frame
