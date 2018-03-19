using System;

namespace Assignment1.Model.QL.RenderTree.Value
{
    public class AnswerValueBool : AnswerValue<bool>, IAnswerComparable, IAnswerLogical
    {
        public AnswerValueBool(bool value) : base(value, AnswerType.Boolean) { }

        public IAnswerValuable<bool> And<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator && cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value && rightVal);
        }

        public IAnswerValuable<bool> Equal<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value == rightVal);
        }

        public IAnswerValuable<bool> GreaterThan<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> GreaterThanOrEqual<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> LessThan<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> LessThanOrEqual<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> Not()
        {
            return new AnswerValueBool(!Value);
        }

        public IAnswerValuable<bool> NotEqual<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value != rightVal);
        }

        public IAnswerValuable<bool> Or<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator || cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value || rightVal);
        }
    }
}
