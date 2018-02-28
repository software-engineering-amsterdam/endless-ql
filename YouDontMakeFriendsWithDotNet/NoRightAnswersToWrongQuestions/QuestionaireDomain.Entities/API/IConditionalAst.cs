using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IConditionalAst : IQuestionnaireAstNode
    {
        string QuestionName { get; }
    }
}
