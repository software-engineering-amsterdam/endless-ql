using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionAst : IAstNode
    {
        string Name { get; }
        string Text { get; }
    }
}
