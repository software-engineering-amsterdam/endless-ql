using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IVariableUpdater
    {
        void Update(Reference<IVariableNode> variable, string value);
    }
}
