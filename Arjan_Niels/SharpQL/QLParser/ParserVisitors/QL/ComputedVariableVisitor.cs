using QLParser.AST;
using QLParser.AST.QL;
using QLParser.ParserVisitors.QL.ExpressionVisitors;
using System;
using static QLGrammar.QLGrammarParser;

namespace QLParser.ParserVisitors.QL
{
    public class ComputedVariableVisitor : QLGrammar.QLGrammarBaseVisitor<ComputedNode>
    {
        public override ComputedNode VisitComputedVariable(ComputedVariableContext context)
        {
            if (context == null)
                throw new ArgumentNullException("Context can't be null");

            var id = context.ID().GetText();
            var text = Util.RemoveQuotes(context.TEXT().GetText());
            var qtype = Util.GetQValueTypeFromString(context.QTYPE().GetText());

            IExpressionNode expression = null;

            if (context.textConcatination() != null)
                expression = new TextConcatinationVisitor().VisitTextConcatination(context.textConcatination());

            else if (context.artithmeticExpression() != null)
                expression = new ArthimetricExpressionVisitor().VisitArtithmeticExpression(context.artithmeticExpression());

            else if (context.comparisonExpression() != null)
                expression = new ComparisonExpressionVisitor().VisitComparisonExpression(context.comparisonExpression());

            var computedNode = new ComputedNode(Location.FromContext(context), id, text, qtype, expression);
            return computedNode;
        }
    }
}
