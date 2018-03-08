namespace QL_Parser.AST.Nodes.ExpressionNodes
{
    public abstract class ExpressionNode : Node, IExpressionNode
    {
        public IExpressionNode Left { get; protected set; }
        public IExpressionNode Right { get; protected set; }

        public ExpressionNode(NodeType type) : base(type)
        {
        }

        public QValueType GetQValueType()
        {
            var leftSideType = Left.GetQValueType();
            var rightSideType = Right.GetQValueType();

            if (leftSideType == rightSideType)
                return leftSideType;
            else
                return QValueType.UNKNOWN;
        }
    }
}