using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets
{
    public abstract class WidgetBuilder<T> : IWidgetBuilder, ICreatable<T>
    {
        protected IStyleParser _styleParser;

        protected IStyler<T> _styler;

        protected ElementManager _elementManager;

        public WidgetBuilder(ElementManager elementManager)
        {
            _elementManager = elementManager;
        }

        public abstract T Create();

        public abstract void ApplyParentStyle(params QLSStyle[] styles);

        public ElementManager GetElementManager()
        {
            return _elementManager;
        }
    }
}
