using QLVisualizer.Expression.Enums;

namespace QLVisualizer
{
    /// <summary>
    /// Class containing all messages that can be displayed to the user
    /// </summary>
    public static class UserMessages
    {
        #region Exceptions
        public static string ExceptionNoCombination(ExpressionType type1, ExpressionType type2, ExpressionOperator expressionOperator)
        {
            return string.Format("Combination of {0} and {1} not implemented for operator {2}", type1, type2, expressionOperator);
        }

        public static string ExceptionNoComparison(ExpressionType type1, ExpressionType type2, ExpressionOperator expressionOperator)
        {
            return string.Format("Comparison for {0} and {1} not implemented for operator {2}", type1, type2, expressionOperator);
        }

        public static string ExceptionCannotParse(string value, string targetType)
        {
            return string.Format("Cannot parse value {0} to {2} value", value, targetType);
        }
        #endregion

        public static string UnsupportedStyle(string styleProperty, string styleType, string displayTarget)
        {
            return string.Format("The style property\"{0}\" of type \"{1}\" is not supported by the \"{2}\" visualizer", styleProperty, styleType, displayTarget);
        }
    }
}
