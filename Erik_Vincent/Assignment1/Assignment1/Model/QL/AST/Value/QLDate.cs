using System;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.Model.QL.AST.Value
{
    public class QLDate : IValue
    {
        public DateTime Value { get; }

        public Type Type => Type.Date;

        public QLDate(DateTime value)
        {
            Value = value;
        }

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);

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
            // TODO
            throw new NotImplementedException();
        }

        public QLBoolean LessThan(IValue right)
        {
            // TODO
            throw new NotImplementedException();
        }

        public QLBoolean GreaterThanOrEqual(IValue right)
        {
            // TODO
            throw new NotImplementedException();
        }

        public QLBoolean LessThanOrEqual(IValue right)
        {
            // TODO
            throw new NotImplementedException();
        }

        public QLBoolean Equal(IValue right)
        {
            // TODO
            throw new NotImplementedException();
        }

        public QLBoolean NotEqual(IValue right)
        {
            // TODO
            throw new NotImplementedException();
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
            throw new NotImplementedException();
        }
    }
}
