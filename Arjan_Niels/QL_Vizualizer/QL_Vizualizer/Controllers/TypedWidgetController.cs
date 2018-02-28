using QL_Parser.AST.Nodes;
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
    public class TypedWidgetController<T, Y> : WidgetController
    {
        /// <summary>
        /// Display controller to show widgets
        /// </summary>
        private WidgetDisplayController<T, Y> _displayController;

        /// <summary>
        /// Dictionary of all element styles, Key: widgetID, Value: element style
        /// </summary>
        public Dictionary<string, Y> ElementStyleIndex { get; private set; }

        /// <summary>
        /// Set styles of widgets
        /// </summary>
        /// <param name="styles">Styles by widgetID</param>
        public void SetStyles(Dictionary<string, Y> styles)
        {
            if (ElementStyleIndex == null)
                ElementStyleIndex = styles;
            else
                foreach (KeyValuePair<string, Y> style in styles)
                    ElementStyleIndex[style.Key] = style.Value;
        }

        /// <summary>
        /// Update widgetstyles with default style
        /// Does not override already set styles
        /// </summary>
        private void UpdateDefaultStyle()
        {
            if (ElementStyleIndex == null)
                ElementStyleIndex = _widgets.Keys.ToDictionary(o => o, o => _displayController.DefaultStyle);
            else
                foreach (string s in _widgets.Keys)
                    if (!ElementStyleIndex.ContainsKey(s))
                        ElementStyleIndex.Add(s, _displayController.DefaultStyle);
        }

        /// <summary>
        /// Sets display controller
        /// </summary>
        /// <typeparam name="X">Element type</typeparam>
        /// <typeparam name="Z">Style type</typeparam>
        /// <param name="displayController">Display contoller, types must match with TypedWidgetController</param>
        public override void SetDisplayController<X, Z>(WidgetDisplayController<X, Z> displayController)
        {
            if (typeof(X) != typeof(T) || typeof(Y) != typeof(Z))
                throw new InvalidOperationException("Tried to set displaycontroller with type mismatch.");

            _displayController = displayController as WidgetDisplayController<T, Y>;
            UpdateDefaultStyle();
        }

        /// <summary>
        /// Shows (main) view to user
        /// </summary>
        public override void ShowView()
        {
            if (_displayController == null)
                throw new InvalidOperationException("Display controller is not set, but showview is called.");
            _displayController.ShowDisplay();
        }

        /// <summary>
        /// Displays form
        /// </summary>
        /// <param name="title">Title of form</param>
        /// <param name="widgets">Widgets of form</param>
        public override void DisplayForm(string title, QLWidget[] widgets)
        {
            _displayController.SetTitle("Form: " + title);
            SetWidgets(widgets);
            ShowWidgets();
        }

        /// <summary>
        /// Shows error(s) to user
        /// </summary>
        /// <param name="errors">One or more errors</param>
        public override void ShowError(params string[] errors)
        {
            if (_displayController == null)
                throw new InvalidOperationException("Display controller is not set, cannot display errors.");

            // Forward error display to errors
            _displayController.ShowError(errors);
        }

        /// <summary>
        /// Displays all widgets
        /// </summary>
        public override void ShowWidgets()
        {
            // Ensure styles are set
            UpdateDefaultStyle();

            // Start showing widgets at specified starting position
            float position = _displayController.InitialPosition;

            // Display all widgets, updating their position as the bottom
            // of the last displayed widget.
            foreach (QLWidget widget in _widgets.Values)
                if (widget.Active)
                {
                    ElementStyleIndex[widget.Identifyer] = _displayController.UpdatePosition(widget, position, ElementStyleIndex[widget.Identifyer]);
                    position = _displayController.ShowWidget(widget, ElementStyleIndex[widget.Identifyer]);
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

        public override void Reset()
        {
            base.Reset();
            _displayController.Reset();
            ElementStyleIndex = new Dictionary<string, Y>();
        }
    }
}
