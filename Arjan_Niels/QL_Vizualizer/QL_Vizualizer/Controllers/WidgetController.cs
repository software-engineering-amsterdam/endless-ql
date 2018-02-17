using QL_Vizualizer.Widgets;
using System;
using System.Collections.Generic;
using System.Linq;


namespace QL_Vizualizer.Controllers
{
    /// <summary>
    /// Stores used Widgets
    /// Handles Widget answer/value updates
    /// Abstracts Widget View
    /// </summary>
    public abstract class WidgetController
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
        /// <param name="controller">Instance of an WidgetController</param>
        public static void Initialize(WidgetController controller)
        {
            // Prevent multiple calls to the Initialize function
            // Multiple calls result in the loss of all initialized data
            if (_instance != null)
                throw new InvalidOperationException("Widget controller cannot be initialized multiple times");

            // Define the instance
            _instance = controller;
        }

        /// <summary>
        /// Singleton instance of the MainControler
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
        /// Collection of widgets, dictionary on widget identifyer
        /// </summary>
        protected Dictionary<string, QLWidget> _widgets;

        /// <summary>
        /// Collection of widgets, subscribed to a widget (subscribed to widgetID is stored in the key)
        /// </summary>
        private Dictionary<string, List<QLWidget>> _notifyOnChange;

        public WidgetController()
        {
            _widgets = new Dictionary<string, QLWidget>();
            _notifyOnChange = new Dictionary<string, List<QLWidget>>();
        }

        /// <summary>
        /// Updates the view of a widget
        /// </summary>
        /// <param name="widget">Widget to update</param>
        public abstract void UpdateView(QLWidget widget);

        /// <summary>
        /// Shows all widgets
        /// </summary>
        public abstract void Show();

        /// <summary>
        /// Sets all widgets, overrides existing values
        /// </summary>
        /// <param name="widgets">Widgets to assign</param>
        public void SetWidgets(List<QLWidget> widgets)
        {
            // Convert list input to dictionary
            _widgets = widgets.ToDictionary(o => o.Identifyer, o => o);
        }

        /// <summary>
        /// Subscribe Widget to updates of the (targetID) Widget
        /// </summary>
        /// <param name="targetID">Widgets' id that initiates updates</param>
        /// <param name="widget">Widget to receive updates</param>
        public void ReceiveUpdates(string targetID, QLWidget widget)
        {
            // Create value in dictonary if it does not exist
            if (!_notifyOnChange.ContainsKey(targetID))
                _notifyOnChange.Add(targetID, new List<QLWidget>());

            // Add value to targetID
            if (!_notifyOnChange[targetID].Contains(widget))
                _notifyOnChange[targetID].Add(widget);
        }

        /// <summary>
        /// Start notifying subscribed that the value of the widget has changed
        /// </summary>
        /// <param name="widgetID">ID of the changed widgets' value</param>
        public void ValueUpdate(string widgetID)
        {
            if (_notifyOnChange.ContainsKey(widgetID))
                foreach (QLWidget w in _notifyOnChange[widgetID])
                    w.ReceiveUpdate(widgetID);
        }

        /// <summary>
        /// Get Widget by ID
        /// </summary>
        /// <param name="widgetID">ID of the widget</param>
        /// <returns>Widget associated with the given ID</returns>
        public QLWidget GetWidget(string widgetID)
        {
            return _widgets[widgetID];
        }
    }
}
