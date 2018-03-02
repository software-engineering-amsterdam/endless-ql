using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression.Types
{
    public class ExpressionBool : TypedExpressionValue<bool>
    {
        // Define boolean in expression
        public ExpressionBool(string[] usedWidgetIDs, Func<bool> value) : base(new Type[] { typeof(bool) },
                                                                            new ExpressionOperator[] {
                                                                                ExpressionOperator.And,
                                                                                ExpressionOperator.Or,
                                                                                ExpressionOperator.Equals
                                                                            },
                                                                            usedWidgetIDs,
                                                                            value)
        {
        }


        public override ExpressionValue Combine(ExpressionValue item, ExpressionOperator op)
        {
            // Validity check
            if (ValidCombine(item, op))
            {
                // Case for bool
                if (item.GetType() == typeof(bool))
                    return CombineWithBool(item as TypedExpressionValue<bool>, op);
                throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
            }
            throw ExpressionExceptions.NoCombine(Type, item.Type, op);
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            {
                if (expressionValue.Type == typeof(bool))
                    return CombineWithBool(expressionValue as TypedExpressionValue<bool>, op);
                throw ExpressionExceptions.NoCompareImplemented(Type, expressionValue.Type, op);
            }
            throw ExpressionExceptions.NoCompare(Type, expressionValue.Type, op);
        }

        private ExpressionBool CombineWithBool(TypedExpressionValue<bool> item, ExpressionOperator op)
        {
            UsedWidgetIDs = CombineWidgets(item);
            switch (op)
            {
                case ExpressionOperator.And:
                    Expression = () => { return Expression() && item.Expression(); };
                    return this;
                case ExpressionOperator.Or:
                    Expression = () => { return Expression() || item.Expression(); };
                    return this;
                case ExpressionOperator.Equals:
                    Expression = () => { return Expression() == item.Expression(); };
                    return this;
            }

            throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
        }
    }
}
