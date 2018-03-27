using System.Collections.Generic;
using System.Runtime.CompilerServices;

[assembly: InternalsVisibleTo("Assignment1Tests")]

namespace Assignment1.Model.QL.RenderTree
{
    public class QuestionForm
    {
        public string Id { get; }
        public List<RenderableQuestion> Questions { get; }
        public List<string> Warnings;

        public QuestionForm(string id, List<RenderableQuestion> questions)
        {
            Id = id;
            Questions = questions;
        }
    }
}
