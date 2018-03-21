using Antlr4.Runtime.Misc;
using QLParser.AST;
using QLParser.AST.Nodes;
using QLParser.Visitors.ExpressionVisitors;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.Visitors
{
    public class ComputedVariableVisitor : QLGrammar.QLGrammarBaseVisitor<ComputedNode>
    {
        public override ComputedNode VisitComputedVariable([NotNull] ComputedVariableContext context)
        {
            var id = context.ID().GetText();
            var textRaw = context.TEXT().GetText();
            var text = textRaw.Substring(1, textRaw.Length - 2);
            var qtype = (QValueType)Enum.Parse(typeof(QValueType), context.QTYPE().GetText().ToUpper());

            IExpressionNode expression = null;
            if (context.artithmeticExpression() != null)
                expression = new ArthimetricExpressionVisitor().VisitArtithmeticExpression(context.artithmeticExpression());

            else if (context.comparisonExpression() != null)
                expression = new ComparisonExpressionVisitor().VisitComparisonExpression(context.comparisonExpression());

            var computedNode = new ComputedNode(Location.FromContext(context), id, text, qtype, expression);
            return computedNode;
        }
    }
}
