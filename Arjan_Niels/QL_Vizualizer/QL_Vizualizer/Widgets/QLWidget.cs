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
        public bool Active { get; private set; }

        /// <summary>
        /// Expression for activation evaluation
        /// </summary>
        private Expression<bool> _activationExpression;

        public QLWidget(string identifyer, string text, Expression<bool> activationExpression)
        {
            Text = text;
            Identifyer = identifyer;
            _activationExpression = activationExpression;

            if (activationExpression != null)
                foreach (string id in activationExpression.WidgetIDs)
                    WidgetController.Instance.ReceiveUpdates(id, this);
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
