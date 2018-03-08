using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QLVizualizer.Controllers;
using QLVizualizer.Expression.Types;

namespace QLVizualizer.ElementManagers
{
    public abstract class ElementManagerCollection : ElementManager
    {
        /// <summary>
        /// Children of this ElementManager
        /// </summary>
        protected List<ElementManager> _children { get; private set; }

        public ElementManagerCollection(string identifyer, string text, ExpressionBool activationExpression = null) : base(identifyer, text, activationExpression)
        {
        }

        /// <summary>
        /// Add child, set parent of ElementManager
        /// </summary>
        /// <param name="elementManager">ElementManager to add as child</param>
        public void AddChild(ElementManager elementManager)
        {
            _children.Add(elementManager);
            elementManager.SetParent(this);
        }

        public override void SetController(ElementManagerController controller)
        {
            base.SetController(controller);

            // Forward call to children
            foreach (ElementManager manager in _children)
                manager.SetController(controller);
        }

        public override void ReceiveUpdate(string updatedIdentifyer)
        {
            // Only trigger if it contains
            if(_activationExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
                base.ReceiveUpdate(updatedIdentifyer);


            // Only send to children if parent is active
            if (Active)
                foreach (ElementManager manager in _children)
                    manager.ReceiveUpdate(updatedIdentifyer);
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            // Return children and self
            return _children.SelectMany(o => o.GetNotifyWidgetIDs()).Concat(_activationExpression.UsedWidgetIDs);
        }
    }
}
