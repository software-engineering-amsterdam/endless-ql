namespace Assignment1.Model.QL.RenderTree
{
    public class QuestionDecimal : Question
    {
        public QuestionDecimal(string id, string label) : base(id, label)
        {
            Value = 0;
        }

        public override void Accept(IQuestionVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
