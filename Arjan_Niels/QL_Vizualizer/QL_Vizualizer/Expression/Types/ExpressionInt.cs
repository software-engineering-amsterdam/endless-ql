using System;

namespace QL_Vizualizer.Expression.Types
{
    public class ExpressionInt : TypedExpressionValue<int>
    {
        public ExpressionInt(string[] usedWidgetIDs, Func<int> expression) : base(new Type[] { typeof(double), typeof(int) }, ExpressionOperators.Numeric, usedWidgetIDs, expression)
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
            if(ValidCombine(expressionValue, op))
            {
                // Convert to correct expresion type
                ExpressionInt expression = null;
                if (expressionValue.Type == typeof(int))
                    expression = expressionValue as ExpressionInt;
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
                if (expressionValue.Type == typeof(double))
                    return CompareValue(expressionValue as TypedExpressionValue<double>, op);
                else if (expressionValue.Type == typeof(int))
                    return CompareValue(expressionValue as ExpressionInt, op); // Uses implicit cast
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));

        }

        /// <summary>
        /// Compares expression with double expression
        /// </summary>
        /// <param name="item">double expression</param>
        /// <param name="op">Compare operator</param>
        /// <returns>Boolean expression</returns>
        private TypedExpressionValue<bool> CompareValue(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result > item.Result; });
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result >= item.Result; });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result < item.Result; });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Result <= item.Result; });
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
            return new ExpressionDouble(expressionInt.UsedWidgetIDs, () => (double)expressionInt.Result);
        }
    }
}
