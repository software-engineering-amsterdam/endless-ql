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
        public static WidgetController Initialize(IWidgetDisplayController displayController)
        {
            // Prevent multiple calls to the Initialize function
            // Multiple calls result in the loss of all initialized data
            if (_instance != null)
                throw new InvalidOperationException("Widget controller cannot be initialized multiple times");

            // Define the instance
            _instance = new WidgetController(displayController);

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
        private Dictionary<string, QLWidget> _widgets;

        private Dictionary<string, List<QLWidget>> _notifyOnChange;

        private WidgetController(IWidgetDisplayController displayController)
        {
            _displayController = displayController;
            _widgets = new Dictionary<string, QLWidget>();
            _notifyOnChange = new Dictionary<string, List<QLWidget>>();
        }

        public void SetWidgets(List<QLWidget> widgets)
        {
            // Convert list input to dictionary
            _widgets = widgets.ToDictionary(o => o.Identifyer, o => o);
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
            foreach(QLWidget widget in _widgets.Values)
                position = _displayController.Show(widget, position);
        }

        public void ReceiveUpdates(string targetID, QLWidget widget)
        {
            // Create value in dictonary if it does not exist
            if (!_notifyOnChange.ContainsKey(targetID))
                _notifyOnChange.Add(targetID, new List<QLWidget>());

            // Add value to targetID
            if (!_notifyOnChange[targetID].Contains(widget))
                _notifyOnChange[targetID].Add(widget);

        }

        public void ValueUpdate(string targetID)
        {
            if (_notifyOnChange.ContainsKey(targetID))
                foreach (QLWidget w in _notifyOnChange[targetID])
                    w.ReceiveUpdate(targetID);
        }

        public QLWidget GetWidget(string widgetID)
        {
            return _widgets[widgetID];
        }

        public void UpdateView(QLWidget widget)
        {
            _displayController.UpdateView(widget);
        }
    }
}
