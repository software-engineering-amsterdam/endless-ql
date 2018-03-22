using System;

namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionDate : RenderableQuestion
    {
        public RenderableQuestionDate(string id, string label) : base(id, label)
        {
            Value = DateTime.Today;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
