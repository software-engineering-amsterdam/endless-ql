using QLParser.AST.Nodes;
using System;
using System.Collections.Generic;

namespace QLVisualizer.Widgets.Windows
{
    public class StyleParserWindows : IStyleParser
    {
        private static Dictionary<string, Tuple<StyleProperties, QValueType>> _supportedStyleProperties
        {
            get
            {
                return new Dictionary<string, Tuple<StyleProperties, QValueType>>()
                {
                    {"Height", new Tuple<StyleProperties, QValueType>(StyleProperties.Height,                   QValueType.INTEGER) },
                    {"Width", new Tuple<StyleProperties, QValueType>(StyleProperties.Width,                     QValueType.INTEGER) },
                    {"Margin-Top", new Tuple<StyleProperties, QValueType>(StyleProperties.TopMargin,            QValueType.INTEGER) },
                    {"Margin-Bottom", new Tuple<StyleProperties, QValueType>(StyleProperties.BottomMargin,      QValueType.INTEGER) },
                    {"BackgroundColor", new Tuple<StyleProperties, QValueType>(StyleProperties.BackgroundColor, QValueType.HEX) },
                    {"Font", new Tuple<StyleProperties, QValueType>(StyleProperties.Font,                       QValueType.TEXT) },
                    {"FontSize", new Tuple<StyleProperties, QValueType>(StyleProperties.FontSize,               QValueType.INTEGER) },
                    {"TextColor", new Tuple<StyleProperties, QValueType>(StyleProperties.TextColor,             QValueType.HEX) }
                };
            }
        }

        public Dictionary<string, string> ParseStyle()
        {
            throw new NotImplementedException();
        }
    }
}
