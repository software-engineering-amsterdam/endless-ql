using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.CollectionTypes
{
    public class FormManager : ElementManagerCollection
    {
        public FormManager(string identifyer, string text, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, "form", controller, activationExpression)
        {
        }
    }
}
