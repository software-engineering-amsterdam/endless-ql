using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class DivideNode : 
        CalculationBinaryExpressionNodeBase, 
        IDivideNode
    {
        public DivideNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) 
            : base(id, definition, leftCalculation,rightCalculation)
        {
        }
    }
}