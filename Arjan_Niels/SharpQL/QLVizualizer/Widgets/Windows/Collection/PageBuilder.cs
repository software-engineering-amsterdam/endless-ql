using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class PageBuilder : WidgetCollectionBuilderWindows<PageManager>
    {
        public PageBuilder(List<QLSValue> qlsElements, PageManager elementManagerCollection, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, elementManagerCollection, parent)
        {
        }

        protected override string GetTitleText()
        {
            return string.Format("Page: {0}", _elementManagerCollection.Text);
        }
    }
}
