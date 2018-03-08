using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.ElementManagers
{
    public abstract class ElementManager
    {
        /// <summary>
        /// Unique identifyer of the Element & ElementManager
        /// </summary>
        public string Identifyer { get; private set; }

        /// <summary>
        /// Text of the ElementManager
        /// </summary>
        public string Text { get; private set; }

        /// <summary>
        /// Indication if the Element should be shown
        /// </summary>
        public bool Active { get; protected set; }

        /// <summary>
        /// Parent of this manager
        /// </summary>
        public ElementManager Parent { get; protected set; }


        public ElementManager(string identifyer, string text, ExpressionBool activationExpression = null)
        {
            Text = text;
            Identifyer = identifyer;

            _activationExpression = activationExpression;
            Active = activationExpression == null;
        }


        public abstract IEnumerable<string> GetNotifyWidgetIDs();

        /// <summary>
        /// Sets widgetcontroller and subscribes to value changes
        /// </summary>
        /// <param name="controller">Controller to use</param>
        public virtual void SetController(ElementManagerController controller)
        {
            _widgetController = controller;

            // Subscribe to items from the controller
            if (_activationExpression != null)
            {
                foreach (string id in GetNotifyWidgetIDs())
                    _widgetController.ReceiveUpdates(id, this);
                Active = _activationExpression.Result;
            }
        }

        /// <summary>
        /// Handles incoming update notifactions
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public virtual void ReceiveUpdate(string updatedIdentifyer)
        {
            bool _oldActive = Active;
            if (_activationExpression != null && _activationExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
                Active = _activationExpression.Result;
            _widgetController.UpdateView(this);

            // Value is changed
            if (_oldActive != Active)
                _widgetController.ActiveChanged();
        }


        /// <summary>
        /// Exports Element to XML
        /// </summary>
        /// <returns>XML representation of the element</returns>
        public abstract string ToXML();

        /// <summary>
        /// Sets parent of IElementManager
        /// </summary>
        /// <param name="parent">Parent manager</param>
        public void SetParent(ElementManager parent)
        {
            Parent = parent;
        }

        /// <summary>
        /// Expression for activation evaluation
        /// </summary>
        protected ExpressionBool _activationExpression { get; private set; }

        /// <summary>
        /// ElementManager controller that this ElementManager receives updates from
        /// </summary>
        protected ElementManagerController _widgetController;

    }
}
