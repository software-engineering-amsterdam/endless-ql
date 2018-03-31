using QLParser.AST.QL;
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

        private Dictionary<QValueType, QLSStyle> _styleValues;

        public WidgetCollectionBuilder(ElementManagerCollection elementManagerCollection) : base(elementManagerCollection)
        {
            _children = new List<WidgetBuilder<T>>();
            _elementManagerCollection = elementManagerCollection;
            _styleValues = new Dictionary<QValueType, QLSStyle>();

            AddStyles(elementManagerCollection.GetStyles().ToArray());
        }

        private void AddStyles(params QLSStyle[] styles)
        {
            foreach (QLSStyle style in styles)
            {
                if (!_styleValues.ContainsKey(style.QValueType))
                    _styleValues.Add(style.QValueType, style);
                else
                    _styleValues[style.QValueType] = style.CombineWith(_styleValues[style.QValueType]);
            }
        }

        public override T Create()
        {
            foreach (WidgetBuilder<T> widgetBuilder in _children)
                widgetBuilder.ApplyParentStyle(_styleValues.Values.ToArray());

            return _styler.StyleElement(Create(_children.ToDictionary(child => child as IWidgetBuilder, child => child.Create())));
        }

        protected abstract T Create(Dictionary<IWidgetBuilder, T> children);

        public void AddChild(IWidgetBuilder child)
        {
            // Type check on child, must be of same generic type
            if (child as WidgetBuilder<T> == null)
                throw new InvalidOperationException("Invalid type added!");

            _children.Add(child as WidgetBuilder<T>);
        }

        public override void ApplyParentStyle(params QLSStyle[] elements)
        {
            AddStyles(elements);
            foreach (WidgetBuilder<T> widgetBuilder in _children)
                widgetBuilder.ApplyParentStyle(_styleValues.Values.ToArray());
        }
    }
}
