using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class DivideNode :
        CalculationBinaryExpressionNodeBase,
        IDivideNode
    {
        public DivideNode(
            Guid id,
            string definition,
            DomainId<ICalculationNode> leftCalculation,
            DomainId<ICalculationNode> rightCalculation)
            : base(id, definition, leftCalculation, rightCalculation)
        {
        }
    }
}