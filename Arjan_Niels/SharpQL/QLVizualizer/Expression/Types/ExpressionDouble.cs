using System;


namespace QLVisualizer.Expression.Types
{
    public class ExpressionDouble : TypedExpressionValue<double>
    {
        public ExpressionDouble(string[] usedWidgetIDs, Func<double> expression) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Double, usedWidgetIDs, expression)
        {
        }

        public ExpressionDouble(LazyElementExpressionLink<double> lazyElementExpressionLink) : base(ExpressionTypes.Numeric, ExpressionOperators.Numeric, ExpressionType.Double, lazyElementExpressionLink) { }

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
                ExpressionDouble expression;
                switch (expressionValue.Type)
                {
                    case ExpressionType.Int:
                        // Double has more precision, convert int to double (implicit)
                        expression = expressionValue as ExpressionInt;
                        break;
                    case ExpressionType.Double:
                        expression = expressionValue as ExpressionDouble;
                        break;
                    default:
                        throw new NotImplementedException();
                }

                AddToChain(expression.GetExpression(), op);
                UsedIdentifiers = CombineWidgets(expressionValue);
                return this;
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, expressionValue.Type, op));
        }
        #endregion

        #region Compare
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
                switch (expressionValue.Type)
                {
                    case ExpressionType.Int:
                        return CompareWith(expressionValue as ExpressionInt, op);
                    case ExpressionType.Double:
                        return CompareWith(expressionValue as ExpressionDouble, op);
                    default:
                        throw new NotImplementedException();
                }
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
        }

        /// <summary>
        /// Compares expression with double expression
        /// </summary>
        /// <param name="item">double expression</param>
        /// <param name="op">Compare operator</param>
        /// <returns>Boolean expression</returns>
        private ExpressionBool CompareWith(ExpressionDouble item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result >= item.Result; });
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result > item.Result; });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result <= item.Result; });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result < item.Result; });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result == item.Result; });
            }

            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, item.Type, op));
        }
        #endregion
        /// <summary>
        /// Combines two expressions
        /// </summary>
        /// <param name="item1">Left hand side</param>
        /// <param name="item2">Right hand side</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting delegate</returns>
        protected override Func<double> CombineExpressions(Func<double> item1, Func<double> item2, ExpressionOperator op)
        {
            switch(op)
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
            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, Type, op));
        }


        /// <summary>
        /// Implicit cast to double expression
        /// </summary>
        /// <param name="expressionDouble">Double expression</param>
        public static implicit operator ExpressionInt(ExpressionDouble expressionDouble)
        {
            return expressionDouble.ToIntExpression();
        }

        private ExpressionInt ToIntExpression()
        {
            return new ExpressionInt(UsedIdentifiers, () => (int)Result);
        }
    }
}
