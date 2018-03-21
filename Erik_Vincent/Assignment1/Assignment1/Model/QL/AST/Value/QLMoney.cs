using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLMoney : IValue
    {
        public decimal Value { get; }

        public QLMoney(decimal value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
    }
}
