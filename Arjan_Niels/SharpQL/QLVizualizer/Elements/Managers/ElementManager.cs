using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Elements.Managers
{
    public abstract class ElementManager
    {
        /// <summary>
        /// Unique identifyer of the Element & ElementManager
        /// </summary>
        public string Identifier { get; private set; }

        /// <summary>
        /// Name of XML tag for this manager
        /// </summary>
        protected string XMLElementName { get; private set; }

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
        public ElementManager Parent;

        /// <summary>
        /// Expression for activation evaluation
        /// </summary>
        protected ExpressionBool _activationExpression { get; private set; }

        /// <summary>
        /// ElementManager controller that this ElementManager receives updates from
        /// </summary>
        protected ElementManagerController _elementManagerController;


        public ElementManager(string identifyer, string text, string xmlName, ElementManagerController controller, ExpressionBool activationExpression = null)
        {
            Text = text;
            Identifier = identifyer;
            XMLElementName = xmlName;
            _elementManagerController = controller;

            _activationExpression = activationExpression;
            Active = activationExpression == null;
        }


        public abstract IEnumerable<string> GetNotifyWidgetIDs();

        /// <summary>
        /// Handles incoming update notifactions
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public virtual void NotifyChange(string updatedIdentifyer)
        {
            bool _oldActive = Active;
            if (_activationExpression != null && _activationExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
                Active = _activationExpression.Result;
            //_elementManagerController.UpdateView(this);

            // Value is changed
            //if (_oldActive != Active)
               // _elementManagerController.ActiveChanged();
        }


        /// <summary>
        /// Exports Element to XML
        /// </summary>
        /// <returns>XML representation of the element</returns>
        public abstract string ToXML();
    }
}
