using QLVisualizer.Elements.Managers.CollectionTypes;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class SectionBuilderWindows : WidgetCollectionBuilderWindows<SectionManager>
    {
        public SectionBuilderWindows(SectionManager elementManagerCollection) : base(elementManagerCollection)
        {
        }

        protected override string GetTitleText()
        {
            return string.Format("Section: {0}", _elementManagerCollection.Text);
        }
    }
}
