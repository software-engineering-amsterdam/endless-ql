using System;

namespace Assignment1.Model.QL.RenderTree
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
