using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Expression
{
    public class ExpressionLeaf<T> : IExpression<T>
    {
        /// <summary>
        /// Function to create the expression value
        /// </summary>
        private Func<T> _expression;

        /// <summary>
        /// Used widgetIDs for the expression
        /// </summary>
        private IEnumerable<string> _widgetIDs;

        public ExpressionLeaf(Func<T> run, params string[] widgetValues)
        {
            _expression = run;
            
            // Ensure all widgetIDs are stored only once
            _widgetIDs = widgetValues.Distinct();
        }

        /// <summary>
        /// Runs Expression
        /// </summary>
        /// <returns>Expression result</returns>
        public T Validate()
        {
            return _expression();
        }

        /// <summary>
        /// Combines Leaf with an Expression
        /// </summary>
        /// <param name="expression">Expression to combine with</param>
        /// <returns>New ExpressionComosite containing the leaf and the expression</returns>
        public IExpression<T> Add(IExpression<T> expression)
        {
            return new ExpressionComposite<T>(this, expression);
        }

        /// <summary>
        /// Get widgetIDs used in the expression
        /// </summary>
        /// <returns>All used WidgetIDs</returns>
        public IEnumerable<string> GetWidgetIDs()
        {
            return _widgetIDs;
        }
    }
}
