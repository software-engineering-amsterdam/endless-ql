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
                if (expressionValue.Type == typeof(double))
                    return CombineWithDouble(expressionValue as TypedExpressionValue<double>, op);
                else if (expressionValue.Type == typeof(int))
                    return CombineWithInt(expressionValue as TypedExpressionValue<int>, op);

                throw ExpressionExceptions.NoCombineImplemented(Type, expressionValue.Type, op);
            }

            throw ExpressionExceptions.NoCombine(Type, expressionValue.Type, op);
        }

        private TypedExpressionValue<int> CombineWithInt(TypedExpressionValue<int> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.Plus:
                    return new ExpressionInt(CombineWidgets(item), () => { return Expression() + item.Expression(); });
                case ExpressionOperator.Minus:
                    return new ExpressionInt(CombineWidgets(item), () => { return Expression() - item.Expression(); });
                case ExpressionOperator.Multiply:
                    return new ExpressionInt(CombineWidgets(item), () => { return Expression() * item.Expression(); });
                case ExpressionOperator.Divide:
                    return new ExpressionInt(CombineWidgets(item), () => { return Expression() / item.Expression(); });
            }

            // Operator was not implemented
            throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
        }

        private TypedExpressionValue<double> CombineWithDouble(TypedExpressionValue<double> item, ExpressionOperator op)
        {

            switch(op)
            {
                case ExpressionOperator.Plus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return ((double)Expression()) + item.Expression(); });
                case ExpressionOperator.Minus:
                    return new ExpressionDouble(CombineWidgets(item), () => { return ((double)Expression()) - item.Expression(); });
                case ExpressionOperator.Multiply:
                    return new ExpressionDouble(CombineWidgets(item), () => { return ((double)Expression()) * item.Expression(); });
                case ExpressionOperator.Divide:
                    return new ExpressionDouble(CombineWidgets(item), () => { return ((double)Expression()) / item.Expression(); });
            }

            // Operator was not implemented
            throw ExpressionExceptions.NoCombineImplemented(Type, item.Type, op);
        }
        #endregion

        #region Compare
        public override TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op)
        {
            if(ValidCompare(expressionValue, op))
            {
                if (expressionValue.Type == typeof(double))
                    return CompareDouble(expressionValue as TypedExpressionValue<double>, op);
                else if (expressionValue.Type == typeof(int))
                    return CompareInt(expressionValue as TypedExpressionValue<int>, op);
                throw ExpressionExceptions.NoCompareImplemented(Type, expressionValue.Type, op);
            }
            throw ExpressionExceptions.NoCompare(Type, expressionValue.Type, op);

        }

        private TypedExpressionValue<bool> CompareDouble(TypedExpressionValue<double> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() > item.Expression(); });
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() >= item.Expression(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() < item.Expression(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() <= item.Expression(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() == item.Expression(); });
            }
            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }

        private TypedExpressionValue<bool> CompareInt(TypedExpressionValue<int> item, ExpressionOperator op)
        {
            switch (op)
            {
                case ExpressionOperator.GreaterThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() > item.Expression(); });
                case ExpressionOperator.GreaterEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() >= item.Expression(); });
                case ExpressionOperator.LessThan:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() < item.Expression(); });
                case ExpressionOperator.LessEquals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() <= item.Expression(); });
                case ExpressionOperator.Equals:
                    return new ExpressionBool(CombineWidgets(item), () => { return Expression() == item.Expression(); });
            }
            throw ExpressionExceptions.NoCompareImplemented(Type, item.Type, op);
        }
        #endregion
    }
}
