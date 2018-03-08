using System;


namespace QLVizualizer.Expression.Types
{
    public class ExpressionDouble : TypedExpressionValue<double>
    {
        public ExpressionDouble(string[] usedWidgetIDs, Func<double> expression) : base(new Type[] { typeof(int), typeof(double) }, ExpressionOperators.Numeric, usedWidgetIDs, expression)
        {
        }

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
                // Get correct expressionValue
                ExpressionDouble expression = null;
                if (expressionValue.Type == typeof(int))
                    expression = expressionValue as ExpressionInt; // Uses implicit cast
                else if (expressionValue.Type == typeof(double))
                    expression = expressionValue as ExpressionDouble;
                else
                    throw new NotImplementedException();

                AddToChain(expression.GetExpression(), op);
                UsedWidgetIDs = CombineWidgets(expressionValue);
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
                // Cast to correct type
                if (expressionValue.Type == typeof(int))
                    return CompareWith(expressionValue as ExpressionInt, op);   // Uses implicit casting to TypedExpressionValue<double>
                else if (expressionValue.Type == typeof(double))
                    return CompareWith(expressionValue as ExpressionDouble, op);
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
        }

        /// <summary>
        /// Compares expression with double expression
        /// </summary>
        /// <param name="item">double expression</param>
        /// <param name="op">Compare operator</param>
        /// <returns>Boolean expression</returns>
        private ExpressionBool CompareWith(TypedExpressionValue<double> item, ExpressionOperator op)
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
            return new ExpressionInt(expressionDouble.UsedWidgetIDs, () => (int)expressionDouble.Result);
        }
    }
}
