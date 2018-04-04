using QLVisualizer.Expression.Enums;
using System;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Expression.Types
{
    public abstract class TypedExpressionValue<T> : ExpressionValue
    {
        protected LazyElementExpressionLink<T> _elementExpressionLink { get; private set; }

        /// <summary>
        /// Contains all expresssions to be run
        /// </summary>
        private List<Func<T>> _expressionChain;

        /// <summary>
        /// Contains all operators to combine expressions
        /// </summary>
        private List<ExpressionOperator> _operatorChain;

        public T Result
        {
            get
            {
                return GetExpression()();
            }
        }

        public TypedExpressionValue(ExpressionType[] compatibleTypes, ExpressionOperator[] compatibleOperators, ExpressionType type, string[] usedWidgetIDs, Func<T> expression) : base(compatibleTypes, compatibleOperators, type, usedWidgetIDs)
        {
            _expressionChain = new List<Func<T>>() { expression };
            _operatorChain = new List<ExpressionOperator>();
        }

        public TypedExpressionValue(ExpressionType[] compatibleTypes, ExpressionOperator[] compatibleOperators, ExpressionType type, LazyElementExpressionLink<T> lazyElementExpressionLink) : base(compatibleTypes, compatibleOperators, type, new string[] { lazyElementExpressionLink.QuestionElementManagerID })
        {
            _elementExpressionLink = lazyElementExpressionLink;
            _expressionChain = new List<Func<T>>() { () => _elementExpressionLink.ElementValue };
            _operatorChain = new List<ExpressionOperator>();
        }

        protected Func<T> GetExpression()
        {
            Func<T> resultExpression = _expressionChain[0];
            for (int i = 1; i < _expressionChain.Count; i++)
                resultExpression = CombineExpressions(resultExpression, _expressionChain[i], _operatorChain[i - 1]);

            return resultExpression;
        }

        protected abstract Func<T> CombineExpressions(Func<T> expression1, Func<T> expression2, ExpressionOperator op);

        protected bool ValidCombine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            return CompatibleTypes.Contains(expressionValue.ExpressionType) && CompatibleOperators.Contains(op);
        }

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
