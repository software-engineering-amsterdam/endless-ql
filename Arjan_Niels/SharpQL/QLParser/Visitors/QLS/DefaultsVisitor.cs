using Antlr4.Runtime.Misc;
using QLParser.AST.Nodes;
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
            var value = context.ID().GetText();
            var type = QValueType.UNKNOWN;
            if (context.TEXT() != null)
                type = QValueType.TEXT;
            else if (context.DOUBLE() != null)
                type = QValueType.DOUBLE;
            else if (context.HEX() != null)
                type = QValueType.HEX;
            else if (context.INT() != null)
                type = QValueType.INTEGER;

            return new QLSValue(value, type);
        }
    }
}