using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IVariableUpdater
    {
        void Update(Reference<IQuestionNode> node, dynamic value);
    }
}
