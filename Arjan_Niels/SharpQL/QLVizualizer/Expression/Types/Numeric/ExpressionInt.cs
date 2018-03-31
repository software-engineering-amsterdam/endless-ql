using QLVisualizer.Expression.Enums;
using System;

namespace QLVisualizer.Expression.Types.Numeric
{
    public class ExpressionInt : TypedExpressionValue<int>, IExpressionNumeric
    {
        public ExpressionInt(string[] usedWidgetIDs, Func<int> expression) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Int, usedWidgetIDs, expression)
        {
        }

        public ExpressionInt(LazyElementExpressionLink<int> lazyElementExpressionLink) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Int, lazyElementExpressionLink) { }

        #region Combine
        /// <summary>
        /// Combines with expressionValue
        /// </summary>
        /// <param name="item">Right hand side</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting expression</returns>
        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCombine(expressionValue, op))
            {
                // Convert to correct expresion type
                switch (expressionValue.Type)
                {
                    case ExpressionType.Int:
                        // Combine the two integers
                        ExpressionInt expression = expressionValue as ExpressionInt;
                        AddToChain(expression.GetExpression(), op);
                        UsedIdentifiers = CombineElements(expressionValue);
                        return this;
                    case ExpressionType.Double:
                        // Convert to double to continue combination
                        return ToDoubleExpression().Combine(expressionValue, op);
                    default:
                        throw new NotImplementedException();
                }

            }

            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, expressionValue.Type, op));
        }
        #endregion

        /// <summary>
        /// Compares with expression
        /// </summary>
        /// <param name="expressionValue">Expression to compare with</param>
        /// <param name="op">Operator</param>
        /// <returns>Boolean expression</returns>
        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            { 
                switch(expressionValue)
                {
                    case IExpressionNumeric numericExpression:
                        return NumericUtils.Compare(this, numericExpression, op);
                    default:
                        throw new NotImplementedException();
                }
                throw new NotImplementedException();
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
        }

        /// <summary>
        /// Combines two expressions
        /// </summary>
        /// <param name="item1">Left hand side</param>
        /// <param name="item2">Right hand side</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting delegate</returns>
        protected override Func<int> CombineExpressions(Func<int> item1, Func<int> item2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return () => item1() + item2();
                case ExpressionOperator.Minus:
                    return () => item1() - item2();
                case ExpressionOperator.Multiply:
                    return () => item1() * item2();
                case ExpressionOperator.Divide:
                    return () => item1() / item2();
            }
            throw new NotImplementedException();
        }

        /// <summary>
        /// Implicit cast to integer expression
        /// </summary>
        /// <param name="expressionInt">Integer expression</param>
        public static implicit operator ExpressionDouble(ExpressionInt expressionInt)
        {
            return expressionInt.ToDoubleExpression();
        }

        public ExpressionDouble ToDoubleExpression()
        {
            return new ExpressionDouble(UsedIdentifiers, () => (double)Result);
        }
    }
}
