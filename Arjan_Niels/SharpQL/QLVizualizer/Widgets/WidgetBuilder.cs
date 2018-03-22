using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
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

        protected ElementManager _elementManager;

        public WidgetBuilder(List<QLSValue> qlsElements, IWidgetCollectionBuilder<T> parent, ElementManager elementManager)
        {
            _qlsElements = qlsElements;
            if (_qlsElements == null)
                _qlsElements = new List<QLSValue>();
            _elementManager = elementManager;
            //parent?.AddChild(this);
        }

        public abstract T Create();

        public void SetParentStyle(List<QLSValue> elements)
        {
            List<string> ownStyleElements = _qlsElements.Select(element => element.StyleProperty).ToList();
            foreach (QLSValue element in elements)
                if (!ownStyleElements.Contains(element.StyleProperty))
                    _qlsElements.Add(element);

            string[] errors = new string[0];
            _styleParser?.ParseStyle(_qlsElements, out errors);

        }

        public ElementManager GetElementManager()
        {
            return _elementManager;
        }
    }
}
