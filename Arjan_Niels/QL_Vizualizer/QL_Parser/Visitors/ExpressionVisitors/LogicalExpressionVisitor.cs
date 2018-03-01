using Antlr4.Runtime.Misc;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QLanguage;

namespace QL_Parser.Visitors.ExpressionVisitors
{
    public class LogicalExpressionVisitor : QLanguage.QLanguageBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitLogicalExpression([NotNull] QLanguageParser.LogicalExpressionContext context)
        {
            // Leaf node
            var logicalEntityContext = context.logicalEntity();
            if (logicalEntityContext != null)
                return VisitLogicalEntity(logicalEntityContext);


            var logicalExpressionContext = context.logicalExpression();
            if (logicalExpressionContext != null && logicalExpressionContext.Length == 1)
                return VisitLogicalExpression(logicalExpressionContext[0]);

            var comparisonExpressionContext = context.comparisonExpression();
            if (comparisonExpressionContext != null)
                return null;


            var left = VisitLogicalExpression(context.LEFT);
            var opr = LogicalExpressionNode.ParseLogicalOperator(context.OPR.Text);
            var right = VisitLogicalExpression(context.RIGHT);


            return new LogicalExpressionNode(left, opr, right); ;
        }

        public override IExpressionNode VisitLogicalEntity([NotNull] QLanguageParser.LogicalEntityContext context)
        {
            if (context.ID() != null)
                return new ValueNode(context.ID().GetText());

            return new ValueNode("blah");
        }
    }
}
