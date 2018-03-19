namespace Assignment1.Model.QL.RenderTree
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
