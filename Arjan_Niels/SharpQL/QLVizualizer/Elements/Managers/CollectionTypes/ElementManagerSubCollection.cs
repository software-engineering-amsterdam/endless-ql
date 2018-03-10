using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public abstract class ElementManagerSubCollection : ElementManagerCollection
    {
        public ElementManagerSubCollection(string identifyer, string text, string xmlName, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null) : 
            base(identifyer, text, xmlName, controller, activationExpression)
        {
            Parent = parent;
        }
    }
}
