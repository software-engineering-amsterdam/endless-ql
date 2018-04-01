using System;
using System.Globalization;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLDate : IValue
    {
        public static QLDate Default => new QLDate(DateTime.Today);

        public DateTime Value { get; }
        private readonly bool _undefined = true;

        public QLDate() { }

        public QLDate(DateTime value)
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
