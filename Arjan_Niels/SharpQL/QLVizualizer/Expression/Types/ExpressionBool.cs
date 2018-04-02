using QLVisualizer.Expression.Enums;
using System;

namespace QLVisualizer.Expression.Types
{
    public class ExpressionBool : TypedExpressionValue<bool>
    {
        // Define boolean in expression
        public ExpressionBool(string[] usedWidgetIDs, Func<bool> value) : base(ExpressionTypes.Logical,
                                                                            ExpressionOperators.Logical,
                                                                            ExpressionType.Bool,
                                                                            usedWidgetIDs,
                                                                            value)
        {
        }

        public ExpressionBool(LazyElementExpressionLink<bool> lazyElementExpressionLink) : base(ExpressionTypes.Logical, ExpressionOperators.Logical, ExpressionType.Bool, lazyElementExpressionLink) { }

        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCombine(expressionValue, op))
            {
                ExpressionBool expression;
                switch (expressionValue.ExpressionType)
                {
                    case ExpressionType.Bool:
                        expression = expressionValue as ExpressionBool;
                        break;
                    default:
                        throw new NotImplementedException();
                }

                AddToChain(expression.GetExpression(), op);
                UsedIdentifiers = CombineElements(expressionValue);
                return this;
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(ExpressionType, expressionValue.ExpressionType, op));
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            {
                ExpressionBool expression;
                switch (expressionValue.ExpressionType)
                {
                    case ExpressionType.Bool:
                        expression = expressionValue as ExpressionBool;
                        break;
                    default:
                        throw new NotImplementedException();
                }

                AddToChain(expression.GetExpression(), op);
                UsedIdentifiers = CombineElements(expressionValue);
                return this;

            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(ExpressionType, expressionValue.ExpressionType, op));
        }

        protected override Func<bool> CombineExpressions(Func<bool> item1, Func<bool> item2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.And:
                    return () => item1() && item2();
                case ExpressionOperator.Or:
                    return () => item1() || item2();
                case ExpressionOperator.Equals:
                    return () => item1() == item2();
            }

            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(ExpressionType, ExpressionType, op));
        }
    }
}
