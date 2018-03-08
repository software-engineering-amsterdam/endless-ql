using QLParser.AST;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.AST.Nodes.ExpressionNodes
{
    public class ComparisonExpressionNode : ExpressionNode, IExpressionNode
    {
        private const string GT = ">";
        private const string GE = ">=";
        private const string LT = "<";
        private const string LE = "<=";
        private const string EQ = "==";

        public ComparisonOperator Operator { get; private set; }

        public ComparisonExpressionNode(Location location, IExpressionNode left, ComparisonOperator opr, IExpressionNode right) : base(location, NodeType.COMPARISON_EXPRESSION)
        {
            this.Left = left;
            this.Operator = opr;
            this.Right = right;
        }

        public static ComparisonOperator ParseComparisonOperator(string opr)
        {
            switch (opr)
            {
                case GT:
                    return ComparisonOperator.GT;
                case GE:
                    return ComparisonOperator.GE;
                case LT:
                    return ComparisonOperator.LT;
                case LE:
                    return ComparisonOperator.LE;
                case EQ:
                    return ComparisonOperator.EQ;
                default:
                    throw new UnknownOperatorException(string.Format("We don't know what to do with this operator: {0}", opr));
            }
        }

        public override string ToString()
        {
            return string.Format("{0} ({1} {2} {3})", base.ToString(), Left, Operator, Right);
        }
    }
}
