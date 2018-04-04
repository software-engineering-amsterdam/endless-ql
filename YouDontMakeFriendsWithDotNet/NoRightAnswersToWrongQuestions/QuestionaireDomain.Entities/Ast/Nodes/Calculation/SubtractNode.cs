using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class SubtractNode :
        CalculationBinaryExpressionNodeBase,
        ISubtractNode
    {
        public SubtractNode(
            Guid id,
            string definition,
            DomainId<ICalculationNode> leftCalculation,
            DomainId<ICalculationNode> rightCalculation)
            : base(id, definition, leftCalculation, rightCalculation)
        {
        }
    }
}