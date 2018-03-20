namespace Assignment1.Model.QL.AST.Expression
{
    public class GreaterThan : Binary
    {
        public GreaterThan(IExpression left, IExpression right) : base(left, right) { }

        public override void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}