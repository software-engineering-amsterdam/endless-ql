using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Questionnaire
{
    public interface IConditionalStatementNode : IStatementNode, INonTerminal
    {
        string ConditionDefinition { get; }
        Reference<IBooleanLogicNode> Predicate { get; }
        IEnumerable<Reference<IStatementNode>> Consequent { get; }
        IEnumerable<Reference<IStatementNode>> Alternative { get; }
    }
}