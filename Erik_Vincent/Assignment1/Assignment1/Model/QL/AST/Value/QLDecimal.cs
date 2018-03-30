using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLDecimal : IValue
    {
        public static QLDecimal Default => new QLDecimal(0);

        public decimal Value { get; }
        private readonly bool _undefined = true;

        public QLDecimal() { }

        public QLDecimal(decimal value)
        {
            Value = value;
            _undefined = false;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => _undefined;
    }
}
