namespace QuestionaireDomain.Entities.API.AstNodes
{
    public interface IAstNode : IDomainItem
    {
        void Accept(IAstVisitor visitor);
        string Definition { get; }
    }
}