using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;

namespace QLParser.Analysis.Semantic
{
    public class BooleanStatementnalyser : IAnalyser
    {
        public bool Analyse(Node node)
        {
            var result = true;
            var expression = GetExpression(node);
            if (expression != null && !AnalyseExpression(expression))
            {
                Analyser.AddMessage("This expression isn't valid.", MessageType.ERROR);
                return false;
            }

            foreach (Node child in node.Children)
                if (!Analyse(child) && result)
                    result = false;

            return result;
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.LOGICAL_EXPRESSION:
                    var statementNode = (LogicalExpressionNode)node;
                    var leftSideIsBoolean = AnalyseExpression(statementNode.Left);
                    var rightSideIsBoolean = AnalyseExpression(statementNode.Right);
                    return leftSideIsBoolean && rightSideIsBoolean;
                case NodeType.COMPARISON_EXPRESSION:
                    return true;
                default:
                    return node.GetQValueType() == QValueType.BOOLEAN;
            }
        }

        private IExpressionNode GetExpression(Node node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.CONDITIONAL:
                    var conditional = (ConditionalNode)node;
                    return conditional.Expression;
                case NodeType.COMPUTED:
                    var computed = (ComputedNode)node;
                    return computed.Expression;
                default:
                    return null;
            }
        }
    }
}