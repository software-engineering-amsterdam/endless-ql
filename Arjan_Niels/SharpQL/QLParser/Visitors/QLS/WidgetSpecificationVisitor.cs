using Antlr4.Runtime.Misc;
using QLParser.AST.QLS;
using System.Collections.Generic;
using static QLSGrammar.QLSGrammarParser;

namespace QLParser.Visitors.QLS
{
    public class WidgetSpecificationVisitor : QLSGrammar.QLSGrammarBaseVisitor<QLSWidgetSpecification>
    {
        public override QLSWidgetSpecification VisitWidgetspecification([NotNull] WidgetspecificationContext context)
        {
            var type = QLSWidgetSpecification.ParseWidgetType(context.WIDGETTYPE().GetText());
            var arguments = VisitWidgetTypeArguments(context.widgettypearguments());

            return new QLSWidgetSpecification(type, arguments);
        }

        private IList<string> VisitWidgetTypeArguments(WidgettypeargumentsContext context)
        {
            var arguments = new List<string>();
            for (var i = 0; i < context.children.Count; i++)
                if (i % 2 != 0)
                    arguments.Add(Util.RemoveQuotes(context.children[i].GetText()));

            return arguments;
        }
    }
}