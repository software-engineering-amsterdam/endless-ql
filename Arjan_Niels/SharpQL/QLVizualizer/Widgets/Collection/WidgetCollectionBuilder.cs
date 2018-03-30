using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets.Collection
{
    public abstract class WidgetCollectionBuilder<T, Y> : WidgetBuilder<T>, IWidgetCollectionBuilder<T> where Y : ElementManagerCollection
    {
        protected ICollection<IWidgetBuilder<T>> _children { get; private set; }

        protected ElementManagerCollection _elementManagerCollection { get; private set; }

        public WidgetCollectionBuilder(Y elementManagerCollection) : base(elementManagerCollection)
        {
            _children = new List<IWidgetBuilder<T>>();
            _elementManagerCollection = elementManagerCollection;
        }

        public override T Create()
        {
            if (_elementManagerCollection.GetStyle() != null)
            {
                List<QLSValue> qlsValues = new List<QLSValue>(_elementManagerCollection.GetStyle().GetStylingValues());
                foreach (WidgetBuilder<T> widgetBuilder in _children)
                    widgetBuilder.SetParentStyle(qlsValues);
            }

            return _styler.StyleElement(Create(_children.ToDictionary(child=> child, child => child.Create())));
        }

        protected abstract T Create(Dictionary<IWidgetBuilder<T>, T> children);

        public void AddChild(IWidgetBuilder<T> child)
        {
            _children.Add(child);
        }

        public override void SetParentStyle(List<QLSValue> elements)
        {
            base.SetParentStyle(elements);

            // Update children when collection style is changed
            foreach (IWidgetBuilder<T> widgetBuilder in _children)
                widgetBuilder.SetParentStyle(_qlsValues);
        }
    }
}
