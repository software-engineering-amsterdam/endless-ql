using System.Drawing;
using System.Windows.Forms;
using QL_Vizualizer.Factories;
using QL_Vizualizer.Style;
using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Controllers.Display
{
    public class WidgetDisplayControllerWindows : WidgetDisplayController<Control, WindowsStyleProperties>
    {
        /// <summary>
        /// Control element to add all created controls to
        /// </summary>
        private Control _mainControl;

        public WidgetDisplayControllerWindows(float topMargin, Control control, WidgetController widgetController) : base(topMargin, new ControlFactory(widgetController))
        {
            _mainControl = control;
        }

        /// <summary>
        /// Shows specific widget
        /// </summary>
        /// <param name="widget">Widget to show</param>
        /// <param name="position">X position to show widget</param>
        /// <returns></returns>
        public override float Show(QLWidget widget, WindowsStyleProperties style)
        {
            // Create control
            Control control = CreateElement(widget, style);

            // Calculate bottom position
            int newBottom = control.Height + control.Location.Y;

            // Check if form has enough space, extend if needed
            if (_mainControl.Height < newBottom)
                _mainControl.Height = newBottom + (int)InitialPosition;

            // Add control to form
            _mainControl.Controls.Add(control);

            // Return bottom
            return newBottom;
        }

        public override WindowsStyleProperties UpdatePosition(QLWidget widget, float positionAbove, WindowsStyleProperties style)
        {
            style.YPosition += (int)positionAbove;
            return style;
        }
    }
}
