using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal class QLListener : QLBaseListener
    {
        public List<QuestionForm> Forms { get; private set; }

        public override void ExitFile(QL.FileContext context)
        {
            Forms = context.result;
        }
    }
}
