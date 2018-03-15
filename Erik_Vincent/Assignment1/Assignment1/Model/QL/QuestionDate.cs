using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model.QL
{
    public class QuestionDate : Question
    {
        public QuestionDate(string id, string label) : base(id, label)
        {
            Value = DateTime.Today;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
