namespace Assignment1.Model.QL.AST.Expression
{
    public class Divide : Binary
    {
        public Divide(IExpression left, IExpression right) : base(left, right) { }

        public override void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}