namespace QuestionaireDomain.Entities.API.AstNodes
{
    public interface IAstNode : IDomainItem
    {
        string Definition { get; }
    }
}