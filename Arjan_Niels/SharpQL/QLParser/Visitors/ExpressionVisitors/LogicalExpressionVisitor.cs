using Antlr4.Runtime.Misc;
using QLGrammar;
using QLParser.AST;
using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.Exceptions;
using static QLGrammar.QLGrammarParser;

namespace QLParser.Visitors.ExpressionVisitors
{
    public class LogicalExpressionVisitor : QLGrammarBaseVisitor<IExpressionNode>
    {
        public override IExpressionNode VisitLogicalExpression([NotNull] LogicalExpressionContext context)
        {
            var logicalEntityContext = context.logicalEntity();
            if (logicalEntityContext != null)
                return VisitLogicalEntity(logicalEntityContext);


            var logicalExpressionContext = context.logicalExpression();
            if (logicalExpressionContext != null && logicalExpressionContext.Length == 1)
                return VisitLogicalExpression(logicalExpressionContext[0]);

            var comparisonExpressionContext = context.comparisonExpression();
            if (comparisonExpressionContext != null)
                return new ComparisonExpressionVisitor().VisitComparisonExpression(comparisonExpressionContext);

            var left = VisitLogicalExpression(context.LEFT);
            var opr = LogicalExpressionNode.ParseLogicalOperator(context.OPR.Text);
            var right = VisitLogicalExpression(context.RIGHT);

            return new LogicalExpressionNode(Location.FromContext(context), left, opr, right); ;
        }

        public override IExpressionNode VisitLogicalEntity([NotNull] LogicalEntityContext context)
        {
            if (context.ID() != null)
                return new IdentifierNode(Location.FromContext(context), context.ID().GetText());

            if (context.TRUE() != null)
                return new LiteralNode(Location.FromContext(context), context.TRUE().GetText(), QValueType.BOOLEAN);

            if (context.FALSE() != null)
                return new LiteralNode(Location.FromContext(context), context.FALSE().GetText(), QValueType.BOOLEAN);

            // Throw an exception if we reach this line, because it should not be possible.
            throw new UnknownLogicalEntity("We don't know what to do with this entity");
        }
    }
}
