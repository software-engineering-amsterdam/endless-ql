using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class CalculationVariableNode :
        VariableNodeBase,
        ICalculationVariableNode
    {
        public CalculationVariableNode(Guid id, string variableName)
            : base(id, variableName)
        {
        }

        public IEnumerable<DomainId<ICalculationNode>> Children => new List<DomainId<ICalculationNode>>();
    }
}