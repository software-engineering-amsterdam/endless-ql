using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class FormManager : ElementManagerCollection
    {
        public FormManager(string identifyer, string text, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, "form", controller, activationExpression)
        {
        }
    }
}
