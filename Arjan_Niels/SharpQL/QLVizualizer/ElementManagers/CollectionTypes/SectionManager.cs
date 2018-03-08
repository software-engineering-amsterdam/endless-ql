using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.CollectionTypes
{
    public class SectionManager : ElementManagerSubCollection
    {
        public SectionManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null) : base(identifyer, text, "section", parent, activationExpression)
        {
        }
    }
}
