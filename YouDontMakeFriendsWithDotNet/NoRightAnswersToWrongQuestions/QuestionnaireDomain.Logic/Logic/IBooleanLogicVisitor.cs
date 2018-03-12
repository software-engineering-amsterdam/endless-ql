using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IBooleanLogicVisitor
    {
        bool Evaluate(Reference<IBooleanLogicNode> predicate);
    }
}