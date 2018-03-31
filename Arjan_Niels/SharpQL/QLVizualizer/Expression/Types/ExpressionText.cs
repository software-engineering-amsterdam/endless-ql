﻿using System;
using QLVisualizer.Expression.Enums;

namespace QLVisualizer.Expression.Types
{
    public class ExpressionText : TypedExpressionValue<string>
    {
        public ExpressionText(LazyElementExpressionLink<string> lazyElementExpressionLink) : base(new ExpressionType[] { ExpressionType.Text }, new ExpressionOperator[] { ExpressionOperator.Plus }, ExpressionType.Text, lazyElementExpressionLink)
        {
        }

        public ExpressionText(string[] usedWidgetIDs, Func<string> expression) : base(new ExpressionType[] { ExpressionType.Text }, new ExpressionOperator[] { ExpressionOperator.Plus }, ExpressionType.Text, usedWidgetIDs, expression)
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
                        break;
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, expressionValue.Type, op));
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
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
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
