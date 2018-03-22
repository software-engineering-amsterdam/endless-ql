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
        public WidgetLeafBuilderWindows(T elementManagerLeaf) : base(elementManagerLeaf)
        {
            WindowsStyler windowsStyler = new WindowsStyler();
            _styler = windowsStyler;
            _styleParser = windowsStyler;
        }
    }
}
