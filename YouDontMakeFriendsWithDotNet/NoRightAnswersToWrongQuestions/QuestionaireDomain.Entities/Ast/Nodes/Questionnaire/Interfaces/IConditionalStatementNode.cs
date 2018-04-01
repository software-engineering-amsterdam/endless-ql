using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IConditionalStatementNode : IStatementNode, INonTerminal
    {
        DomainId<IBooleanLogicNode> Predicate { get; }
        IEnumerable<DomainId<IStatementNode>> Consequent { get; }
        IEnumerable<DomainId<IStatementNode>> Alternative { get; }
    }
}