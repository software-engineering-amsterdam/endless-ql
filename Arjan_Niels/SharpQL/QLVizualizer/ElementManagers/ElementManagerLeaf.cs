using QLVisualizer.Expression.Types;
using System.Collections.Generic;

namespace QLVisualizer.ElementManagers
{
    public abstract class ElementManagerLeaf : ElementManager
    {
        public ElementManagerLeaf(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null) : base(identifyer, text, activationExpression)
        {
            SetParent(parent);
        }

        public override IEnumerable<string> GetNotifyWidgetIDs()
        {
            return _activationExpression.UsedWidgetIDs;
        }
    }
}
