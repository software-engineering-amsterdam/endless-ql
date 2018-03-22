using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class PageBuilderWindows : WidgetCollectionBuilderWindows<PageManager>
    {
        public PageBuilderWindows(PageManager elementManagerCollection) : base(elementManagerCollection)
        {
        }

        protected override string GetTitleText()
        {
            return string.Format("Page: {0}", _elementManagerCollection.Text);
        }
    }
}
