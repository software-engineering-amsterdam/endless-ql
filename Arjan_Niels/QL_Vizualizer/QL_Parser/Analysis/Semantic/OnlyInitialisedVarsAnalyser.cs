using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QL_Parser.Exceptions;

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
            else if (node.Type == NodeType.COMPUTED)
            {
                var computedNode = (ComputedNode)node;
                return AnalyseExpression(computedNode.Expression);
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
                Analyser.AddMessage(string.Format("Unknown identifier '{0}' in statement", node.ID), MessageType.ERROR);
                return false;
            }
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            switch (node.GetNodeType())
            {
                case NodeType.LOGICAL_EXPRESSION:
                case NodeType.COMPARISON_EXPRESSION:
                case NodeType.ARTHIMETIC_EXPRESSION:
                    var statementNode = (ExpressionNode)node;
                    var leftResult = AnalyseExpression(statementNode.Left);
                    var rightResult = AnalyseExpression(statementNode.Right);
                    return leftResult == rightResult;
                case NodeType.LITERAL:
                    return true;
                case NodeType.IDENTIFIER:
                    var valueNode = (IdentifierNode)node;
                    return IsIdentiierInSymbolTable(valueNode);

                default:
                    throw new UnknownNodeTypeException(string.Format("We don't know what to do with a {0} node.", node.GetNodeType()));
            }
        }
    }
}