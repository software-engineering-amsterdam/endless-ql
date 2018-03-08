using QLParser.AST;

namespace QLParser.AST.Nodes
{
    public class StatementNode : Node, IExpressionNode
    {
        public IExpressionNode LeftSide { get; private set; }
        public string Operator { get; private set; }
        public IExpressionNode RightSide { get; private set; }

        public StatementNode(Location location, IExpressionNode lhs, string opr, IExpressionNode rhs) : base(location, NodeType.LOGICAL_EXPRESSION)
        {
            this.LeftSide = lhs;
            this.Operator = opr;
            this.RightSide = rhs;
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
                return QValueType.UNKNOWN;
        }
    }
}