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

        public WidgetCollectionBuilder(List<QLSValue> qlsElements, Y elementManagerCollection, IWidgetCollectionBuilder<T> parent) : base(qlsElements, parent, elementManagerCollection)
        {
            _children = new List<IWidgetBuilder<T>>();
            _elementManagerCollection = elementManagerCollection;
        }

        public override T Create()
        {
            foreach(WidgetBuilder<T> widgetBuilder in _children)
                widgetBuilder.SetParentStyle(_qlsElements);

            return _styler.StyleElement(Create(_children.ToDictionary(child=> child, child => child.Create())));
        }

        protected abstract T Create(Dictionary<IWidgetBuilder<T>, T> children);

        public void AddChild(IWidgetBuilder<T> child)
        {
            _children.Add(child);
        }
    }
}
