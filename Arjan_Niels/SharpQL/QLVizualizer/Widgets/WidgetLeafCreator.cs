using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetLeafCreator<T> : WidgetCreator where T : ElementManagerLeaf
    {
        protected WidgetCreator _parent;

        public WidgetLeafCreator(WidgetCreator parent, T elementManagerLeaf) : base(elementManagerLeaf)
        {
            _parent = parent;
        }
    }
}
