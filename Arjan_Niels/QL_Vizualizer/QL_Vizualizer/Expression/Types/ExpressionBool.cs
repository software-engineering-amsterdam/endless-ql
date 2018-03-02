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
                ExpressionBool expression = null;
                if (item.Type == typeof(bool))
                    expression = item as ExpressionBool;
                else
                    throw new NotImplementedException();

                AddToChain(expression.GetExpression(), op);
                UsedWidgetIDs = CombineWidgets(item);
                return this;
            }
            throw ExpressionExceptions.NoCombine(Type, item.Type, op);
        }

        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            {
                if (expressionValue.Type == typeof(bool))
                {
                    AddToChain((expressionValue as ExpressionBool).GetExpression(), op);
                    UsedWidgetIDs = CombineWidgets(expressionValue);
                    return this;
                }
                throw ExpressionExceptions.NoCompareImplemented(Type, expressionValue.Type, op);
            }
            throw ExpressionExceptions.NoCompare(Type, expressionValue.Type, op);
        }

        protected override Func<bool> GetExpression()
        {
            Func<bool> res = _expressionChain[0];
            for (int i = 1; i < _expressionChain.Count; i++)
                res = CombineFuncs(res, _expressionChain[i], _operatorChain[i - 1]);
            return res;
        }

        private Func<bool> CombineFuncs(Func<bool> item1, Func<bool> item2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.And:
                    return () => item1() && item2();
                case ExpressionOperator.Or:
                    return () => item1() || item2();
                case ExpressionOperator.Equals:
                    return () => item1() == item2();
            }

            throw ExpressionExceptions.NoCombineImplemented(Type, Type, op);
        }
    }
}
