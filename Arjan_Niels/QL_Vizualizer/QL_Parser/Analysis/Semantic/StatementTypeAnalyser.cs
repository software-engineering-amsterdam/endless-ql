using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;

namespace QL_Parser.Analysis.Semantic
{
    public class BooleanStatementnalyser : IAnalyser
    {
        public bool Analyse(Node node)
        {
            var result = true;
            if (node.Type == NodeType.CONDITIONAL)
            {
                var conditionalNode = (ConditionalNode)node;
                if (!AnalyseExpression(conditionalNode.Expression))
                {
                    Analyser.AddMessage("This is not a boolean expression", MessageType.ERROR);
                    return false;
                }
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
    }
}