using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Expression
{
    public static class ExpressionTypes
    {
        public static ExpressionType[] Numeric = new ExpressionType[] { ExpressionType.Int, ExpressionType.Double };
        public static ExpressionType[] Logical = new ExpressionType[] { ExpressionType.Bool };
    }
}
