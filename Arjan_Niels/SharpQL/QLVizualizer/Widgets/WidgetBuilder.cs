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

        protected List<QLSValue> _qlsValues;

        public WidgetBuilder(ElementManager elementManager)
        {
            _elementManager = elementManager;
            if (elementManager.GetStyle() == null)
                _qlsValues = new List<QLSValue>();
            else
                _qlsValues = new List<QLSValue>(elementManager.GetStyle().GetStylingValues());
        }

        public abstract T Create();

        public virtual void SetParentStyle(List<QLSValue> elements)
        {
            List<string> ownStyleElements = _qlsValues.Select(element => element.StyleProperty).ToList();
            foreach (QLSValue element in elements)
                if (!ownStyleElements.Contains(element.StyleProperty))
                    _qlsValues.Add(element);
            
            // TODO: HANDLE ERRORS
            string[] errors = new string[0];
            _styleParser?.ParseStyle(_qlsValues, out errors);

        }

        public ElementManager GetElementManager()
        {
            return _elementManager;
        }
    }
}
