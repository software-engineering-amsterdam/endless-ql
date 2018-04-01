using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class MultiplyNode : 
        CalculationBinaryExpressionNodeBase, 
        IMultiplyNode
    {
        public MultiplyNode(
            Guid id,
            string definition,
            DomainId<ICalculationNode> leftCalculation,
            DomainId<ICalculationNode> rightCalculation) 
            : base(id, definition, leftCalculation, rightCalculation)
        {
        }
    }
}