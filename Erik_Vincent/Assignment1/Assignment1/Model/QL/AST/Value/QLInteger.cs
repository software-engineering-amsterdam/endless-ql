using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLInteger : IValue
    {
        public static QLInteger Default => new QLInteger(0);

        public int Value { get; }
        private readonly bool _undefined = true;

        public QLInteger() { }

        public QLInteger(int value)
        {
            Value = value;
            _undefined = false;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => _undefined;
        public override string ToString() => Value.ToString();
    }
}
