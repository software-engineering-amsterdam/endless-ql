namespace Assignment1.Model.QL.AST.Expression
{
    public class Not : ASTNode, IExpression
    {
        public IExpression Expression { get; }

        public Not(IExpression expression)
        {
            Expression = expression;
        }

        public Not(int lineNumber, IExpression expression)
        {
            _lineNumber = lineNumber;
            Expression = expression;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
