using QLParser.AST.QL.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.AST.QL.ExpressionNodes
{
    public class LogicalExpressionNode : ExpressionNode
    {
        private const string AND = "&&";
        private const string OR = "||";

        public LogicalOperator Operator { get; private set; }

        public LogicalExpressionNode(Location location, IExpressionNode left, LogicalOperator op, IExpressionNode right) : base(location, NodeType.LogicalExpression)
        {
            this.Left = left;
            this.Operator = op;
            this.Right = right;
        }

        public static LogicalOperator ParseLogicalOperator(string opr)
        {
            switch (opr)
            {
                case AND:
                    return LogicalOperator.And;
                case OR:
                    return LogicalOperator.Or;
                default:
                    throw new UnknownOperatorException(string.Format("QL doesn't know what to do with {0}", opr));
            }
        }

        public override string ToString()
        {
            return string.Format("{0} ({1} {2} {3})", base.ToString(), Left, Operator, Right);
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
