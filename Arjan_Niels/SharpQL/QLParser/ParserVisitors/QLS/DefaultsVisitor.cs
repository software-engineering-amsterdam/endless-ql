using Antlr4.Runtime.Misc;
using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLSGrammar;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.ParserVisitors.QLS
{
    public class DefaultsVisitor : QLSGrammarBaseVisitor<QLSStyle>
    {
        public override QLSStyle VisitDefaults([NotNull] QLSGrammarParser.DefaultsContext context)
        {
            var specificationVisitor = new WidgetSpecificationVisitor();
            var specification = specificationVisitor.VisitWidgetspecification(context.widgetspecification());
            var qtype = Util.GetQValueTypeFromString(context.QTYPE().GetText());
            var style = new QLSStyle(qtype, specification);

            foreach (var ctx in context.stylevalue())
                style.AddStyleValue(GetStylevalue(ctx));

            return style;
        }

        private QLSValue GetStylevalue(StylevalueContext context)
        {
            var property = context.ID().GetText();
            var stringValue = "";

            var type = QValueType.Unknown;
            if (context.TEXT() != null)
            {
                type = QValueType.Text;
                stringValue = context.TEXT().GetText();
            }
            else if (context.DOUBLE() != null)
            {
                type = QValueType.Double;
                stringValue = context.DOUBLE().GetText();
            }
            else if (context.HEX() != null)
            {
                type = QValueType.Hex;
                stringValue = context.HEX().GetText();
            }
            else if (context.INT() != null)
            {
                type = QValueType.Integer;
                stringValue = context.INT().GetText();
            }

            return new QLSValue(property, stringValue, type);
        }
    }
}