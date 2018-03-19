using System;

namespace Assignment1.Model.QL.RenderTree
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
