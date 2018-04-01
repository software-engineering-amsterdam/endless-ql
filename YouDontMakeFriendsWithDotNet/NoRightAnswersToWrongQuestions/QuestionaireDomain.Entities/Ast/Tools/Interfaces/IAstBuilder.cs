using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Tools.Interfaces
{
    public interface IAstBuilder
    {
        DomainId<IQuestionnaireRootNode> BuildForm(string definition);
    }
}
