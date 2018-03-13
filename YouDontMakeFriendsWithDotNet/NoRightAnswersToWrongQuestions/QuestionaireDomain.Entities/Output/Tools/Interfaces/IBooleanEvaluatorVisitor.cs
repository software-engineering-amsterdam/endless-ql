using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Output.Tools.Interfaces
{
    public interface IBooleanEvaluatorVisitor
    {
        bool Evaluate(Reference<IBooleanLogicNode> predicate);
    }
}