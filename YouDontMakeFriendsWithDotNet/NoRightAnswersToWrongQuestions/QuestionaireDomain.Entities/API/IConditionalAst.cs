using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IConditionalAst : IAstNode
    {
        string QuestionName { get; }
    }
}
