using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL.Value
{
    public interface IAnswerValuable<out T>
    {
        T Value { get; }
        AnswerType Type { get; }
        bool IsUndefined { get; }
    }
}
