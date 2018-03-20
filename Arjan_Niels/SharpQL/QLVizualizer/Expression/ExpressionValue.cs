using System;
using System.Linq;

namespace QLVisualizer.Expression.Types
{
    public abstract class ExpressionValue
    {
        /// <summary>
        /// Value types that this expression can be compared and combined with
        /// </summary>
        protected Type[] CompatibleTypes { get; private set; }

        /// <summary>
        /// Operators that can be used on this expression
        /// </summary>
        protected ExpressionOperator[] CompatibleOperators { get; private set; }

        /// <summary>
        /// Result-vale type
        /// </summary>
        public Type Type { get; private set; }

        /// <summary>
        /// Widgets this expression is dependend on
        /// </summary>
        public string[] UsedIdentifiers { get; protected set; }

        public ExpressionValue(Type[] compatibleTypes, ExpressionOperator[] compatibleOperators, Type type, string[] usedWidgetIDs)
        {
            CompatibleTypes = compatibleTypes;
            CompatibleOperators = compatibleOperators;
            Type = type;
            UsedIdentifiers = usedWidgetIDs;
        }

        /// <summary>
        /// Combines this expression with a provided expression
        /// </summary>
        /// <param name="expressionValue">Expression to combine with</param>
        /// <param name="op">Operator for combination</param>
        /// <returns>Resulting expression</returns>
        public abstract ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op);

        /// <summary>
        /// Compares this expression with a provided expression
        /// </summary>
        /// <param name="expressionValue">Expression to compare with</param>
        /// <param name="op">Comparison Operator</param>
        /// <returns>Boolean expression</returns>
        public abstract TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op);

        /// <summary>
        /// Combines the dependend widgetIDs
        /// </summary>
        /// <param name="expressionValue">Expression to combine with</param>
        /// <returns>Combined widgetIDs</returns>
        protected string[] CombineWidgets(ExpressionValue expressionValue)
        {
            // Create a list of unique widgetIDs
            return UsedIdentifiers.Concat(expressionValue.UsedIdentifiers).Distinct().ToArray();
        }
    }
}
