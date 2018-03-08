using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.CollectionTypes
{
    public abstract class ElementManagerSubCollection : ElementManagerCollection
    {
        public ElementManagerSubCollection(string identifyer, string text, string xmlName, ElementManager parent, ExpressionBool activationExpression = null) : base(identifyer, text, xmlName, activationExpression)
        {
            SetParent(parent);
        }
    }
}
