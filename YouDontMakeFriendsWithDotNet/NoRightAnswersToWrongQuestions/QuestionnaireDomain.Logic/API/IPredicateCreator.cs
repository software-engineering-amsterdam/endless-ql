using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.API
{
    public interface IPredicateCreator
    {
        Reference<IBooleanLogicNode> Create(string definition);
    }
}