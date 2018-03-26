using Assignment1.Model.QL.AST.Expression;
using System;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLBoolean : IValue
    {
        public bool Value { get; }

        public Type Type => Type.Boolean;

        public QLBoolean(bool value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);

        public IValue Add(IValue right)
        {
            throw new NotSupportedException();
        }

        public IValue Subtract(IValue right)
        {
            throw new NotSupportedException();
        }

        public IValue Multiply(IValue right)
        {
            throw new NotSupportedException();
        }

        public IValue Divide(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean GreaterThan(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean LessThan(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean GreaterThanOrEqual(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean LessThanOrEqual(IValue right)
        {
            throw new NotSupportedException();
        }

        public QLBoolean Equal(IValue right)
        {
            if (right.Type != Type.Boolean)
            {
                throw new NotSupportedException(OperationErrorMessage("==", Type.ToString(), right.Type.ToString()));
            }
            QLBoolean boolValue = (QLBoolean)right;
            return new QLBoolean(Value == boolValue.Value);
        }

        public QLBoolean NotEqual(IValue right)
        {
            if (right.Type != Type.Boolean)
            {
                throw new NotSupportedException(OperationErrorMessage("!=", Type.ToString(), right.Type.ToString()));
            }
            QLBoolean boolValue = (QLBoolean)right;
            return new QLBoolean(Value != boolValue.Value);
        }

        public QLBoolean And(IValue right)
        {
            if (right.Type != Type.Boolean)
            {
                throw new NotSupportedException(OperationErrorMessage("&&", Type.ToString(), right.Type.ToString()));
            }
            QLBoolean boolValue = (QLBoolean)right;
            return new QLBoolean(Value && boolValue.Value);
        }

        public QLBoolean Or(IValue right)
        {
            if (right.Type != Type.Boolean)
            {
                throw new NotSupportedException(OperationErrorMessage("||", Type.ToString(), right.Type.ToString()));
            }
            QLBoolean boolValue = (QLBoolean)right;
            return new QLBoolean(Value || boolValue.Value);
        }

        public QLBoolean Not()
        {
            return new QLBoolean(!Value);
        }

        public string OperationErrorMessage(string operation, string leftType, string rightType)
        {
            return "Operator " + operation + " cannot be applied to " + leftType + " and " + rightType;
        }
    }
}
