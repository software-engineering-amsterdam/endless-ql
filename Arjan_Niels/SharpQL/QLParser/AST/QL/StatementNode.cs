namespace QLParser.AST.QL
{
    public class StatementNode : QLNode, IExpressionNode
    {
        public IExpressionNode LeftSide { get; private set; }
        public string Operator { get; private set; }
        public IExpressionNode RightSide { get; private set; }

        public StatementNode(Location location, IExpressionNode left, string op, IExpressionNode right) : base(location, NodeType.LogicalExpression)
        {
            this.LeftSide = left;
            this.Operator = op;
            this.RightSide = right;
        }

        public override string ToString()
        {
            return string.Format("{0} ({1} {2} {3})", base.ToString(), LeftSide, Operator, RightSide);
        }

        public QValueType GetQValueType()
        {
            var leftSideType = LeftSide.GetQValueType();
            var rightSideType = RightSide.GetQValueType();

            if (leftSideType == rightSideType)
                return leftSideType;
            else
                return QValueType.Unknown;
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit((dynamic)this);
        }
    }
}