using System.Globalization;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLMoney : IValue
    {
        public static QLMoney Default => new QLMoney(0);

        public decimal Value { get; }
        private readonly bool _undefined = true;

        public QLMoney() { }

        public QLMoney(decimal value)
        {
            Value = value;
            _undefined = false;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);
        public bool IsUndefined() => _undefined;
        public override string ToString() => Value.ToString(CultureInfo.InvariantCulture);
    }
}
