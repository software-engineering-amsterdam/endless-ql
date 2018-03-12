using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireModelCreator
    {
        bool Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode);
        void Create(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}
