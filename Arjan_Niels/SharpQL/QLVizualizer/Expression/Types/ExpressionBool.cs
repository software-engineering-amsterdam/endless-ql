using QLVisualizer.Elements.Managers.LeafTypes;
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

        /// <summary>
        /// Combines with expressionValue
        /// </summary>
        /// <param name="expressionValue">Right hand side</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting expression</returns>
        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCombine(expressionValue, op))
            {
                ExpressionBool expression;
                switch (expressionValue.Type)
                {
                    case ExpressionType.Bool:
                        expression = expressionValue as ExpressionBool;
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
                ExpressionBool expression;
                switch(expressionValue.Type)
                {
                    case ExpressionType.Bool:
                        expression = expressionValue as ExpressionBool;
                        break;
                    default:
                        throw new NotImplementedException();
                }

                    AddToChain(expression.GetExpression(), op);
                    UsedIdentifiers = CombineWidgets(expressionValue);
                    return this;
                
            }
            throw new InvalidOperationException(UserMessages.ExceptionNoComparison(Type, expressionValue.Type, op));
        }

        /// <summary>
        /// Combines two boolean expressions
        /// </summary>
        /// <param name="item1">Left hand side</param>
        /// <param name="item2">Right hand side</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting delegate</returns>
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

            throw new InvalidOperationException(UserMessages.ExceptionNoCombination(Type, Type, op));
        }
    }
}
