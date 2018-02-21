namespace QuestionaireDomain.Entities.DomainObjects
{
    public interface IAstVisitor
    {
        void Visit(IAstVisitor visitor);
    }
}