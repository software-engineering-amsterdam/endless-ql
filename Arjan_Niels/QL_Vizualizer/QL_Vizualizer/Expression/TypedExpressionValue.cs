using System;
using System.Collections.Generic;
using System.Linq;

namespace QL_Vizualizer.Expression.Types
{
    public abstract class TypedExpressionValue<T> : ExpressionValue
    {
        /// <summary>
        /// Contains all expresssions to be run
        /// </summary>
        private List<Func<T>> _expressionChain;

        /// <summary>
        /// Contains all operators to combine expressions
        /// </summary>
        private List<ExpressionOperator> _operatorChain;

        /// <summary>
        /// Result of expression
        /// </summary>
        public T Result
        {
            get
            {
                return GetExpression()();
            }
        }

        public TypedExpressionValue(Type[] compatibleTypes, ExpressionOperator[] compatibleOperators, string[] usedWidgetIDs, Func<T> expression) : base(compatibleTypes, compatibleOperators, typeof(T), usedWidgetIDs)
        {
            _expressionChain = new List<Func<T>>() { expression };
            _operatorChain = new List<ExpressionOperator>();
        }

        /// <summary>
        /// Creates single delegate as expression
        /// </summary>
        /// <returns>Delegate expression</returns>
        protected Func<T> GetExpression()
        {
            Func<T> resultExpression = _expressionChain[0];
            for (int i = 1; i < _expressionChain.Count; i++)
                resultExpression = CombineExpressions(resultExpression, _expressionChain[i], _operatorChain[i - 1]);

            return resultExpression;
        }

        /// <summary>
        /// Combine expressions with eachother
        /// </summary>
        /// <param name="expression1">Left hand side expression</param>
        /// <param name="expression2">Right hand side expression</param>
        /// <param name="op">Operator</param>
        /// <returns>Resulting expression</returns>
        protected abstract Func<T> CombineExpressions(Func<T> expression1, Func<T> expression2, ExpressionOperator op);

        /// <summary>
        /// Checks if combination is possible
        /// </summary>
        /// <param name="expressionValue">Expression to combine with</param>
        /// <param name="op">Operators for expression</param>
        /// <returns>Is valid combination</returns>
        protected bool ValidCombine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            return CompatibleTypes.Contains(expressionValue.Type) && CompatibleOperators.Contains(op);
        }

        /// <summary>
        /// Checks if a comparison is possible
        /// </summary>
        /// <param name="expressionValue">Expression to compare with</param>
        /// <param name="op">Comparison operator</param>
        /// <returns>Is valid comparison</returns>
        protected bool ValidCompare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            return ExpressionOperators.Comparison.Contains(op) && CompatibleOperators.Contains(op);
        }

        /// <summary>
        /// Adds expression and operator to the respective chains
        /// </summary>
        /// <param name="expression">Expression to add</param>
        /// <param name="op">Operator to add</param>
        protected void AddToChain(Func<T> expression, ExpressionOperator op)
        {
            _expressionChain.Add(expression);
            _operatorChain.Add(op);
        }

    }
}
