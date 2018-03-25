using System;

namespace Assignment1.Model.QL.RenderTree.Value
{
    public class AnswerValueBool : AnswerValue<bool>, IAnswerComparable, IAnswerLogical
    {
        public AnswerValueBool(bool value) : base(value, AnswerType.Boolean) { }

        public AnswerValue<bool> And<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator && cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value && rightVal);
        }

        public AnswerValue<bool> Equal<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value == rightVal);
        }

        public AnswerValue<bool> GreaterThan<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> GreaterThanOrEqual<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> LessThan<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> LessThanOrEqual<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> Not()
        {
            return new AnswerValueBool(!Value);
        }

        public AnswerValue<bool> NotEqual<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.Boolean)
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            bool rightVal = Boolean.Parse(right.Value.ToString());
            return new AnswerValueBool(Value != rightVal);
        }

        public AnswerValue<bool> Or<U>(AnswerValue<U> right)
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
