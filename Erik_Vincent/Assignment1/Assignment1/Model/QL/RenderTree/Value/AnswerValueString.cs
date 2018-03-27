using System;

namespace Assignment1.Model.QL.RenderTree.Value
{
    public class AnswerValueString : AnswerValue<string>, IAnswerComparable, IAnswerComputable
    {
        public AnswerValueString(string value) : base(value, AnswerType.String) { }

        public IAnswerValuable Add<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator + cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueString(Value + right.Value);
        }

        public IAnswerValuable Divide<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> Equal<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value.Equals(right.Value));
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

        public IAnswerValuable Multiply<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }

        public AnswerValue<bool> NotEqual<U>(AnswerValue<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(!(Value.Equals(right.Value)));
        }

        public IAnswerValuable Subtract<U>(AnswerValue<U> right)
        {
            throw new InvalidOperationException();
        }
    }
}
