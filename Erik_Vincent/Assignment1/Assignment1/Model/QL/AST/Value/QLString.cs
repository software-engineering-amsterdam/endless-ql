using Assignment1.Model.QL.AST.Expression;
using System;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLString : IValue
    {
        public string Value { get; }

        public Type Type => Type.String;

        public QLString(string value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);

        public IValue Add(IValue right)
        {
            if (right.Type != Type.String)
            {
                throw new NotSupportedException(OperationErrorMessage("+", Type.ToString(), right.Type.ToString()));
            }
            return new QLString(Value + ((QLString)right).Value);
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
            if (right.Type != Type.String)
            {
                throw new NotSupportedException(OperationErrorMessage("==", Type.ToString(), right.Type.ToString()));
            }
            return new QLBoolean(Value.Equals(((QLString)right).Value));
        }

        public QLBoolean NotEqual(IValue right)
        {
            if (right.Type != Type.String)
            {
                throw new NotSupportedException(OperationErrorMessage("!=", Type.ToString(), right.Type.ToString()));
            }
            return new QLBoolean(!Value.Equals(((QLString)right).Value));
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
