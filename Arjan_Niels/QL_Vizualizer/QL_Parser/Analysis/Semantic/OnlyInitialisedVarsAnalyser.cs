using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Semantic
{
    public class OnlyInitialisedVarsAnalyser : IAnalyser
    {
        public bool Analyse(Node node, bool logErrors = true)
        {
            var result = true;
            if (node.Type == NodeType.CONDITIONAL)
            {
                var conditionalNode = (ConditionalNode)node;
                return AnalyseExpression(conditionalNode.Expression);
            }

            // Set result to false when any of the children encounters a error.
            foreach (Node n in node.Children)
                if (!Analyse(n, logErrors) && result)
                    result = false;


            return result;
        }

        private bool IsIdentiierInSymbolTable(ValueNode node)
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
            if (node.GetNodeType() == NodeType.STATEMENT)
            {
                var statementNode = (StatementNode)node;
                var leftResult = AnalyseExpression(statementNode.LeftSide);
                var rightResult = AnalyseExpression(statementNode.RightSide);
                return leftResult == rightResult;
            }
            else
            {
                var valueNode = (ValueNode)node;
                return IsIdentiierInSymbolTable(valueNode);
            }
        }
    }
}
