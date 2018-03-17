using System;

namespace Assignment1.Model.QL.Value
{
    class AnswerValueNumber : AnswerValue<decimal>, IAnswerComputable<decimal>, IAnswerComparable
    {
        public AnswerValueNumber(decimal value, AnswerType type) : base(value, type) { }

        public IAnswerValuable<decimal> Add<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return (IAnswerValuable<decimal>)new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator + cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value + Decimal.Parse(right.Value.ToString()), Type);
        }

        public IAnswerValuable<decimal> Divide<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return (IAnswerValuable<decimal>)new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator / cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value / Decimal.Parse(right.Value.ToString()), Type);
        }

        public IAnswerValuable<bool> Equal<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator == cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value == Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<bool> GreaterThan<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator > cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value > Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<bool> GreaterThanOrEqual<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator >= cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value >= Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<bool> LessThan<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator < cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value < Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<bool> LessThanOrEqual<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator <= cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value <= Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<decimal> Multiply<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return (IAnswerValuable<decimal>)new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator * cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value * Decimal.Parse(right.Value.ToString()), Type);
        }

        public IAnswerValuable<bool> NotEqual<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator != cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueBool(Value != Decimal.Parse(right.Value.ToString()));
        }

        public IAnswerValuable<decimal> Subtract<U>(IAnswerValuable<U> right)
        {
            if (right.IsUndefined) return (IAnswerValuable<decimal>)new AnswerValueUndefined();
            if (!right.Type.IsNumeric())
            {
                throw new NotSupportedException("Operator - cannot be applied to " + Type.ToString() + " and " + right.Type.ToString());
            }
            return new AnswerValueNumber(Value - Decimal.Parse(right.Value.ToString()), Type);
        }
    }
}
