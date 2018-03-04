namespace QuestionaireDomain.Entities.API.AstNodes
{
    public interface IAstNode : IDomainItem
    {
        void Accept(IAstVisitor visitor);
    }

    public interface IHasDefinition
    {
        string Definition { get; }
    }
}