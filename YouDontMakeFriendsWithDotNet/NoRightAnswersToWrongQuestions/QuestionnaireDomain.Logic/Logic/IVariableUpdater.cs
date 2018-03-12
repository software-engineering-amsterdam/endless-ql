using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IVariableUpdater
    {
        void Update(Reference<IVariableNode> variable, string value);
    }
}
