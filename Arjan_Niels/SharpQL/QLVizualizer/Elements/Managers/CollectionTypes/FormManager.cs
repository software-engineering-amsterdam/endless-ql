using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class FormManager : ElementManagerCollection
    {
        public FormManager(string identifier, string text, ElementManagerController controller, ExpressionBool activationExpression = null) :
            base(identifier, text, "form", controller, activationExpression)
        { }
    }
}
