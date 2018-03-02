using QL_Vizualizer.Expression.Types;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression
{
    public static class ExpressionExceptions
    {
        public static NotImplementedException NoCombineImplemented(Type t1, Type t2, ExpressionOperator op)
        {
            return new NotImplementedException(string.Format("Combination of {0} and {1} not implemented for operator {2}", t1, t2, op));
        }

        public static InvalidOperationException NoCombine(Type t1, Type t2, ExpressionOperator op)
        {
            throw new InvalidOperationException(string.Format("Cannot combine {0} and {1} with operator {2}", t1, t2, op));
        }

        public static NotImplementedException NoCompareImplemented(Type t1, Type t2, ExpressionOperator op)
        {
            return new NotImplementedException(string.Format("Comparison for {0} and {1} not implemented for operator {2}", t1, t2, op));
        }

        public static InvalidOperationException NoCompare(Type t1, Type t2, ExpressionOperator op)
        {
            throw new InvalidOperationException(string.Format("Cannot compare {0} and {1} with operator {2}", t1, t2, op));
        }
    }
}
