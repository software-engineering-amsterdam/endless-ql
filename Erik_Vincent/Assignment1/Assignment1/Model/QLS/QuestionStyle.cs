namespace Assignment1.Model.QLS
{
    public class QuestionStyle : IContent
    {
        public readonly string Id;
        public Style Style = new Style();

        public QuestionStyle(string id)
        {
            Id = id;
        }

        public void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
