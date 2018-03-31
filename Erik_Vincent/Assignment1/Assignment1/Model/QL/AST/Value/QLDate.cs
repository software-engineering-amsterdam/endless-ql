using System;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLDate : IValue
    {
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
    }
}
