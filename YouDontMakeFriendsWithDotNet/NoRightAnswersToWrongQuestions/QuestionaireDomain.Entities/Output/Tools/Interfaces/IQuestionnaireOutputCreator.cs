using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IQuestionnaireOutputCreator
    {
        void CreateOrUpdate(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}
