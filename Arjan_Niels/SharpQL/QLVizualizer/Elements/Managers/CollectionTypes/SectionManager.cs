using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class SectionManager : ElementManagerSubCollection
    {
        public SectionManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, "section", parent, controller, activationExpression)
        {
        }
    }
}
