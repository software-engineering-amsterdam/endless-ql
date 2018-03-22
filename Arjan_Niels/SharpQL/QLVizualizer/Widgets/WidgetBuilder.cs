using QLParser.AST.QLS;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetBuilder<T> : IWidgetBuilder<T>
    {
        protected List<QLSValue> _qlsElements { get; private set; }

        protected IStyleParser _styleParser { get; private set; }

        public WidgetBuilder(List<QLSValue> qlsElements, IWidgetCollectionBuilder<T> parent, IStyleParser styleParser)
        {
            _qlsElements = qlsElements;
            _styleParser = styleParser;
            parent?.AddChild(this);
        }

        public abstract T Create();

        public void SetParentStyle(List<QLSValue> elements)
        {
            List<string> ownStyleElements = _qlsElements.Select(element => element.StyleProperty).ToList();
            foreach (QLSValue element in elements)
                if (!ownStyleElements.Contains(element.StyleProperty) && IsCompatible(element.StyleProperty))
                    _qlsElements.Add(element);
        }

        protected abstract bool IsCompatible(string styleElement);
    }
}
