using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IBooleanEvaluatorVisitor
    {
        bool Evaluate(Reference<IBooleanLogicNode> predicate);
    }
}