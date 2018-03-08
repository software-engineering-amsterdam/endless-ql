using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.CollectionTypes
{
    public class FormManager : ElementManagerCollection
    {
        public FormManager(string identifyer, string text, ExpressionBool activationExpression = null) : base(identifyer, text, "form", activationExpression)
        {
        }
    }
}
