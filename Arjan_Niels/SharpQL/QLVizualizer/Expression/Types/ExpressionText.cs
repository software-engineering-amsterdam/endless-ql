using QLVisualizer.Expression.Enums;
using System;

namespace QLVisualizer.Expression.Types
{
    public class ExpressionText : TypedExpressionValue<string>
    {
        public ExpressionText(LazyElementExpressionLink<string> lazyElementExpressionLink) : base(new ExpressionType[] { ExpressionType.Text }, new ExpressionOperator[] { ExpressionOperator.Plus, ExpressionOperator.Equals }, ExpressionType.Text, lazyElementExpressionLink)
        {
        }

        public ExpressionText(string[] usedWidgetIDs, Func<string> expression) : base(new ExpressionType[] { ExpressionType.Text }, new ExpressionOperator[] { ExpressionOperator.Plus, ExpressionOperator.Equals }, ExpressionType.Text, usedWidgetIDs, expression)
        {
        }

        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCombine(expressionValue, op))
            {
                switch (expressionValue)
                {
                    case ExpressionText expressionText:
                        AddToChain(expressionText.GetExpression(), op);
                        UsedIdentifiers = CombineElements(expressionText);
                        return this;
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(ExpressionType, expressionValue.ExpressionType, op));
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            {
                switch (expressionValue)
                {
                    case ExpressionText expressionText:
                        return CompareWith(expressionText, op);
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(ExpressionType, expressionValue.ExpressionType, op));
        }

        private ExpressionBool CompareWith(ExpressionText expressionText, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineElements(expressionText), () => Result == expressionText.Result);
                default:
                    throw new NotImplementedException();
            }
        }

        protected override Func<string> CombineExpressions(Func<string> expression1, Func<string> expression2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return () => expression1() + expression2();
                default:
                    throw new NotImplementedException();
            }
        }
    }
}
