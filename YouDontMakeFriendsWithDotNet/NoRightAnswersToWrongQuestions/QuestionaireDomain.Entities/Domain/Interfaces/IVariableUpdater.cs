using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableUpdater
    {
        void Update(DomainId<IQuestionNode> node, dynamic value);
    }
}
