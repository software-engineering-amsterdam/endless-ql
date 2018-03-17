using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL.Value
{
    interface IAnswerLogical
    {
        IAnswerValuable<bool> And<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> Or<U>(IAnswerValuable<U> right);

        IAnswerValuable<bool> Not();
    }
}
