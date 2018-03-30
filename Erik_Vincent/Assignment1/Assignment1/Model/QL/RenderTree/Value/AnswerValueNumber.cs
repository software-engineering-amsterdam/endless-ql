using System;

namespace Assignment1.Model.QL.RenderTree.Value
{
    class AnswerValueNumber : AnswerValue<decimal>, IAnswerComputable, IAnswerComparable
    {
        public AnswerValueNumber(decimal value, AnswerType type) : base(value, type) { }

        public IAnswerValuable Add<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator + cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value + Decimal.Parse(right.Value.ToString()), Type);
        }

        public IAnswerValuable Divide<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator / cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value / Decimal.Parse(right.Value.ToString()), Type);
        }

        public AnswerValue<bool> Equal<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value == Decimal.Parse(right.Value.ToString()));
        }

        public AnswerValue<bool> GreaterThan<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator > cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value > Decimal.Parse(right.Value.ToString()));
        }

        public AnswerValue<bool> GreaterThanOrEqual<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator >= cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value >= Decimal.Parse(right.Value.ToString()));
        }

        public AnswerValue<bool> LessThan<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator < cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value < Decimal.Parse(right.Value.ToString()));
        }

        public AnswerValue<bool> LessThanOrEqual<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator <= cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value <= Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable Multiply<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator * cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value * Decimal.Parse(right.Value.ToString()), Type);
        }

        public AnswerValue<bool> NotEqual<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value != Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable Subtract<U>(AnswerValue<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator - cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value - Decimal.Parse(right.Value.ToString()), Type);
        }
    }
}
