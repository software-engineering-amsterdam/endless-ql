using QL_Parser.AST.Nodes.ExpressionNodes.Enums;
using QL_Parser.Exceptions;

namespace QL_Parser.AST.Nodes.ExpressionNodes
{
    public class ComparisonExpressionNode : ExpressionNode, IExpressionNode
    {
        private const string GT = ">";
        private const string GE = ">=";
        private const string LT = "<";
        private const string LE = "<=";
        private const string EQ = "==";

        public ComparisonOperator Operator { get; private set; }

        public ComparisonExpressionNode(IExpressionNode left, ComparisonOperator opr, IExpressionNode right) : base(NodeType.COMPARISON_EXPRESSION)
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
