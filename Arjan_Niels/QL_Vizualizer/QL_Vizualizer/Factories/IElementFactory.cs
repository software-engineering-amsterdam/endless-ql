using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Factories
{
    public interface IElementFactory<T>
    {
        /// <summary>
        /// Creates (display) Element
        /// </summary>
        /// <param name="widget">Widget to create Element for</param>
        /// <returns>Element</returns>
        T CreateElement(QLWidget widget);

        /// <summary>
        /// Updates specified element for widget
        /// </summary>
        /// <param name="widget">Widget associated with the element</param>
        /// <param name="element">Element to update</param>
        /// <returns>Updated element</returns>
        T UpdateElement(QLWidget widget, T element);
    }
}
