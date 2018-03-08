using QLVisualizer.Expression.Types;
using System.Collections.Generic;

namespace QLVisualizer.ElementManagers
{
    public abstract class ElementManagerLeaf : ElementManager
    {
        public ElementManagerLeaf(string identifyer, string text, string xmlName, ElementManager parent, ExpressionBool activationExpression = null) : base(identifyer, text, xmlName, activationExpression)
        {
            SetParent(parent);
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            return _activationExpression.UsedWidgetIDs;
        }
    }
}
