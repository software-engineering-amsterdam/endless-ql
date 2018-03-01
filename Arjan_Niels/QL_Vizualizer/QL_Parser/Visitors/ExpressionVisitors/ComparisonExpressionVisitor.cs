using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QLanguage;

namespace QL_Parser.Visitors.ExpressionVisitors
{
    public class ComparisonExpressionVisitor : QLanguage.QLanguageBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitComparisonExpression([NotNull] QLanguageParser.ComparisonExpressionContext context)
        {
            // If ( expression )
            var comparisonoContext = context.comparisonExpression();
            if (comparisonoContext != null)
                return VisitComparisonExpression(comparisonoContext);



            var comparisonNode = new ComparisonExpressionNode();


            return comparisonNode;
        }
    }
}
