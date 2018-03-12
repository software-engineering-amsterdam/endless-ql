using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces
{
    public interface IAstNode : IDomainItem
    {
        string Definition { get; }
    }
}