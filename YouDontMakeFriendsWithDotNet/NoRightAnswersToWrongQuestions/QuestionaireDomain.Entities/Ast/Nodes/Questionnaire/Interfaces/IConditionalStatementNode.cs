using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IConditionalStatementNode : IStatementNode, INonTerminal
    {
        Reference<IBooleanLogicNode> Predicate { get; }
        IEnumerable<Reference<IStatementNode>> Consequent { get; }
        IEnumerable<Reference<IStatementNode>> Alternative { get; }
    }
}