using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.API
{
    public interface IPredicateCreator
    {
        Reference<IBooleanLogicNode> Create(string definition);
    }
}