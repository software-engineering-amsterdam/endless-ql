namespace QLVisualizer.Widgets.Collection
{
    public interface IWidgetCollectionBuilder : IWidgetBuilder
    {
        void AddChild(IWidgetBuilder builder);
    }
}
