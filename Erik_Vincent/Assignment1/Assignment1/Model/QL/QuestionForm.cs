using System.Collections.Generic;
using System.Runtime.CompilerServices;

[assembly: InternalsVisibleTo("Assignment1Tests")]

namespace Assignment1.Model.QL
{
    public class QuestionForm
    {
        public string Id { get; }
        public List<Question> Questions { get; }
        public List<string> Warnings;

        public QuestionForm(string id, List<Question> questions)
        {
            Id = id;
            Questions = questions;
        }
    }
}
