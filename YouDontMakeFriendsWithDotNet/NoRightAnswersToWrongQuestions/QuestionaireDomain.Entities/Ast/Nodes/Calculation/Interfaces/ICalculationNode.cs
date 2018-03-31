using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces
{
    public interface ICalculationNode : IAstNode
    {
        IEnumerable<DomainId<ICalculationNode>> Children { get; }
    }
}