using QuestionaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IVariableUpdater
    {
        void Update(Reference<IVariableNode> variable, string value);
    }
}
