using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression.Types
{
    public class ExpressionDouble : TypedExpressionValue<double>
    {
        public ExpressionDouble(string[] usedWidgetIDs, Func<double> expression) : base(new Type[] { typeof(int), typeof(double) }, ExpressionOperators.Numeric, usedWidgetIDs, expression)
        {
        }

        #region Combine
        public override ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if (ValidCombine(expressionValue, op))
            {
                _expressionChain.Add(() => (expressionValue as ExpressionDouble).Execute());
                _operatorChain.Add(op);
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
                if (expressionValue.Type == typeof(int))
                    return CompareWithInt(expressionValue as TypedExpressionValue<int>, op);
                else if (expressionValue.Type == typeof(double))
                    return CompareWithDouble(expressionValue as TypedExpressionValue<double>, op);
                throw ExpressionExceptions.NoCompareImplemented(Type, expressionValue.Type, op);
            }
            throw ExpressionExceptions.NoCompare(Type, expressionValue.Type, op);
        }

        private ExpressionBool CompareWithInt(TypedExpressionValue<int> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() >= item.Execute(); });
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() > item.Execute(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() <= item.Execute(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() < item.Execute(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() == item.Execute(); });
            }

            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }

        private ExpressionBool CompareWithDouble(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() >= item.Execute(); });
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() > item.Execute(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() <= item.Execute(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() < item.Execute(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Execute() == item.Execute(); });
            }

            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }
        #endregion
        protected override Func<double> GetExpression()
        {
            Func<double> res = _expressionChain[0];
            for (int i = 1; i < _expressionChain.Count; i++)
                res = CombineFuncs(res, _expressionChain[i], _operatorChain[i - 1]);
            return res;
        }

        private Func<double> CombineFuncs(Func<double> item1, Func<double> item2, ExpressionOperator op)
        {
            switch(op)
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

        public static implicit operator ExpressionInt(ExpressionDouble expressionDouble)
        {
            return new ExpressionInt(expressionDouble.UsedWidgetIDs, () => (int)expressionDouble.Execute());
        }
    }
}
