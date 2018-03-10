using QLVisualizer.Elements.Managers;
using System.Collections.Generic;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetCollectionCreator<T> : WidgetCreator where T : ElementManagerCollection
    {
        public List<WidgetCreator> _children;

        protected T _elementManagerCollection { get; private set; }

        public WidgetCollectionCreator(T elementManagerCollection) : base(elementManagerCollection)
        {
            _children = new List<WidgetCreator>();
            _elementManagerCollection = elementManagerCollection;
        }

        public void AddChild(WidgetCreator widgetCreator)
        {
            _children.Add(widgetCreator);
        }
    }
}
