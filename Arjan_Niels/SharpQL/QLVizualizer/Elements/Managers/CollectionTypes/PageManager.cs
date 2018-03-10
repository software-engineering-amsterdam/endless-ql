using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class PageManager : ElementManagerSubCollection
    {
        public PageManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, "page", parent, controller, activationExpression)
        {
        }
    }
}
