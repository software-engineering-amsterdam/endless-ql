using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

[assembly: InternalsVisibleTo("Assignment1Tests")]

namespace Assignment1
{
    public class QuestionForm
    {
        public string Id { get; }
        public List<Content> Content { get; }


        public QuestionForm(string id, List<Content> content)
        {
            Id = id;
            Content = content;
        }
    }
}
