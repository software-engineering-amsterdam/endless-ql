using QLVizualizer.Expression.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVizualizer
{
    /// <summary>
    /// Class containing all messages that can be displayed to the user
    /// </summary>
    public static class UserMessages
    {
        #region Exceptions
        public static string ExceptionNoCombination(Type type1, Type type2, ExpressionOperator expressionOperator)
        {
            return string.Format("Combination of {0} and {1} not implemented for operator {2}", type1, type2, expressionOperator);
        }

        public static string ExceptionNoComparison(Type type1, Type type2, ExpressionOperator expressionOperator)
        {
            return string.Format("Comparison for {0} and {1} not implemented for operator {2}", type1, type2, expressionOperator);
        }
        #endregion
    }
}
