using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1.Model
{
    public class QuestionMoney : Question
    {
        public QuestionMoney(string id, string label) : base(id, label)
        {
            Value = new Decimal(0);
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
