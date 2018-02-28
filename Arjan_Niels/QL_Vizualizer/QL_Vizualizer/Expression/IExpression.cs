using System.Collections.Generic;

namespace QL_Vizualizer.Expression
{
    /// <summary>
    /// Expression, composite element
    /// </summary>
    /// <typeparam name="T">Result of the expression</typeparam>
    public interface IExpression<T>
    {
        /// <summary>
        /// Runs expression
        /// </summary>
        /// <returns>Result of the expression</returns>
        T Validate();

        /// <summary>
        /// Add expression to current expression
        /// </summary>
        /// <param name="expression">Expression to add</param>
        /// <returns>Combined expression</returns>
        IExpression<T> Add(IExpression<T> expression);

        /// <summary>
        /// Get all widgetID's used in this expression
        /// </summary>
        /// <returns>All used widgetID's</returns>
        IEnumerable<string> GetWidgetIDs();
    }

    
}
