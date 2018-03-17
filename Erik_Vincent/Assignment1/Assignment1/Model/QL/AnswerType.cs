using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL
{
    public enum AnswerType
    {
        Boolean,
        Integer,
        String,
        Decimal,
        Money,
        Date,
        Undefined
    }

    public static class AnswerTypeMethods
    {
        public static bool IsNumeric(this AnswerType at) => at == AnswerType.Integer || at == AnswerType.Money || at == AnswerType.Decimal;
    }
}
