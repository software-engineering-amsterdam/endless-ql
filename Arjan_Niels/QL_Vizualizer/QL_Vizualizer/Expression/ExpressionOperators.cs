using QL_Vizualizer.Expression.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression
{
    public static class ExpressionOperators
    {
        /// <summary>
        /// All ExpressionOperators that can be executed on logical statements
        /// </summary>
        public static ExpressionOperator[] Logical = new ExpressionOperator[]
        {
            ExpressionOperator.And,
            ExpressionOperator.Or,
            ExpressionOperator.Equals
        };


        /// <summary>
        /// All ExpressionOperators that can be used for comparison
        /// </summary>
        public static ExpressionOperator[] Comparison = new ExpressionOperator[]
        {
            ExpressionOperator.Equals,
            ExpressionOperator.GreaterEquals,
            ExpressionOperator.LessEquals,
            ExpressionOperator.GreaterThan,
            ExpressionOperator.LessThan
        };

        /// <summary>
        /// All ExpressionOperators that can be used for numeric statements
        /// </summary>
        public static ExpressionOperator[] Numeric = new ExpressionOperator[]
        {
            ExpressionOperator.Equals,
            ExpressionOperator.GreaterEquals,
            ExpressionOperator.LessEquals,
            ExpressionOperator.GreaterThan,
            ExpressionOperator.LessThan,
            ExpressionOperator.Plus,
            ExpressionOperator.Minus,
            ExpressionOperator.Multiply,
            ExpressionOperator.Divide
        };

    }
}
