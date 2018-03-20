namespace Assignment1.Model.QL.AST.Expression
{
    public class GreaterThanOrEqual : Binary
    {
        public GreaterThanOrEqual(int lineNumber, IExpression left, IExpression right) : base(left, right)
        {
            _lineNumber = lineNumber;
        }

        public override void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}