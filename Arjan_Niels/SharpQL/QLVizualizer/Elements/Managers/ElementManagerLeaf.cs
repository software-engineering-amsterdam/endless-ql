using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;

namespace QLVisualizer.Elements.Managers
{
    public abstract class ElementManagerLeaf : ElementManager
    {
        public ElementManagerLeaf(string identifyer, string text, string xmlName, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, xmlName, controller, activationExpression)
        {
            Parent = parent;
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            return _activationExpression.UsedWidgetIDs;
        }
    }
}
