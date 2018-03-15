using System.Drawing;

namespace Assignment1.Model.QLS
{
    public class Style
    {
        public string Label = "";
        public int Width = 0;
        public Font Font = SystemFonts.DefaultFont;
        public float FontSize = SystemFonts.DefaultFont.SizeInPoints;
        public Color Color = Color.Black;
        public Widget Widget;
    }
}
