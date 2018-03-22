namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionBool : RenderableQuestion
    {
        public RenderableQuestionBool(string id, string label) : base(id, label)
        {
            Value = false;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
