using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLBoolean : IValue
    {
        public static QLBoolean Default => new QLBoolean(false);

        public bool Value { get; }

        public QLBoolean() : this(false) { }

        public QLBoolean(bool value) => Value = value;

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => false;
        public override string ToString() => Value.ToString();
    }
}
