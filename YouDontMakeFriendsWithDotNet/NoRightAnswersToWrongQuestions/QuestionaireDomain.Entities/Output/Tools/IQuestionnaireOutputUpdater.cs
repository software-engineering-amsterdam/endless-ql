using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    public interface IQuestionnaireOutputUpdater
    {
        bool OutputExistsFor(Reference<IQuestionnaireRootNode> questionnaireRootNode);
        void DeleteOutputFor(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}