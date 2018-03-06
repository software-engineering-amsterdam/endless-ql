using QL_Vizualizer.Controllers;
using QL_Vizualizer.Expression;
using QL_Vizualizer.Expression.Types;
using System.Linq;

namespace QL_Vizualizer.Widgets
{
    public abstract class QLWidget
    {
        /// <summary>
        /// Unique identifyer of the widget
        /// </summary>
        public string Identifyer { get; private set; }

        /// <summary>
        /// Text of the widget
        /// </summary>
        public string Text { get; private set; }

        /// <summary>
        /// Indication if the widget should be shown
        /// </summary>
        public bool Active { get; protected set; }

        /// <summary>
        /// Expression for activation evaluation
        /// </summary>
        private ExpressionBool _activationExpression;

        /// <summary>
        /// Widget controller that this widget receives updates from
        /// </summary>
        protected WidgetController _widgetController { get; private set; }

        public QLWidget(string identifyer, string text, ExpressionBool activationExpression = null)
        {
            Text = text;
            Identifyer = identifyer;

            _activationExpression = activationExpression;
            if (_activationExpression == null)
                Active = true;
            else
            {
                Active = false;
//                foreach(string id in _activationExpression.UsedWidgetIDs)
//                _widgetController.ReceiveUpdates()
            }

//            Active = (_activationExpression == null);// ? true : _activationExpression.Expression();
        }

        /// <summary>
        /// Sets widgetcontroller and subscribes to value changes
        /// </summary>
        /// <param name="controller">Controller to use</param>
        public virtual void SetController(WidgetController controller)
        {
            _widgetController = controller;

            // Subscribe to items from the controller
            if (_activationExpression != null)
                foreach (string id in _activationExpression.UsedWidgetIDs)
                    _widgetController.ReceiveUpdates(id, this);
        }

        /// <summary>
        /// Handles incoming update notifactions
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public virtual void ReceiveUpdate(string updatedIdentifyer)
        {
            if (_activationExpression != null && _activationExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
                Active = _activationExpression.Result;
            _widgetController.UpdateView(this);
        }

    }
}
