using QLParser.AST.QLS;
using QLParser.AST.QLS.Enums;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public abstract class WidgetLeafBuilderWindows<T> : WidgetLeafBuilder<Control, T> where T : ElementManagerLeaf
    {
        protected WidgetType _widgetType;
        protected IList<string> _widgetTypeArguments;

        public WidgetLeafBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, T elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, elementManagerLeaf, parent)
        {
            WindowsStyler windowsStyler = new WindowsStyler();
            _styler = windowsStyler;
            _styleParser = windowsStyler;
            if (widgetSpecification != null)
            {
                _widgetType = widgetSpecification.WidgetType;
                _widgetTypeArguments = widgetSpecification.WidgetTypeArguments;
            }
        }
    }
}
