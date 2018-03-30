using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLString : IValue
    {
        public static QLString Default => new QLString("");

        public string Value { get; }
        private readonly bool _undefined = true;

        public QLString() { }

        public QLString(string value)
        {
            Value = value;
            _undefined = false;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => _undefined;
        public override string ToString() => Value;
    }
}
