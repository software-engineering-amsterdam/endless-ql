namespace Assignment1.Model.QL.RenderTree
{
    public class RenderableQuestionString : RenderableQuestion
    {
        public RenderableQuestionString(string id, string label) : base(id, label)
        {
            Value = "";
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
