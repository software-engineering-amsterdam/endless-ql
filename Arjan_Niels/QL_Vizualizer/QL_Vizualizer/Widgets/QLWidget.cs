using QL_Vizualizer.Controllers;
using QL_Vizualizer.Factories;
using System.Linq;

namespace QL_Vizualizer.Widgets
{
    public abstract class QLWidget
    {
        /// <summary>
        /// Unique identifyer of the widget
        /// </summary>
        public string Identifyer { get; private set; }

        public string Text { get; private set; }

        /// <summary>
        /// Indication if the widget should be shown
        /// </summary>
        public bool Active { get; protected set; }

        /// <summary>
        /// Expression for activation evaluation
        /// </summary>
        private Expression<bool> _activationExpression;

        /// <summary>
        /// Widget controller that this widget receives updates from
        /// </summary>
        protected WidgetController _widgetController { get; private set; }

        public QLWidget(string identifyer, string text, WidgetController widgetController, Expression<bool> activationExpression = null)
        {
            Text = text;
            Identifyer = identifyer;
            _activationExpression = activationExpression;
            if (_activationExpression != null)
                Active = _activationExpression.Run();
            else
                Active = true;

            _widgetController = widgetController;

            if (activationExpression != null)
                foreach (string id in activationExpression.WidgetIDs)
                    _widgetController.ReceiveUpdates(id, this);
        }

        /// <summary>
        /// Handles incoming update notifactions
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public virtual void ReceiveUpdate(string updatedIdentifyer)
        {
            if (_activationExpression.WidgetIDs.Contains(updatedIdentifyer))
                Active = _activationExpression.Run();
        }

    }
}
