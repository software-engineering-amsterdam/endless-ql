using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using QLVisualizer.Widgets.Collection;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public abstract class WidgetCollectionBuilderWindows<T> : WidgetCollectionBuilder<Control, T> where T : ElementManagerCollection
    {
        public WidgetCollectionBuilderWindows(List<QLSValue> qlsElements, T elementManagerCollection, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, elementManagerCollection, parent)
        {
            WindowsStyler styler = new WindowsStyler();
            _styleParser = styler;
            _styler = styler;
        }

        protected override Control Create(Dictionary<WidgetBuilder<Control>, Control> children)
        {
            FlowLayoutPanel holder = new FlowLayoutPanel() { FlowDirection = FlowDirection.TopDown };

            string title = GetTitleText();
            if (string.IsNullOrEmpty(title))
                holder.Controls.Add(CreateTitle(title));

            foreach(KeyValuePair<WidgetBuilder<Control>, Control> child in children)
                holder.Controls.Add(child.Value);

            _elementManagerCollection.OnActiveChange += (string identifier, bool isActive) =>  holder.Visible = isActive;

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
