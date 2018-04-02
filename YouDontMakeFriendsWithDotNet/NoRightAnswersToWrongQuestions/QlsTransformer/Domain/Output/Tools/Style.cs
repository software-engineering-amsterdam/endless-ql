using QlsTransformer.Domain.Ast.Nodes;

namespace QlsTransformer.Domain.Output.Tools
{
    public class Style
    {
        public Style(
            Style style,
            IWidget widget = null,
            int? width = null,
            string font = null,
            decimal? fontSize = null,
            string color = null)
        {
            Widget = widget ?? style.Widget;
            Width = width ?? style.Width;
            Font = font ?? style.Font;
            FontSize = fontSize ?? style.FontSize;
            Color = color ?? style.Color;
        }

        public Style(
            IWidget widget,
            int width,
            string font,
            decimal fontSize,
            string color)
        {
            Widget = widget;
            Width = width;
            Font = font;
            FontSize = fontSize;
            Color = color;
        }

        public IWidget Widget { get; }
        public int Width { get; }
        public string Font { get; }
        public decimal FontSize { get; }
        public string Color { get; }
    }
}