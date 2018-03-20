namespace Assignment1.Model.QL.AST.Expression
{
    public class Multiply : Binary
    {
        public Multiply(IExpression left, IExpression right) : base(left, right) { }

        public override void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}