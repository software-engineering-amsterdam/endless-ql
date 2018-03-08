using QLVisualizer.Factories;
using QLVisualizer.ElementManagers;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Controllers.Display
{
    public abstract class WidgetDisplayController<T, Y> : ElementManagerController where Y : ICloneable
    {
        /// <summary>
        /// X-Position of first widget
        /// </summary>
        public float InitialPosition { get; private set; }

        /// <summary>
        /// Dictonary of all created elements, key: WidgetID, Value: element
        /// </summary>
        public Dictionary<string, T> ElementIndex { get; private set; }

        /// <summary>
        /// Element factory that creates all elements
        /// </summary>
        protected ElementFactory<T, Y> _elementFactory;

        /// <summary>
        /// Style for each widget, key: widgetID, value: style
        /// </summary>
        public Dictionary<string, Y> ElementStyleIndex { get; private set; }

        public Dictionary<string, Y> ActiveElementStyles { get; private set; }

        /// <summary>
        /// Default style in case no style is set
        /// </summary>
        public Y DefaultStyle { get; private set; }

        public WidgetDisplayController(float initialPosition, Y defaultStyle)
        {
            InitialPosition = initialPosition;
            ElementIndex = new Dictionary<string, T>();
            DefaultStyle = defaultStyle;
        }

        /// <summary>
        /// Removes all widgets and shows them again
        /// </summary>
        public override void RefreshWidgets()
        {
            ShowWidgets();
        }

        /// <summary>
        /// Set style for widgets
        /// </summary>
        /// <param name="styles">Styles by widgetID</param>
        public void SetStyles(Dictionary<string, Y> styles)
        {          
            if (ElementStyleIndex == null)      // Case no defaultstyle is set
                ElementStyleIndex = styles;
            else                                // Case defaultstyle already set
                foreach (KeyValuePair<string, Y> style in styles)
                    ElementStyleIndex[style.Key] = style.Value;
        }

        /// <summary>
        /// Set default style for all widgets
        /// </summary>
        private void UpdateDefaultStyle()
        {
            if (ElementStyleIndex == null)      // Case no styles set
                ElementStyleIndex = _widgets.Keys.ToDictionary(o => o, o => DefaultStyle);
            else                                // Case styles already set
                foreach (string s in _widgets.Keys)
                    if (!ElementStyleIndex.ContainsKey(s))
                        ElementStyleIndex.Add(s, DefaultStyle);
        }

        /// <summary>
        /// Remove element by ID
        /// </summary>
        /// <param name="id">widgetID</param>
        public void DestroyElement(string id)
        {
            // Remove from view
            RemoveFromView(ElementIndex[id]);

            // Remove from storage
            ElementIndex.Remove(id);
        }

        /// <summary>
        /// Removes element from view
        /// </summary>
        /// <param name="element">Element to remove</param>
        protected abstract void RemoveFromView(T element);

        /// <summary>
        /// Displays widget at specified position
        /// </summary>
        /// <param name="widget">Widget to be shown</param>
        /// <param name="position">X-Position of widget</param>
        /// <returns>Bottom X-Position of placed widget with repsect to all style attributes</returns>
        public abstract float ShowWidget(ElementManager widget, Y style);

        /// <summary>
        /// Shows form to user
        /// </summary>
        /// <param name="title">Tile of form</param>
        /// <param name="widgets">Widgets on form</param>
        public override void DisplayForm(string title, ElementManager[] widgets)
        {
            SetTitle(title);
            SetWidgets(widgets);
            ShowWidgets();
        }

        /// <summary>
        /// Shows all widgets to the user
        /// </summary>
        public override void ShowWidgets()
        {
            // Ensure styles are set
            UpdateDefaultStyle();

            Dictionary<string, Y> _tempStyle = ElementStyleIndex.ToDictionary(o => o.Key, o => (Y)o.Value.Clone());

            // Start showing widgets at specified starting position
            float position = InitialPosition;

            // Display all widgets, updating their position as the bottom
            // of the last displayed widget.
            foreach (ElementManager widget in _widgets.Values)
                if (widget.Active)
                {
                    _tempStyle[widget.Identifyer] = UpdatePosition(widget, position, _tempStyle[widget.Identifyer]);
                    position = ShowWidget(widget, _tempStyle[widget.Identifyer]);
                }
        }

        /// <summary>
        /// Sets title of the form on view
        /// </summary>
        /// <param name="title">Title to show</param>
        protected abstract void SetTitle(string title);

        /// <summary>
        /// Updates the position of the widget with the bottom position of the previous widget
        /// </summary>
        /// <param name="widget">Widget to update</param>
        /// <param name="positionAbove">Bottom of previous/above widget</param>
        /// <param name="style">Style of the widget to update</param>
        /// <returns>Updated style</returns>
        public abstract Y UpdatePosition(ElementManager widget, float positionAbove, Y style);

        /// <summary>
        /// Creates view element
        /// </summary>
        /// <param name="widget">Widget to show</param>
        /// <param name="style">Style of widget</param>
        /// <returns>View element</returns>
        protected T CreateElement(ElementManager widget, Y style)
        {
            T element = _elementFactory.CreateElement(widget, style);
            ElementIndex.Add(widget.Identifyer, element);
            return element;
        }

        /// <summary>
        /// Updates the view of a specific widget
        /// </summary>
        /// <param name="widget">Target widget</param>
        public override void UpdateView(ElementManager widget)
        {
            if (widget.Active == true)
            {
                if (!ElementIndex.ContainsKey(widget.Identifyer))
                {
                    // TODO: ADD WIDGET ON CORRECT POSITION IN VIEW
                    ShowWidget(widget, DefaultStyle);
                }
                else
                    _elementFactory.UpdateElement(widget, ElementIndex[widget.Identifyer]);
            }
            else if (ElementIndex.ContainsKey(widget.Identifyer))
                DestroyElement(widget.Identifyer);
        }

        /// <summary>
        /// Resets all values that define its state
        /// </summary>
        public override void Reset()
        {
            base.Reset();
            ElementIndex = new Dictionary<string, T>();
            ElementStyleIndex = new Dictionary<string, Y>();
        }
    }
}
