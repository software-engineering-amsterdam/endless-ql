﻿namespace QL_Parser.AST.Nodes
{
    public class StatementNode : Node, IExpressionNode
    {
        public IExpressionNode LeftSide { get; private set; }
        public string Operator { get; private set; }
        public IExpressionNode RightSide { get; private set; }

        public StatementNode(IExpressionNode lhs, string opr, IExpressionNode rhs) : base(NodeType.STATEMENT)
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

        public NodeType GetNodeType()
        {
            return Type;
        }
    }
}