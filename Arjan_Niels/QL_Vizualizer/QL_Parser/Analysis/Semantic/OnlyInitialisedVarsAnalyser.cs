using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;

namespace QL_Parser.Analysis.Semantic
{
    public class OnlyInitialisedVarsAnalyser : IAnalyser
    {
        public bool Analyse(Node node)
        {
            var result = true;
            if (node.Type == NodeType.CONDITIONAL)
            {
                var conditionalNode = (ConditionalNode)node;
                return AnalyseExpression(conditionalNode.Expression);
            }

            // Set result to false when any of the children encounters a error.
            foreach (Node child in node.Children)
                if (!Analyse(child) && result)
                    result = false;

            return result;
        }

        private bool IsIdentiierInSymbolTable(IdentifierNode node)
        {
            var valueType = SymbolTable.Get(node.ID);
            if (valueType != QValueType.UNKNOWN)
            {
                return true;
            }
            else
            {
                Analyser.AddMessage(string.Format("Unknown identifier {0} in statement", node.ID), MessageType.ERROR);
                return false;
            }
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            if (node.GetNodeType() == NodeType.LOGICAL_EXPRESSION)
            {
                var statementNode = (LogicalExpressionNode)node;
                var leftResult = AnalyseExpression(statementNode.Left);
                var rightResult = AnalyseExpression(statementNode.Right);
                return leftResult == rightResult;
            }
            else if (node.GetNodeType() == NodeType.LITERAL)
            {
                // Literals are valid in an expression.
                return true;
            }

            else
            {
                var valueNode = (IdentifierNode)node;
                return IsIdentiierInSymbolTable(valueNode);
            }
        }
    }
}