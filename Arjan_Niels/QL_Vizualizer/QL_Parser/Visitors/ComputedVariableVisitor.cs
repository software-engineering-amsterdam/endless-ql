using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.Visitors.ExpressionVisitors;
using QLanguage;
using System;

namespace QL_Parser.Visitors
{
    public class ComputedVariableVisitor : QLanguage.QLanguageBaseVisitor<ComputedNode>
    {
        public override ComputedNode VisitComputedVariable([NotNull] QLanguageParser.ComputedVariableContext context)
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

            var computedNode = new ComputedNode(id, text, qtype, expression);
            return computedNode;
        }
    }
}
