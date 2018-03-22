using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets.Collection
{
    public abstract class WidgetCollectionBuilder<T, Y> : WidgetBuilder<T> where Y : ElementManagerCollection
    {
        protected ICollection<WidgetBuilder<T>> _children { get; private set; }

        protected ElementManagerCollection _elementManagerCollection { get; private set; }

        public WidgetCollectionBuilder(List<QLSValue> qlsElements, Y elementManagerCollection, IWidgetCollectionBuilder<T> parent) : base(qlsElements, parent)
        {
            _children = new List<WidgetBuilder<T>>();
            _elementManagerCollection = elementManagerCollection;
        }

        public override T Create()
        {
            foreach(WidgetBuilder<T> widgetBuilder in _children)
                widgetBuilder.SetParentStyle(_qlsElements);

            return Create(_children.Select(child => child.Create()));
        }

        protected abstract T Create(IEnumerable<T> children);

        public void AddChild(WidgetBuilder<T> child)
        {
            _children.Add(child);
        }
    }
}
