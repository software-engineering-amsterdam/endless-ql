namespace Assignment1.Model.QLS
{
    public class QuestionStyle : IContent
    {
        public Style Style = new Style();

        public QuestionStyle(string id)
        {

        }

        public void Accept(IContentVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
