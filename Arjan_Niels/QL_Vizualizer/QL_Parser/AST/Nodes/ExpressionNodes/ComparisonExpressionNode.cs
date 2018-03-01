namespace QL_Parser.AST.Nodes.ExpressionNodes
{
    public class ComparisonExpressionNode : Node, IExpressionNode
    {
        public ComparisonExpressionNode() : base(NodeType.COMPARISON_EXPRESSION)
        {
        }

        public NodeType GetNodeType()
        {
            throw new System.NotImplementedException();
        }

        public QValueType GetQValueType()
        {
            throw new System.NotImplementedException();
        }
    }
}
