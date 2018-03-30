using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets.Collection
{
    public abstract class WidgetCollectionBuilder<T> : WidgetBuilder<T>, IWidgetCollectionBuilder
    {
        protected ICollection<WidgetBuilder<T>> _children { get; private set; }

        protected ElementManagerCollection _elementManagerCollection { get; private set; }

        public WidgetCollectionBuilder(ElementManagerCollection elementManagerCollection) : base(elementManagerCollection)
        {
            _children = new List<WidgetBuilder<T>>();
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

            return _styler.StyleElement(Create(_children.ToDictionary(child=> child as IWidgetBuilder, child => child.Create())));
        }

        protected abstract T Create(Dictionary<IWidgetBuilder, T> children);

        public void AddChild(IWidgetBuilder child)
        {
            // TODO: refactor
            if (child as WidgetBuilder<T> == null)
                throw new InvalidOperationException("Invalid type added!");

            _children.Add(child as WidgetBuilder<T>);
        }

        public override void SetParentStyle(List<QLSValue> elements)
        {
            base.SetParentStyle(elements);

            // Update children when collection style is changed
            foreach (IWidgetBuilder widgetBuilder in _children)
                widgetBuilder.SetParentStyle(_qlsValues);
        }
    }
}
