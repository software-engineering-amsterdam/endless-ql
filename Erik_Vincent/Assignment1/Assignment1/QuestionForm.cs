using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal class QuestionForm
    {
        public string Id { get; }
        public Dictionary<string, Question> Questions { get; }

        public QuestionForm(string id)
        {
            Id = id;
            Questions = new Dictionary<string, Question>();
        }

        public void AddQuestion(string id, string label, string qtype)
        {
            Console.WriteLine(id + " : " + label + " : " + qtype);
            Questions.Add(id, new QuestionBool(id, label));
        }
    }
}
