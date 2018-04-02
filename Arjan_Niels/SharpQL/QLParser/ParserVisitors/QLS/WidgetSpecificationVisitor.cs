using QLParser.AST.QLS;
using System.Collections.Generic;
using System.Linq;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.ParserVisitors.QLS
{
    public class WidgetSpecificationVisitor : QLSGrammar.QLSGrammarBaseVisitor<QLSWidgetSpecification>
    {
        public override QLSWidgetSpecification VisitWidgetspecification(WidgetspecificationContext context)
        {
            if (context == null)
                return new QLSWidgetSpecification();

            var type = QLSWidgetSpecification.ParseWidgetType(context.WIDGETTYPE().GetText());
            var arguments = VisitWidgetTypeArguments(context.widgettypearguments());

            return new QLSWidgetSpecification(type, arguments);
        }

        private IList<string> VisitWidgetTypeArguments(WidgettypeargumentsContext context)
        {
            if (context.children == null)
                return new List<string>();

            var rawArguments = context.children.Select(x => x.GetText());
            return GetUneven(rawArguments).Select(x => Util.RemoveQuotes(x)).ToList();
        }

        private IEnumerable<string> GetUneven(IEnumerable<string> values)
        {
            for (var i = 0; i < values.Count(); i++)
                if (i % 2 != 0)
                    yield return values.ElementAt(i);
        }
    }
}