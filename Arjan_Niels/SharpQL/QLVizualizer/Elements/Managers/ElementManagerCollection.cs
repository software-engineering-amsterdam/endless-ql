using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers
{
    public abstract class ElementManagerCollection : ElementManager
    {
        /// <summary>
        /// Children of this ElementManager
        /// </summary>
        public List<ElementManager> Children { get; private set; }

        public ElementManagerCollection(string identifyer, string text, string xmlName, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, xmlName, controller, activationExpression)
        {
            Children = new List<ElementManager>();
        }

        /// <summary>
        /// Add child, set parent of ElementManager
        /// </summary>
        /// <param name="elementManager">ElementManager to add as child</param>
        public void AddChild(ElementManager elementManager)
        {
            Children.Add(elementManager);
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
                foreach (ElementManagerLeaf manager in Children)
                    manager.NotifyChange(updatedIdentifyer);
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            // Return children and self
            return Children.SelectMany(o => o.GetNotifyWidgetIDs()).Concat(_activationExpression.UsedWidgetIDs);
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\">{2}</{0}>", XMLElementName, Identifier, string.Join("", Children.Select(o => o.ToXML())));
        }
    }
}
