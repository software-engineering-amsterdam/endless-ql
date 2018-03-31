using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QLVisualizer.Expression.Enums;

namespace QLVisualizer.Expression.Types.Numeric
{
    public static class NumericUtils
    {
        public static TypedExpressionValue<bool> Compare(IExpressionNumeric leftNumeric, IExpressionNumeric rightNumeric, ExpressionOperator op)
        {
            ExpressionDouble left = leftNumeric.ToDoubleExpression();
            ExpressionDouble right = rightNumeric.ToDoubleExpression();
            string[] elementIDs = left.UsedIdentifiers.Concat(right.UsedIdentifiers).Distinct().ToArray();

            switch (op)
            {
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(elementIDs, () => { return left.Result > right.Result; });
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(elementIDs, () => { return left.Result >= right.Result; });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(elementIDs, () => { return left.Result < right.Result; });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(elementIDs, () => { return left.Result <= right.Result; });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(elementIDs, () => { return left.Result == right.Result; });
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(left.Type, right.Type, op));
        }
    }
}
