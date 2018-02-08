using QL_Vizualizer.Controllers.Display;
using QL_Vizualizer.Widgets;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Controllers
{
    public class WidgetController
    {
        // Singleton structure
        #region Singleton
        /// <summary>
        /// Static, one time initialized, instance
        /// </summary>
        private static WidgetController _instance;

        /// <summary>
        /// Defines the WidgetController Singleton instance
        /// </summary>
        /// <param name="displayController">Display controller to be used</param>
        /// <param name="widgets">Initial list of widgets</param>
        /// <returns></returns>
        public static WidgetController Initialize(IWidgetDisplayController displayController, List<QLWidget<object>> widgets)
        {
            // Prevent multiple calls to the Initialize function
            // Multiple calls result in the loss of all initialized data
            if (_instance != null)
                throw new InvalidOperationException("Widget controller cannot be initialized multiple times");

            // Convert list input to dictionary
            Dictionary<string, QLWidget<object>> widgetDictionary = widgets.ToDictionary(o => o.Identifyer, o => o);

            // Define the instance
            _instance = new WidgetController(displayController, widgetDictionary);

            // Use singleton structure to return the value
            return Instance;
        }

        /// <summary>
        /// Singleton instance of the WidgetController
        /// </summary>
        public static WidgetController Instance
        {
            get
            {
                // Throw excetion if the instance is not initialzied
                if (_instance == null)
                    throw new InvalidOperationException("Widget controller was not initiallized");
                return _instance;
            }
        }
        #endregion

        /// <summary>
        /// Display controller to show widgets
        /// </summary>
        private IWidgetDisplayController _displayController;

        /// <summary>
        /// Collection of widgets, dictionary on widget identifyer
        /// </summary>
        private Dictionary<string, QLWidget<object>> _widgets;

        private WidgetController(IWidgetDisplayController displayController, Dictionary<string, QLWidget<object>> widgets)
        {
            _displayController = displayController;
            _widgets = widgets;
        }

        /// <summary>
        /// Displays all widgets
        /// </summary>
        public void Show()
        {
            // Start showing widgets at specified starting position
            float position = _displayController.InitialPosition;

            // Display all widgets, updating their position as the bottom
            // of the last displayed widget.
            foreach(QLWidget<object> widget in _widgets.Values)
                position = _displayController.Show(widget, position);
        }

    }
}
