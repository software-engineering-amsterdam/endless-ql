using QL_Vizualizer.Widgets;

namespace QL_Vizualizer.Controllers.Display
{
    public interface IWidgetDisplayController
    {
        /// <summary>
        /// Displays widget at specified position
        /// </summary>
        /// <param name="widget">Widget to be shown</param>
        /// <param name="position">X-Position of widget</param>
        /// <returns>Bottom X-Position of placed widget with repsect to all style attributes</returns>
        float Show(QLWidget widget, float position);

        /// <summary>
        /// X-Position of first widget
        /// </summary>
        float InitialPosition { get; }
    }
}
