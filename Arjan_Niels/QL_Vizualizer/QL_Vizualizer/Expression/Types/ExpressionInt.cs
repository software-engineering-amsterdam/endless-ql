using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression.Types
{
    public class ExpressionInt : TypedExpressionValue<int>
    {
        public ExpressionInt(string[] usedWidgetIDs, Func<int> expression) : base(new Type[] { typeof(double), typeof(int) }, ExpressionOperators.Numeric, usedWidgetIDs, expression)
        {
        }

        #region Combine
        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if(ValidCombine(expressionValue, op))
            {
                ExpressionInt expression = null;
                if (expressionValue.Type == typeof(int))
                    expression = expressionValue as ExpressionInt;
                else if (expressionValue.Type == typeof(double))
                    expression = expressionValue as ExpressionDouble;
                else
                    throw new NotImplementedException();

                AddToChain(expression.GetExpression(), op);
                UsedWidgetIDs = CombineWidgets(expressionValue);
                return this;
            }

            throw ExpressionExceptions.NoCombine(Type, expressionValue.Type, op);
        }
        #endregion

        #region Compare
        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCompare(expressionValue, op))
            {
                if (expressionValue.Type == typeof(double))
                    return CompareValue(expressionValue as TypedExpressionValue<double>, op);
                else if (expressionValue.Type == typeof(int))
                    return CompareValue(expressionValue as ExpressionInt, op);
            }
            throw ExpressionExceptions.NoCompare(Type, expressionValue.Type, op);

        }

        private TypedExpressionValue<bool> CompareValue(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() > item.Execute(); });
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() >= item.Execute(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() < item.Execute(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() <= item.Execute(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() == item.Execute(); });
            }
            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }
        #endregion

        protected override Func<int> GetExpression()
        {
            Func<int> res = _expressionChain[0];
            for (int i = 1; i < _expressionChain.Count; i++)
                res = CombineFuncs(res, _expressionChain[i], _operatorChain[i - 1]);
            return res;
        }

        private Func<int> CombineFuncs(Func<int> item1, Func<int> item2, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return () => item1() + item2();
                case ExpressionOperator.Minus:
                    return () => item1() - item2();
                case ExpressionOperator.Multiply:
                    return () => item1() * item2();
                case ExpressionOperator.Divide:
                    return () => item1() / item2();
            }
            throw new NotImplementedException();
        }

        public static implicit operator ExpressionDouble(ExpressionInt expressionInt)
        {
            return new ExpressionDouble(expressionInt.UsedWidgetIDs, () => (double)expressionInt.Execute());
        }
    }
}
