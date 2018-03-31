using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class SectionManager : ElementManagerSubCollection
    {
        public SectionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifier, text, "section", parent, controller, activationExpression)
        {
        }
    }
}
