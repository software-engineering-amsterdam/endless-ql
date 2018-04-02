using System.Windows.Media;
using QlsTransformer.Domain.Ast.Nodes;
using static System.Drawing.ColorTranslator;
using WpfColor = System.Windows.Media.Color;
using DrawColor = System.Drawing.Color;

namespace QlsTransformer.Orchestration.Models
{
    public class StyleModel
    {
        public StyleModel(
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
            var drawColor = FromHtml(color);
            var wpfColor = WpfColor.FromArgb(drawColor.A, drawColor.R, drawColor.G, drawColor.B);
            Color = new SolidColorBrush(wpfColor);
        }

        public IWidget Widget { get; }
        public int Width { get; }
        public string Font { get; }
        public decimal FontSize { get; }
        public SolidColorBrush Color { get; }
    }
}