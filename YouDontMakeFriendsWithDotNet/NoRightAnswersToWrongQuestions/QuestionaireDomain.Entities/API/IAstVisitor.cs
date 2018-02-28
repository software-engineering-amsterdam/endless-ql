namespace QuestionaireDomain.Entities.API
{
    public interface IAstVisitor
    {
        void Visit(IAstVisitor visitor);
    }
}