using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class Undefined : IValue
    {
        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
