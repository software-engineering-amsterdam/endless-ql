using Assignment1.Model.QL.AST.Expression;
using System;

namespace Assignment1.Model.QL.AST.Value
{
    public class Undefined : IValue
    {
        public Type Type => Type.Undefined;

        public void Accept(IExpressionVisitor visitor) => visitor.Visit(this);
        public void Accept(IValueVisitor visitor) => visitor.Visit(this);

        public IValue Add(IValue right)
        {
            return new Undefined();
        }

        public QLBoolean And(IValue right)
        {
            return new QLBoolean(false);
        }

        public IValue Divide(IValue right)
        {
            return new Undefined();
        }

        public QLBoolean Equal(IValue right)
        {
            return new QLBoolean(false);
        }

        public QLBoolean GreaterThan(IValue right)
        {
            return new QLBoolean(false);
        }

        public QLBoolean GreaterThanOrEqual(IValue right)
        {
            return new QLBoolean(false);
        }

        public QLBoolean LessThan(IValue right)
        {
            return new QLBoolean(false);
        }

        public QLBoolean LessThanOrEqual(IValue right)
        {
            return new QLBoolean(false);
        }

        public IValue Multiply(IValue right)
        {
            return new Undefined();
        }

        public QLBoolean Not()
        {
            return new QLBoolean(false);
        }

        public QLBoolean NotEqual(IValue right)
        {
            return new QLBoolean(false);
        }

        public string OperationErrorMessage(string operation, string leftType, string rightType)
        {
            throw new NotSupportedException();
        }

        public QLBoolean Or(IValue right)
        {
            return new QLBoolean(false);
        }

        public IValue Subtract(IValue right)
        {
            return new Undefined();
        }
    }
}
