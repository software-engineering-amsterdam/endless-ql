using QLParser.AST.QL;
using QLParser.AST.QLS;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLParser
{
    public static class Util
    {
        public static string RemoveQuotes(string text)
        {
            return text.Substring(1, text.Length - 2);
        }

        public static QLSStyle CombineStyles(QLSStyle dominantStyle, QLSStyle style)
        {
            QLSStyle result = new QLSStyle(dominantStyle.QValueType, dominantStyle.WidgetSpecification);
            foreach (var styleValue in dominantStyle.StylingValues)
                result.AddStyleValue(styleValue);

            foreach (var styleValue in style.StylingValues)
                if (!result.GetStylingValues().Select(x => x.StyleProperty).Contains(styleValue.StyleProperty))
                    result.AddStyleValue(styleValue);

            return result;
        }

        public static QValueType GetQValueTypeFromString(string value)
        {
            Dictionary<string, QValueType> qValueTable = new Dictionary<string, QValueType>();
            foreach (QValueType x in Enum.GetValues(typeof(QValueType)))
                qValueTable.Add(x.ToString().ToLower(), x);

            return qValueTable[value.ToLower()];
        }
    }
}
