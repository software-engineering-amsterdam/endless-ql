using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers
{
    public abstract class ElementManagerCollection : ElementManager
    {
        /// <summary>
        /// Children of this ElementManager
        /// </summary>
        protected List<ElementManager> _children { get; private set; }

        public ElementManagerCollection(string identifyer, string text, string xmlName, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, xmlName, controller, activationExpression)
        {
            _children = new List<ElementManager>();
        }

        /// <summary>
        /// Add child, set parent of ElementManager
        /// </summary>
        /// <param name="elementManager">ElementManager to add as child</param>
        public void AddChild(ElementManager elementManager)
        {
            _children.Add(elementManager);
            elementManager.Parent = this;
        }

        public void AddChildren(IEnumerable<ElementManager> elementManagers)
        {
            foreach (ElementManager elementManager in elementManagers)
                AddChild(elementManager);
        }

        public override void NotifyChange(string updatedIdentifyer)
        {
            // Only trigger if it contains
            if(_activationExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
                base.NotifyChange(updatedIdentifyer);


            // Only send to children if parent is active
            if (Active)
                foreach (ElementManagerLeaf manager in _children)
                    manager.NotifyChange(updatedIdentifyer);
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            // Return children and self
            return _children.SelectMany(o => o.GetNotifyWidgetIDs()).Concat(_activationExpression.UsedWidgetIDs);
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\">{2}</{0}>", XMLElementName, Identifier, string.Join("", _children.Select(o => o.ToXML())));
        }
    }
}
