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
            if(ValidCombine(expressionValue, op))
            {
                if (expressionValue.Type == typeof(int))
                    return CombineWithInt(expressionValue as TypedExpressionValue<int>, op);
                else if (expressionValue.Type == typeof(double))
                    return CombineWithDouble(expressionValue as TypedExpressionValue<double>, op);

                throw ExpressionExceptions.NoCombineImplemented(Type, expressionValue.Type, op);
            }
            throw ExpressionExceptions.NoCombine(Type, expressionValue.Type, op);
        }

        private ExpressionDouble CombineWithDouble(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() + item.Expression(); });
                case ExpressionOperator.Minus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() - item.Expression(); });
                case ExpressionOperator.Multiply:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() * item.Expression(); });
                case ExpressionOperator.Divide:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() / item.Expression(); });
            }
            throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
        }

        private ExpressionDouble CombineWithInt(TypedExpressionValue<int> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() + ((double)item.Expression()); });
                case ExpressionOperator.Minus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() - ((double)item.Expression()); });
                case ExpressionOperator.Multiply:                                                   
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() * ((double)item.Expression()); });
                case ExpressionOperator.Divide:                                                     
                    return new ExpressionDouble(CombineWidgets(item), () => { return Expression() / ((double)item.Expression()); });
            }
            throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
        }


        #endregion
        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if(ValidCompare(expressionValue, op))
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
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() >= item.Expression(); });
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() > item.Expression(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() <= item.Expression(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() < item.Expression(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() == item.Expression(); });
            }

            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }

        private ExpressionBool CompareWithDouble(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() >= item.Expression(); });
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() > item.Expression(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() <= item.Expression(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() < item.Expression(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() == item.Expression(); });
            }

            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }
    }
}
