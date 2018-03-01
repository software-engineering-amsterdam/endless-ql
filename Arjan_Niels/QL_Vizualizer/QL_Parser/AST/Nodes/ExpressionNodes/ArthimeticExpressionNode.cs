using QL_Parser.Exceptions;

namespace QL_Parser.AST.Nodes.ExpressionNodes
{
    public enum ArthimeticOperator
    {
        MULT,
        DIV,
        PLUS,
        MINUS
    }

    public class ArthimeticExpressionNode : ExpressionNode
    {
        private const string MULT = "*";
        private const string DIV = "/";
        private const string PLUS = "+";
        private const string MINUS = "-";

        public ArthimeticOperator Operator { get; set; }

        public ArthimeticExpressionNode(IExpressionNode left, ArthimeticOperator opr, IExpressionNode right) : base(NodeType.ARTHIMETIC_EXPRESSION)
        {
            this.Left = left;
            this.Operator = opr;
            this.Right = right;
        }

        public static ArthimeticOperator ParseArthimeticOperator(string opr)
        {
            switch (opr)
            {
                case MULT:
                    return ArthimeticOperator.MULT;
                case DIV:
                    return ArthimeticOperator.DIV;
                case PLUS:
                    return ArthimeticOperator.PLUS;
                case MINUS:
                    return ArthimeticOperator.MINUS;
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