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
            return string.Format("Cannot parse value {0} to {1} value", value, targetType);
        }
        #endregion

        public static string UnsupportedStyle(string styleProperty, string styleType, string displayTarget)
        {
            return string.Format("The style property\"{0}\" of type \"{1}\" is not supported by the \"{2}\" visualizer", styleProperty, styleType, displayTarget);
        }

        public static string ExportedAnswers(string filename, string formname)
        {
            return string.Format("The answers of form \"{0}\" are exported to:{1}", formname, filename);
        }

        public static string ErrorsOccurred(bool single)
        {
            return string.Format("Error{0} occured", single ? "" : "s");
        }

        public static string SuccesfulExport()
        {
            return "Export succesfull";
        }

        public static string Parse()
        {
            return "Parse";
        }

        public static string Export()
        {
            return "Export";
        }

        public static string FatalErrorOccured(string message)
        {
            return string.Format("A fatal error occured:{0}\n parsing will terminate", message);
        }

        public static string UnableToExport(string filename)
        {
            return string.Format("Unable to export the answers to {0}", filename);
        }
    }
}
