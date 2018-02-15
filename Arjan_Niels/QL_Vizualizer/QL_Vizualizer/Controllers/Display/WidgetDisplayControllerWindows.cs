using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using QL_Vizualizer.Factories;
using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Controllers.Display
{
    public class WidgetDisplayControllerWindows : IWidgetDisplayController
    {
        public float InitialPosition { get; private set; }

        private Control _formControl;
        private Dictionary<string, Control> _controlIndex;

        public WidgetDisplayControllerWindows(float topMargin, Control form)
        {
            InitialPosition = topMargin;
            _formControl = form;
            _controlIndex = new Dictionary<string, Control>();
        }

        public float Show(QLWidget widget, float position)
        {
            // Create control
            Control control = CreateControl(widget);

            // Set location of control
            control.Location = new Point(0, (int)position);

            // Calculate bottom position
            int newBottom = control.Height + control.Location.Y;

            // Check if form has enough space, extend if needed
            if (_formControl.Height < newBottom)
                _formControl.Height = newBottom + (int)InitialPosition;

            // Add control to form
            _formControl.Controls.Add(control);

            // Return bottom
            return newBottom;
        }

        private Control CreateControl(QLWidget widget)
        {
            Control c = ControlFactory.CreateControl(widget);
            _controlIndex.Add(widget.Identifyer, c);
            return c;
        }

        public void UpdateView(QLWidget widget)
        {
            ControlFactory.UpdateControl(widget, _controlIndex[widget.Identifyer]);
        }
    }
}
