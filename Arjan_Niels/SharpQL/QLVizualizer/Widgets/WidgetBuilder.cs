using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetBuilder<T> : IWidgetBuilder<T>
    {
        protected IStyleParser _styleParser;

        protected IStyler<T> _styler;

        protected ElementManager _elementManager;

        public WidgetBuilder(ElementManager elementManager)
        {
            _elementManager = elementManager;
        }

        public abstract T Create();

        public void SetParentStyle(List<QLSValue> elements)
        {
            List<QLSValue> qlsElements = new List<QLSValue>();
            if (_elementManager.GetStyle() != null)
            {
                qlsElements.AddRange(_elementManager.GetStyle().GetStylingValues());

                List<string> ownStyleElements = qlsElements.Select(element => element.StyleProperty).ToList();
                foreach (QLSValue element in elements)
                    if (!ownStyleElements.Contains(element.StyleProperty))
                        qlsElements.Add(element);
            }

            // TODO: HANDLE ERRORS
            string[] errors = new string[0];
            _styleParser?.ParseStyle(qlsElements, out errors);
        }

        public ElementManager GetElementManager()
        {
            return _elementManager;
        }
    }
}
