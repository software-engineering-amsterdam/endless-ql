using QLVisualizer.Controllers;
using QLVisualizer.ElementManagers;

namespace QLVisualizer.Factories
{
    public abstract class ElementFactory<T,Y>
    {
        /// <summary>
        /// Widget controller that contains all widgets
        /// </summary>
        protected ElementManagerController _widgetController { get; private set; }

        public ElementFactory(ElementManagerController widgetController)
        {
            _widgetController = widgetController;
        }

        /// <summary>
        /// Creates (display) Element
        /// </summary>
        /// <param name="widget">Widget to create Element for</param>
        /// <param name="style">Style of the widget to be created</param>
        /// <returns>Element</returns>
        public abstract T CreateElement(ElementManager widget, Y style);

        /// <summary>
        /// Updates specified element for widget
        /// </summary>
        /// <param name="widget">Widget associated with the element</param>
        /// <param name="element">Element to update</param>
        /// <returns>Updated element</returns>
        public abstract T UpdateElement(ElementManager widget, T element);
    }
}
