using System.Collections.Generic;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;

namespace QLVisualizer.Widgets.Leaf
{
    public abstract class WidgetLeafBuilder<T, Y> : WidgetBuilder<T> where Y : ElementManagerLeaf
    {
        protected ElementManagerLeaf _elementManagerLeaf { get; private set; }

        public WidgetLeafBuilder(Y elementManagerLeaf) : base(elementManagerLeaf)
        {
            _elementManagerLeaf = elementManagerLeaf;
        }
    }
}
