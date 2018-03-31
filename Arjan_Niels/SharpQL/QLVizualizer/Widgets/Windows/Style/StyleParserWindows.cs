using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLVisualizer.Factories;
using System;
using System.Collections.Generic;
using System.Drawing;

namespace QLVisualizer.Widgets.Windows.Style
{
    public class StyleParserWindows : IStyleParser
    {
        private static Dictionary<string, Tuple<StyleProperty, QValueType>> _supportedStyleProperties
        {
            get
            {
                return new Dictionary<string, Tuple<StyleProperty, QValueType>>()
                {
                    {"height", new Tuple<StyleProperty, QValueType>(StyleProperty.Height,                   QValueType.INTEGER) },
                    {"width", new Tuple<StyleProperty, QValueType>(StyleProperty.Width,                     QValueType.INTEGER) },
                    {"margin-top", new Tuple<StyleProperty, QValueType>(StyleProperty.MarginTop,            QValueType.INTEGER) },
                    {"margin-bottom", new Tuple<StyleProperty, QValueType>(StyleProperty.MarginBottom,      QValueType.INTEGER) },
                    {"background-color", new Tuple<StyleProperty, QValueType>(StyleProperty.BackgroundColor, QValueType.HEX) },
                    {"font", new Tuple<StyleProperty, QValueType>(StyleProperty.Font,                       QValueType.TEXT) },
                    {"font-size", new Tuple<StyleProperty, QValueType>(StyleProperty.FontSize,               QValueType.INTEGER) },
                    {"text-color", new Tuple<StyleProperty, QValueType>(StyleProperty.TextColor,             QValueType.HEX) },
                    {"auto-size", new Tuple<StyleProperty, QValueType>(StyleProperty.AutoSize,               QValueType.BOOLEAN) }
                };
            }
        }

        public int Height { get; private set; }
        public int Width { get; private set; }
        public int MarginTop { get; private set; }
        public int MarginBottom { get; private set; }
        public Color BackgroundColor { get; private set; }
        public string Font { get; private set; }
        public int FontSize { get; private set; }
        public Color TextColor { get; private set; }
        public bool AutoSize { get; private set; }

        public void ParseStyle(QLSStyle qlsStyle, out string[] errors)
        {
            List<string> collectedErrors = new List<string>();
            ColorConverter colorConverter = new ColorConverter();
            foreach(QLSValue qlsValue in qlsStyle.GetStylingValues())
            {
                if (_supportedStyleProperties.ContainsKey(qlsValue.StyleProperty) && qlsValue.QValueType == _supportedStyleProperties[qlsValue.StyleProperty].Item2)
                {
                    try
                    {
                        StyleProperty styleProperty = _supportedStyleProperties[qlsValue.StyleProperty].Item1;
                        switch (styleProperty)
                        {
                            case StyleProperty.Height:
                                Height = QValueTypeParser.ParseInteger(qlsValue.StyleValue);
                                break;
                            case StyleProperty.Width:
                                Width = QValueTypeParser.ParseInteger(qlsValue.StyleValue);
                                break;
                            case StyleProperty.MarginTop:
                                MarginTop = QValueTypeParser.ParseInteger(qlsValue.StyleValue);
                                break;
                            case StyleProperty.MarginBottom:
                                MarginBottom = QValueTypeParser.ParseInteger(qlsValue.StyleValue);
                                break;
                            case StyleProperty.BackgroundColor:
                                BackgroundColor = (Color)colorConverter.ConvertFromString(QValueTypeParser.ParseHexadecimal(qlsValue.StyleValue));
                                break;
                            case StyleProperty.Font:
                                Font = QValueTypeParser.ParseText(qlsValue.StyleValue);
                                break;
                            case StyleProperty.FontSize:
                                FontSize = QValueTypeParser.ParseInteger(qlsValue.StyleValue);
                                break;
                            case StyleProperty.TextColor:
                                TextColor = (Color)colorConverter.ConvertFromString(QValueTypeParser.ParseHexadecimal(qlsValue.StyleValue));
                                break;
                            case StyleProperty.AutoSize:
                                AutoSize = QValueTypeParser.ParseBoolean(qlsValue.StyleValue);
                                break;
                        }
                    }
                    catch (InvalidOperationException invalidOperation)
                    {
                        // Exception created by the parser, if parsing fails, the default value is still used
                        // Send the exception message back with the results
                        collectedErrors.Add(invalidOperation.Message);
                    }
                }
                else
                {
                    collectedErrors.Add(UserMessages.UnsupportedStyle(qlsValue.StyleValue, qlsValue.StyleProperty.ToString(), "Windows"));
                }
            }
            errors = collectedErrors.ToArray();
        }

        public StyleParserWindows()
        {
            // Default values
            Height = 150;
            Width = 200;

            AutoSize = true;

            MarginTop = 10;
            MarginBottom = 0;

            BackgroundColor = SystemColors.Control;

            Font = "Arial";
            FontSize = 12;
            TextColor = Color.Black;
        }
    }
}
