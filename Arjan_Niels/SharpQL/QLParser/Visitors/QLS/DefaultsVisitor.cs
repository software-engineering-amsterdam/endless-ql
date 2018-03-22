using Antlr4.Runtime.Misc;
using QLParser.AST.QL;
using QLParser.AST.QLS;
using QLSGrammar;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.Visitors.QLS
{
    public class DefaultsVisitor : QLSGrammarBaseVisitor<QLSStyle>
    {
        public override QLSStyle VisitDefaults([NotNull] QLSGrammarParser.DefaultsContext context)
        {
            var specificationVisitor = new WidgetSpecificationVisitor();
            var specification = specificationVisitor.VisitWidgetspecification(context.widgetspecification());
            var style = new QLSStyle(specification);

            foreach (var ctx in context.stylevalue())
                style.AddStyleValue(GetStylevalue(ctx));

            return style;
        }

        private QLSValue GetStylevalue(StylevalueContext context)
        {
            var property = context.ID().GetText();
            var stringValue = "";

            var type = QValueType.UNKNOWN;
            if (context.TEXT() != null)
            {
                type = QValueType.TEXT;
                stringValue = context.TEXT().GetText();
            }
            else if (context.DOUBLE() != null)
            {
                type = QValueType.DOUBLE;
                stringValue = context.DOUBLE().GetText();
            }
            else if (context.HEX() != null)
            {
                type = QValueType.HEX;
                stringValue = context.HEX().GetText();
            }
            else if (context.INT() != null)
            {
                type = QValueType.INTEGER;
                stringValue = context.INT().GetText();
            }

            return new QLSValue(property, stringValue, type);
        }
    }
}