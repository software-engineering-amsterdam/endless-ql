using System;
using QLVisualizer.Expression.Enums;

namespace QLVisualizer.Expression.Types.Numeric
{
    public class ExpressionHex : TypedExpressionValue<Hexadecimal>, IExpressionNumeric
    {
        public ExpressionHex(LazyElementExpressionLink<Hexadecimal> lazyElementExpressionLink) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Hex, lazyElementExpressionLink)
        {
        }

        public ExpressionHex(ExpressionType type, string[] usedWidgetIDs, Func<Hexadecimal> expression) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Hex, usedWidgetIDs, expression)
        {
        }

        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if(ValidCombine(expressionValue, op))
            {
                switch (expressionValue.Type)
                {
                    case ExpressionType.Hex:
                        ExpressionHex expression = expressionValue as ExpressionHex;
                        AddToChain(expression.GetExpression(), op);
                        UsedIdentifiers = CombineElements(expression);
                        return this;
                    case ExpressionType.Int:
                        return ToIntExpression().Combine(expressionValue, op);
                    case ExpressionType.Double:
                        return ToDoubleExpression().Combine(expressionValue, op);
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, expressionValue.Type, op));
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if(ValidCompare(expressionValue, op))
            {
                switch(expressionValue)
                {
                    case IExpressionNumeric numericExpression:
                        return NumericUtils.Compare(this, numericExpression, op);
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
            
        }

        protected override Func<Hexadecimal> CombineExpressions(Func<Hexadecimal> expression1, Func<Hexadecimal> expression2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return () => expression1() + expression2();
                case ExpressionOperator.Minus:
                    return () => expression1() - expression2();
                case ExpressionOperator.Multiply:
                    return () => expression1() * expression2();
                case ExpressionOperator.Divide:
                    return () => expression1() / expression2();
            }
            throw new NotImplementedException();
        }

        private ExpressionInt ToIntExpression()
        {
            return new ExpressionInt(UsedIdentifiers, () => Result.ToInteger());
        }

        public ExpressionDouble ToDoubleExpression()
        {
            return new ExpressionDouble(UsedIdentifiers, () => Result.ToInteger());
        }
    }
}
