using QL_Vizualizer.Controllers;
using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Factories
{
    public abstract class ElementFactory<T,Y>
    {
        protected WidgetController _widgetController { get; private set; }

        public ElementFactory(WidgetController widgetController)
        {
            _widgetController = widgetController;
        }

        /// <summary>
        /// Creates (display) Element
        /// </summary>
        /// <param name="widget">Widget to create Element for</param>
        /// <param name="style">Style of the widget to be created</param>
        /// <returns>Element</returns>
        public abstract T CreateElement(QLWidget widget, Y style);

        /// <summary>
        /// Updates specified element for widget
        /// </summary>
        /// <param name="widget">Widget associated with the element</param>
        /// <param name="element">Element to update</param>
        /// <returns>Updated element</returns>
        public abstract T UpdateElement(QLWidget widget, T element);
    }
}
