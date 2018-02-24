using QL_Vizualizer.Factories;
using QL_Vizualizer.Widgets;
using System.Collections.Generic;

namespace QL_Vizualizer.Controllers.Display
{
    public abstract class WidgetDisplayController<T,Y>
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
        protected ElementFactory<T,Y> _elementFactory { get; private set; }

        protected WidgetController _widgetController { get; private set; }

        public Y DefaultStyle { get; private set; }

        public WidgetDisplayController(float initialPosition, ElementFactory<T,Y> elementFactory, WidgetController controller, Y defaultStyle)
        {
            InitialPosition = initialPosition;
            _elementFactory = elementFactory;
            ElementIndex = new Dictionary<string, T>();
            _widgetController = controller;
            DefaultStyle = defaultStyle;
        }

        /// <summary>
        /// Displays widget at specified position
        /// </summary>
        /// <param name="widget">Widget to be shown</param>
        /// <param name="position">X-Position of widget</param>
        /// <returns>Bottom X-Position of placed widget with repsect to all style attributes</returns>
        public abstract float ShowWidget(QLWidget widget, Y style);

        public abstract void SetTitle(string title);

        /// <summary>
        /// Shows display to user
        /// </summary>
        public abstract void ShowDisplay();

        /// <summary>
        /// Displays given errors to the user
        /// </summary>
        /// <param name="errors">Errors to show</param>
        public abstract void ShowError(params string[] errors);

        /// <summary>
        /// Updates the position of the widget with the bottom position of the previous widget
        /// </summary>
        /// <param name="widget">Widget to update</param>
        /// <param name="positionAbove">Bottom of previous/above widget</param>
        /// <param name="style">Style of the widget to update</param>
        /// <returns>Updated style</returns>
        public abstract Y UpdatePosition(QLWidget widget, float positionAbove, Y style);

        protected T CreateElement(QLWidget widget, Y style)
        {
            T element = _elementFactory.CreateElement(widget, style);
            ElementIndex.Add(widget.Identifyer, element);
            return element;
        }

        /// <summary>
        /// Updates the view of a specific widget
        /// </summary>
        /// <param name="widget">Target widget</param>
        public void UpdateView(QLWidget widget)
        {
            _elementFactory.UpdateElement(widget, ElementIndex[widget.Identifyer]);
        }

        public virtual void Reset()
        {
            ElementIndex = new Dictionary<string, T>();
        }
    }
}
