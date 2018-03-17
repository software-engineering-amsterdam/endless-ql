using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IQuestionnaireOutputCreator
    {
        bool Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode);
        void CreateOrUpdate(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}
