using QLParser.AST.QLS;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetBuilder<T> : IWidgetBuilder<T>
    {
        protected List<QLSValue> _qlsElements { get; private set; }

        protected IStyleParser _styleParser;

        protected IStyler<T> _styler;

        public WidgetBuilder(List<QLSValue> qlsElements, IWidgetCollectionBuilder<T> parent)
        {
            _qlsElements = qlsElements;
            parent?.AddChild(this);          
        }

        public abstract T Create();

        public void SetParentStyle(List<QLSValue> elements)
        {
            List<string> ownStyleElements = _qlsElements.Select(element => element.StyleProperty).ToList();
            foreach (QLSValue element in elements)
                if (!ownStyleElements.Contains(element.StyleProperty))
                    _qlsElements.Add(element);

            string[] errors;
            _styleParser.ParseStyle(_qlsElements, out errors);
        }
    }
}
