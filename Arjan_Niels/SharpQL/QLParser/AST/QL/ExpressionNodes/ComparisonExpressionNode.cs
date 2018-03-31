using QLParser.AST.QL.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.AST.QL.ExpressionNodes
{
    public class ComparisonExpressionNode : ExpressionNode, IExpressionNode
    {
        private const string GT = ">";
        private const string GE = ">=";
        private const string LT = "<";
        private const string LE = "<=";
        private const string EQ = "==";

        public ComparisonOperator Operator { get; private set; }

        public ComparisonExpressionNode(Location location, IExpressionNode left, ComparisonOperator op, IExpressionNode right) : base(location, NodeType.ComparisonExpression)
        {
            this.Left = left;
            this.Operator = op;
            this.Right = right;
        }

        public static ComparisonOperator ParseComparisonOperator(string opr)
        {
            switch (opr)
            {
                case GT:
                    return ComparisonOperator.GreaterThan;
                case GE:
                    return ComparisonOperator.GreaterEqual;
                case LT:
                    return ComparisonOperator.LessThan;
                case LE:
                    return ComparisonOperator.LessEqual;
                case EQ:
                    return ComparisonOperator.Equal;
                default:
                    throw new UnknownOperatorException(string.Format("We don't know what to do with this operator: {0}", opr));
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
