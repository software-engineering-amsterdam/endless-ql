using QLVisualizer.Elements;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetCreator
    {
        public ElementType ElementType { get; private set; }

        protected WidgetCreator(ElementManager elementManager)
        {
            ElementType = ElementUtils.ElementToEnum(elementManager);
        }
    }
}
