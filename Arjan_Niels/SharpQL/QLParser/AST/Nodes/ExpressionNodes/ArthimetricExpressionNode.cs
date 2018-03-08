using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.AST.Nodes.ExpressionNodes
{
    public class ArthimetricExpressionNode : ExpressionNode
    {
        private const string MULT = "*";
        private const string DIV = "/";
        private const string PLUS = "+";
        private const string MINUS = "-";

        public ArthimetricOperator Operator { get; set; }

        public ArthimetricExpressionNode(IExpressionNode left, ArthimetricOperator opr, IExpressionNode right) : base(NodeType.ARTHIMETRIC_EXPRESSION)
        {
            this.Left = left;
            this.Operator = opr;
            this.Right = right;
        }

        public static ArthimetricOperator ParseArthimeticOperator(string opr)
        {
            switch (opr)
            {
                case MULT:
                    return ArthimetricOperator.MULT;
                case DIV:
                    return ArthimetricOperator.DIV;
                case PLUS:
                    return ArthimetricOperator.PLUS;
                case MINUS:
                    return ArthimetricOperator.MINUS;
                default:
                    throw new UnknownOperatorException(string.Format("We don't know wat to do with this operator: {0}", opr));
            }
        }

        public override string ToString()
        {
            return string.Format("({0} {1} {2})", Left, Operator, Right);
        }
    }
}