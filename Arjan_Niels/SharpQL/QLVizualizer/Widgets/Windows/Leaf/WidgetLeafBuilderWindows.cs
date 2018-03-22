using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public abstract class WidgetLeafBuilderWindows<T> : WidgetLeafBuilder<Control, T> where T : ElementManagerLeaf
    {
        public WidgetLeafBuilderWindows(List<QLSValue> qlsElements, T elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, elementManagerLeaf, parent)
        {
        }
    }
}
