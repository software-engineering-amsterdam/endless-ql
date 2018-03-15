using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL
{
    public class QuestionInt : Question
    {
        public QuestionInt(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
