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
        public List<Content> Content { get; } = new List<Content>();
        public Dictionary<string, Question> Questions { get; } = new Dictionary<string, Question>();

        public QuestionForm(string id)
        {
            Id = id;
        }

        public void AddQuestion(Question question)
        {
            Content.Add(question);
            Questions.Add(question.Id, question);
        }

        public void AddStatement()
        {
            
        }
    }
}
