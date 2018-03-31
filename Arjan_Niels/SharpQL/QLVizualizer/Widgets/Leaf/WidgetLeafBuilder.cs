using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;

namespace QLVisualizer.Widgets.Leaf
{
    public abstract class WidgetLeafBuilder<T, Y> : WidgetBuilder<T> where Y : ElementManagerLeaf
    {
        private QLSStyle _style;

        public WidgetLeafBuilder(Y elementManagerLeaf) : base(elementManagerLeaf)
        {
            _style = elementManagerLeaf.GetStyle();
        }

        public override void ApplyParentStyle(params QLSStyle[] styles)
        {
            QLSStyle style;
            if (!RetrieveValidStyle(styles, out style))
                return;

            _style = style.CombineWith(_style);

            string[] errors = new string[0];
            _styleParser?.ParseStyle(_style, out errors);
        }

        private bool RetrieveValidStyle(QLSStyle[] styles, out QLSStyle style)
        {
            style = null;
            foreach (QLSStyle qlsStyle in styles)
                if (qlsStyle.QValueType == GetQValueType())
                {
                    style = qlsStyle;
                    return true;
                }

            return false;
        }

        protected abstract QValueType GetQValueType();
    }
}
