using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Expression.Types
{
    public enum ExpressionOperator
    {
        // Calculation
        Plus,
        Minus,
        Multiply,
        Divide,
        
        // Logical
        And,
        Or,

        // Comparison
        GreaterThan,
        LessThan,
        Equals,
        GreaterEquals,
        LessEquals,

        // Not set
        Undefined
    }
}
