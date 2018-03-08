namespace Assignment1.Model.QLS
{
    public interface IContentVisitor
    {
        void Visit(Section section);
        void Visit(QuestionStyle question);
    }

    public interface IContent
    {
        void Accept(IContentVisitor visitor);
    }
}
