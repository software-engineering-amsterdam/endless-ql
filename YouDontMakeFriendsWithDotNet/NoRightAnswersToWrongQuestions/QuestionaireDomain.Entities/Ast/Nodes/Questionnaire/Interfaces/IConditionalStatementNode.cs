using System.Collections.Generic;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IConditionalStatementNode : IStatementNode, INonTerminal
    {
        Reference<IBooleanLogicNode> Predicate { get; }
        IEnumerable<Reference<IStatementNode>> Consequent { get; }
        IEnumerable<Reference<IStatementNode>> Alternative { get; }
    }
}