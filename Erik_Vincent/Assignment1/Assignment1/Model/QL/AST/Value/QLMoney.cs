using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLMoney : IValue
    {
        public decimal Value { get; }
        private readonly bool _undefined = true;

        public QLMoney() { }

        public QLMoney(decimal value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => _undefined;
    }
}
