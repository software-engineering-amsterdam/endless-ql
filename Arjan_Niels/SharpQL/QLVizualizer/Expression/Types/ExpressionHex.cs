using System;
using QLVisualizer.Expression.Enums;

namespace QLVisualizer.Expression.Types
{
    public class ExpressionHex : TypedExpressionValue<Hexadecimal>
    {
        public ExpressionHex(LazyElementExpressionLink<Hexadecimal> lazyElementExpressionLink) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Hex, lazyElementExpressionLink)
        {
        }

        public ExpressionHex(ExpressionType type, string[] usedWidgetIDs, Func<Hexadecimal> expression) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Hex, usedWidgetIDs, expression)
        {
        }

        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            throw new NotImplementedException();
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            throw new NotImplementedException();
        }

        protected override Func<Hexadecimal> CombineExpressions(Func<Hexadecimal> expression1, Func<Hexadecimal> expression2, ExpressionOperator op)
        {
            throw new NotImplementedException();
        }
    }
}
