using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Widgets;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Controllers
{
    /// <summary>
    /// Implements View of Widgets
    /// </summary>
    /// <typeparam name="T">Widget View Type</typeparam>
    public class WidgetVisualizeController<T,Y> : WidgetController
    {
        /// <summary>
        /// Display controller to show widgets
        /// </summary>
        private WidgetDisplayController<T,Y> _displayController;

        /// <summary>
        /// Dictionary of all element styles, Key: widgetID, Value: element style
        /// </summary>
        public Dictionary<string, Y> ElementStyleIndex { get; private set; }

        private Y _defaultStyle;

        public WidgetVisualizeController(Y defaultStyle)
        {
            _defaultStyle = defaultStyle;
        }

        public void SetStyles(Dictionary<string, Y> styles)
        {
            foreach (KeyValuePair<string, Y> style in styles)
                ElementStyleIndex[style.Key] = style.Value;
        }

        public override void SetWidgets(List<QLWidget> widgets)
        {
            base.SetWidgets(widgets);
            ElementStyleIndex = _widgets.Keys.ToDictionary(o => o, o => _defaultStyle);
        }

        public override void SetDisplayController<X,Z>(WidgetDisplayController<X, Z> displayController)
        {
            if (typeof(X) != typeof(T) || typeof(Y) != typeof(Z))
                throw new InvalidOperationException("Tried to set displaycontroller with type mismatch");
            _displayController = displayController as WidgetDisplayController<T,Y>;
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
            foreach (QLWidget widget in _widgets.Values)
            {
                ElementStyleIndex[widget.Identifyer] = _displayController.UpdatePosition(widget, position, ElementStyleIndex[widget.Identifyer]);
                position = _displayController.Show(widget, ElementStyleIndex[widget.Identifyer]);
            }
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
