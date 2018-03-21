using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.CollectionTypes
{
    public class PageManager : ElementManagerSubCollection
    {
        public PageManager(string identifyer, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null) :
            base(identifyer, text, "page", parent, controller, activationExpression)
        {
        }

        public void Activate()
        {

            // TODO: WAT IS NETTER (cast)?/SWITCH

            foreach (ElementManager manager in Parent.Children)
                (manager as PageManager)?.SetActive(false);

            SetActive(true);
            
            /*switch (manager)
            {
                case PageManager pageManager:
                    if (pageManager.Active)
                        pageManager.SetActive(false);
                    break;
            }*/
        }
    }
}
