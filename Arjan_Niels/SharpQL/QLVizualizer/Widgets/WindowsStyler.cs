using QLParser.AST.QLS;
using QLVisualizer.Widgets.Windows;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
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

            if (_styleParser.Width != StyleParserWindows.Dynamic)
                element.Width = _styleParser.Width;
            if (_styleParser.Height != StyleParserWindows.Dynamic)
                element.Height = _styleParser.Height;

            element.Margin = new Padding(0, _styleParser.MarginTop, 0, _styleParser.MarginBottom);
            return element;
        }
    }
}
