namespace Assignment1.Model.QL.AST.Expression
{
    public class Not :IExpression
    {
        public IExpression Expression { get; }

        public Not(IExpression expression)
        {
            Expression = expression;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
