using QLVisualizer.Expression.Enums;
using System.Linq;

namespace QLVisualizer.Expression.Types
{
    public abstract class ExpressionValue
    {
        protected ExpressionType[] CompatibleTypes { get; private set; }

        protected ExpressionOperator[] CompatibleOperators { get; private set; }

        public ExpressionType ExpressionType { get; private set; }

        public string[] UsedIdentifiers { get; protected set; }

        public ExpressionValue(ExpressionType[] compatibleTypes, ExpressionOperator[] compatibleOperators, ExpressionType type, string[] usedWidgetIDs)
        {
            CompatibleTypes = compatibleTypes;
            CompatibleOperators = compatibleOperators;
            ExpressionType = type;
            UsedIdentifiers = usedWidgetIDs;
        }


        public abstract ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op);

        public abstract TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op);

        /// <summary>
        /// Combines the dependend ElementManager identifiers
        /// </summary>
        /// <param name="expressionValue">Expression to combine with</param>
        /// <returns>Combined ElementManager identifiers</returns>
        protected string[] CombineElements(ExpressionValue expressionValue)
        {
            // Create a list of unique widgetIDs
            return UsedIdentifiers
                    .Concat(expressionValue.UsedIdentifiers)
                    .Distinct()
                    .ToArray();
        }
    }
}
