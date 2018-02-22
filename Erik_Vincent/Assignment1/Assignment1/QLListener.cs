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
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();

        public override void ExitFile(QL.FileContext context)
        {
            Forms = context.result;
        }

        public override void ExitQuestion(QL.QuestionContext context)
        {
            _questions.Add(context.result.Id, context.result);
        }

        public override void ExitExpressionId(QL.ExpressionIdContext context)
        {
            try
            {
                context.result.Question = _questions[context.result.Id];
            }
            catch (KeyNotFoundException e)
            {
                Console.WriteLine("ERROR: The name '" + context.result.Id + "' does not exist in the current context.");
                throw;
            }
        }
    }
}
