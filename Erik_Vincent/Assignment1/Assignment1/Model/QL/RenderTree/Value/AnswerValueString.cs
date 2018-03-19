using System;

namespace Assignment1.Model.QL.RenderTree.Value
{
    public class AnswerValueString : AnswerValue<string>, IAnswerComparable, IAnswerComputable<string>
    {
        public AnswerValueString(string value) : base(value, AnswerType.String) { }

        public IAnswerValuable<string> Add<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator + cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueString(Value + right.Value);
        }

        public IAnswerValuable<string> Divide<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> Equal<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value.Equals(right.Value));
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

        public IAnswerValuable<string> Multiply<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }

        public IAnswerValuable<bool> NotEqual<U>(IAnswerValuable<U> right)
        {
            if (right.Type != AnswerType.String)
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(!(Value.Equals(right.Value)));
        }

        public IAnswerValuable<string> Subtract<U>(IAnswerValuable<U> right)
        {
            throw new InvalidOperationException();
        }
    }
}
