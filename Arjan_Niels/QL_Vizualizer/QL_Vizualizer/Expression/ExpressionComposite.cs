using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Expression
{
    public class ExpressionComposite<T> : IExpression<T>
    {
        /// <summary>
        /// Collection of expressions
        /// </summary>
        private List<IExpression<T>> _expressions;

        public ExpressionComposite(params IExpression<T>[] expressions)
        {
            _expressions = new List<IExpression<T>>(expressions);
        }

        /// <summary>
        /// Runs all expressions
        /// </summary>
        /// <returns>Combined result of all expressions</returns>
        public T Validate()
        {       
            return ExpressionCombiner.Combine(_expressions.Select(o => o.Validate()));
        }

        /// <summary>
        /// Adds expression to the collection
        /// </summary>
        /// <param name="expression">Expression to add</param>
        /// <returns>This element</returns>
        public IExpression<T> Add(IExpression<T> expression)
        {
            _expressions.Add(expression);
            return this;
        }

        /// <summary>
        /// Get all distinct used widgetID's
        /// </summary>
        /// <returns>Distinct widgetIDs</returns>
        public IEnumerable<string> GetWidgetIDs()
        {
            return _expressions.SelectMany(o => o.GetWidgetIDs()).Distinct();
        }
    }
}
