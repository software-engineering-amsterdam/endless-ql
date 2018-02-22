using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Semantic
{
    public class BooleanStatementnalyser : IAnalyser
    {
        public bool Analyse(Node node, bool logErrors = true)
        {
            var result = true;
            if (node.Type == NodeType.CONDITIONAL)
            {
                var conditionalNode = (ConditionalNode)node;
                if (!AnalyseExpression(conditionalNode.Expression))
                {
                    Analyser.AddMessage("Invalid type on boolean operator", MessageType.ERROR);
                    return false;
                }
            }

            foreach (Node n in node.Children)
                if (!Analyse(n) && result)
                    result = false;

            return result;
        }

        private bool IsBooleanOperator(string opr)
        {
            return opr == "&&" || opr == "||";
        }

        private bool AnalyseExpression(IExpressionNode node)
        {
            if (node.GetNodeType() == NodeType.STATEMENT)
            {
                var statementNode = (StatementNode)node;
                var leftSideIsBoolean = AnalyseExpression(statementNode.LeftSide);
                var rightSideIsBoolean = AnalyseExpression(statementNode.RightSide);
                var operatorIsBoolean = IsBooleanOperator(statementNode.Operator);

                return leftSideIsBoolean && operatorIsBoolean && rightSideIsBoolean;
            }
            else
            {
                return node.GetQValueType() == QValueType.BOOLEAN;
            }
        }
    }
}
