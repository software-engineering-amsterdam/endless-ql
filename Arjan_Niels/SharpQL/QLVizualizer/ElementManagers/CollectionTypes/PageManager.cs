using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.CollectionTypes
{
    public class PageManager : ElementManagerSubCollection
    {
        public PageManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null) : base(identifyer, text, "page", parent, activationExpression)
        {
        }
    }
}
