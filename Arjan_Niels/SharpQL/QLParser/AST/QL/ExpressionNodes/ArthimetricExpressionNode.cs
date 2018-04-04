using QLParser.AST.QL.ExpressionNodes.Enums;
using QLParser.Exceptions;

namespace QLParser.AST.QL.ExpressionNodes
{
    public class ArthimetricExpressionNode : ExpressionNode
    {
        private const string MULT = "*";
        private const string DIV = "/";
        private const string PLUS = "+";
        private const string MINUS = "-";

        public ArthimetricOperator Operator { get; private set; }

        public ArthimetricExpressionNode(Location location, IExpressionNode left, ArthimetricOperator op, IExpressionNode right)
            : base(location, NodeType.ArthimetricExpression)
        {
            this.Left = left;
            this.Operator = op;
            this.Right = right;
        }

        public static ArthimetricOperator ParseArthimeticOperator(string opr)
        {
            switch (opr)
            {
                case MULT:
                    return ArthimetricOperator.Mult;
                case DIV:
                    return ArthimetricOperator.Div;
                case PLUS:
                    return ArthimetricOperator.Plus;
                case MINUS:
                    return ArthimetricOperator.Minus;
                default:
                    throw new UnknownOperatorException(string.Format("We don't know wat to do with this operator: {0}", opr));
            }
        }

        public override string ToString()
        {
            return string.Format("({0} {1} {2})", Left, Operator, Right);
        }

        public override void Accept(IQLVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}