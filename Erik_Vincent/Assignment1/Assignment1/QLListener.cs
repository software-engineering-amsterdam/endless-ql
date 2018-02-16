using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal class QLListener : QLBaseListener
    {
        public QuestionForm Form { get; private set; }

        public override void EnterF([NotNull] QLParser.FContext context)
        {
            Form = new QuestionForm(context.ID().ToString());
        }

        public override void EnterQuestion(QLParser.QuestionContext context)
        {
            Form.AddQuestion(context.ID().ToString(), context.LABEL().ToString(), context.QTYPE().ToString());
        }
    }
}
