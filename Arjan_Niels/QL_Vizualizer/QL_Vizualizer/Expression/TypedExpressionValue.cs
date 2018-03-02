using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Expression.Types
{
    public abstract class TypedExpressionValue<T> : ExpressionValue
    {
        public TypedExpressionValue(Type[] compatibleTypes, ExpressionOperator[] compatibleOperators, string[] usedWidgetIDs, Func<T> expression) : base(compatibleTypes, compatibleOperators, typeof(T), usedWidgetIDs)
        {
            Expression = expression;
        }

        public Func<T> Expression { get; protected set; }

        protected bool ValidCombine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            return CompatibleTypes.Contains(expressionValue.Type) && CompatibleOperators.Contains(op);
        }

        protected bool ValidCompare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (!ExpressionOperators.Logical.Contains(op))
                throw new InvalidOperationException(string.Format("{0} is not a comparison operator", op));
            if (!CompatibleOperators.Contains(op))
                throw new InvalidOperationException(string.Format("{0} is not a compatible comparisson operator for {1}", op, Type));

            return true;
        }

    }
}
