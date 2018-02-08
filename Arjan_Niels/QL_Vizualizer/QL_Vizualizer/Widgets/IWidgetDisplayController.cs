using System;

namespace QL_Vizualizer.Widgets
{
    public interface IWidgetDisplayController
    {
        /// <summary>
        /// Shows widget on the specified position
        /// </summary>
        /// <typeparam name="T">Type of widget</typeparam>
        /// <param name="widget">Widget to be displayed</param>
        /// <param name="position">Top-left position of widget</param>
        void Show<T>(QLWidget<T> widget, Tuple<float, float> position);
    }
}
