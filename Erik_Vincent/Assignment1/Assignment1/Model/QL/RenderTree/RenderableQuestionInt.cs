namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionInt : RenderableQuestion
    {
        public RenderableQuestionInt(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
