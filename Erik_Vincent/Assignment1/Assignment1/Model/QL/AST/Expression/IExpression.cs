namespace Assignment1.Model.QL.AST.Expression
{
    public interface IExpression
    {
        void Accept(IExpressionVisitor visitor);
    }
}
