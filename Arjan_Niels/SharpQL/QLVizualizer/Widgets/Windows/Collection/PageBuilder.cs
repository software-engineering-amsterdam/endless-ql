using QLVisualizer.Elements.Managers.CollectionTypes;

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
