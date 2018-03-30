using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public abstract class WidgetCollectionBuilderWindows<Y> : WidgetCollectionBuilder<Control> where Y : ElementManagerCollection
    {
        public WidgetCollectionBuilderWindows(Y elementManagerCollection) : base(elementManagerCollection)
        {
            WindowsStyler styler = new WindowsStyler();
            _styleParser = styler;
            _styler = styler;
        }

        protected override Control Create(Dictionary<IWidgetBuilder, Control> children)
        {
            FlowLayoutPanel holder = new FlowLayoutPanel() { FlowDirection = FlowDirection.TopDown, Size = new System.Drawing.Size(300,300)};

            string title = GetTitleText();
            if (!string.IsNullOrEmpty(title))
                holder.Controls.Add(CreateTitle(title));

            foreach(KeyValuePair<IWidgetBuilder, Control> child in children)
                holder.Controls.Add(child.Value);

            _elementManagerCollection.OnActiveChange += (string identifier, bool isActive) =>  holder.Visible = isActive;
            holder.Visible = _elementManager.Active;
            return holder;
        }

        private Control CreateTitle(string text)
        {
            return _styler.StyleElement(new Label { Text = text });
        }

        protected abstract string GetTitleText();

        protected StyleParserWindows GetStyleParser()
        {
            return _styleParser as StyleParserWindows;
        }
    }
}
