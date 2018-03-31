using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class PageManager : ElementManagerSubCollection
    {
        public PageManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null) :
            base(identifier, text, "page", parent, controller, activationExpression)
        {
        }
    }
}
