using QlsTransformer.Ast.Nodes;

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
            Color = color;
        }
        public IWidget Widget { get; }
        public int? Width { get; }
        public string Font { get; }
        public decimal? FontSize { get; }
        public string Color { get; }
    }
}