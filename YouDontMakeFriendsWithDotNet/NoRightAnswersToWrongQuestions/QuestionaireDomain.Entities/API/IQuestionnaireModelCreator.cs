using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireModelCreator
    {
        bool Validate(IQuestionnaireRootNode questionnaireRootNode);
        void Create(IQuestionnaireRootNode questionnaireRootNode);
    }
}
