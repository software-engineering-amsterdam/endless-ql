using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression.Types
{
    public abstract class ExpressionValue
    {
        protected Type[] CompatibleTypes { get; private set; }
        protected ExpressionOperator[] CompatibleOperators { get; private set; }
        public Type Type { get; private set; }
        public string[] UsedWidgetIDs { get; protected set; }

        public ExpressionValue(Type[] compatibleTypes, ExpressionOperator[] compatibleOperators, Type type, string[] usedWidgetIDs)
        {
            CompatibleTypes = compatibleTypes;
            CompatibleOperators = compatibleOperators;
            Type = type;
            UsedWidgetIDs = usedWidgetIDs;
        }

        public abstract ExpressionValue Combine(ExpressionValue expressionValue, ExpressionOperator op);

        public abstract TypedExpressionValue<bool> Compare(ExpressionValue expressionValue, ExpressionOperator op);

        protected string[] CombineWidgets(ExpressionValue expressionValue)
        {
            return UsedWidgetIDs.Concat(expressionValue.UsedWidgetIDs).Distinct().ToArray();
        }
    }
}
