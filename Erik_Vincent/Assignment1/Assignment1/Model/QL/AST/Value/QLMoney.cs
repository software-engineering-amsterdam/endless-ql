using Assignment1.Model.QL.AST.Expression;
using System;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLMoney : IValue
    {
        public decimal Value { get; }

        public Type Type => Type.Money;

        public QLMoney(decimal value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);

        public IValue Add(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLMoney(Value + ((QLInteger)right).Value);
                case Type.Decimal: return new QLMoney(Value + ((QLDecimal)right).Value);
                case Type.Money: return new QLMoney(Value + ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("+", Type.ToString(), right.Type.ToString()));
            }
        }

        public IValue Subtract(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLMoney(Value - ((QLInteger)right).Value);
                case Type.Decimal: return new QLMoney(Value - ((QLDecimal)right).Value);
                case Type.Money: return new QLMoney(Value - ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("-", Type.ToString(), right.Type.ToString()));
            }
        }

        public IValue Multiply(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLMoney(Value * ((QLInteger)right).Value);
                case Type.Decimal: return new QLMoney(Value * ((QLDecimal)right).Value);
                case Type.Money: throw new NotSupportedException(OperationErrorMessage("*", Type.ToString(), right.Type.ToString()));
                default: throw new NotSupportedException(OperationErrorMessage("*", Type.ToString(), right.Type.ToString()));
            }
        }

        public IValue Divide(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLMoney(Value / ((QLInteger)right).Value);
                case Type.Decimal: return new QLMoney(Value / ((QLDecimal)right).Value);
                case Type.Money: throw new NotSupportedException(OperationErrorMessage("/", Type.ToString(), right.Type.ToString()));
                default: throw new NotSupportedException(OperationErrorMessage("/", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean GreaterThan(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value > ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value > ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value > ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage(">", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean LessThan(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value < ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value < ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value < ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("<", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean GreaterThanOrEqual(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value >= ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value >= ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value >= ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage(">=", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean LessThanOrEqual(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value <= ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value <= ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value <= ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("<=", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean Equal(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value == ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value == ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value == ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("==", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean NotEqual(IValue right)
        {
            switch (right.Type)
            {
                case Type.Integer: return new QLBoolean(Value != ((QLInteger)right).Value);
                case Type.Decimal: return new QLBoolean(Value != ((QLDecimal)right).Value);
                case Type.Money: return new QLBoolean(Value != ((QLMoney)right).Value);
                default: throw new NotSupportedException(OperationErrorMessage("!=", Type.ToString(), right.Type.ToString()));
            }
        }

        public QLBoolean And(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean Or(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean Not()
        {
            throw new NotSupportedException();
        }

        public string OperationErrorMessage(string operation, string leftType, string rightType)
        {
            return "Operator " + operation + " cannot be applied to " + leftType + " and " + rightType;
        }
    }
}
