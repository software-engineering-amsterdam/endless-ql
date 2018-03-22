using QLParser.AST.QLS;
using QLVisualizer.Widgets.Windows;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets
{
    public class WindowsStyler : IStyler<Control>, IStyleParser
    {
        private StyleParserWindows _styleParser;

        public WindowsStyler()
        {
            _styleParser = new StyleParserWindows();
        }

        public void ParseStyle(List<QLSValue> qlsValues, out string[] errors)
        {
            _styleParser.ParseStyle(qlsValues, out errors);
        }

        public Control StyleElement(Control element)
        {
            element.ForeColor = _styleParser.TextColor;
            element.Font = new System.Drawing.Font(_styleParser.Font, _styleParser.FontSize);
            element.BackColor = _styleParser.BackgroundColor;
            element.AutoSize = _styleParser.AutoSize;
            element.Margin = new Padding(0, _styleParser.MarginTop, 0, _styleParser.MarginBottom);

            if (!_styleParser.AutoSize)
            {
                element.Width = _styleParser.Width;
                element.Height = _styleParser.Height;
            }

            return element;
        }
    }
}
