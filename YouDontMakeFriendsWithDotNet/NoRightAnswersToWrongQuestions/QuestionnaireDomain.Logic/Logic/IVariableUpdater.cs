
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IVariableUpdater
    {
        void Update(Reference<IVariableNode> variable, string value);
    }
}
