using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Controllers
{
    /// <summary>
    /// Implements View of Widgets
    /// </summary>
    /// <typeparam name="T">Widget View Type</typeparam>
    public class WidgetVisualizeController<T> : WidgetController
    {
        /// <summary>
        /// Display controller to show widgets
        /// </summary>
        private WidgetDisplayController<T> _displayController;

        public WidgetVisualizeController(WidgetDisplayController<T> displayController)
        {
            _displayController = displayController;
        }

        /// <summary>
        /// Displays all widgets
        /// </summary>
        public override void Show()
        {
            // Start showing widgets at specified starting position
            float position = _displayController.InitialPosition;

            // Display all widgets, updating their position as the bottom
            // of the last displayed widget.
            foreach(QLWidget widget in _widgets.Values)
                position = _displayController.Show(widget, position);
        }

        /// <summary>
        /// Updates the widgets' view
        /// </summary>
        /// <param name="widget">Widget to update</param>
        public override void UpdateView(QLWidget widget)
        {
            _displayController.UpdateView(widget);
        }
    }
}
