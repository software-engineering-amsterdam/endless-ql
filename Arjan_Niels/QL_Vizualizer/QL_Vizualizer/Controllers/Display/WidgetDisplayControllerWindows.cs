using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Controllers.Display
{
    public class WidgetDisplayControllerWindows : IWidgetDisplayController
    {
        public float InitialPosition { get; private set; }

        private Control _formControl;

        public WidgetDisplayControllerWindows(float topMargin)
        {
            InitialPosition = topMargin;
        }

        public float Show(QLWidget widget, float position)
        {
            // Create control
            Control control = CreateControl(widget);

            // Set location of control
            control.Location = new Point((int)position, 0);

            // Calculate bottom position
            int newBottom = control.Height + (int)position;

            // Check if form has enough space, extend if needed
            if (_formControl.Height < newBottom)
                _formControl.Height = newBottom + (int)InitialPosition;

            // Add control to form
            _formControl.Controls.Add(control);

            // Create event

            // Return bottom
            return newBottom;
        }

        private Control CreateControl(QLWidget widget)
        {
            Console.WriteLine(widget.GetType());
            throw new Exception();
        }
    }
}
