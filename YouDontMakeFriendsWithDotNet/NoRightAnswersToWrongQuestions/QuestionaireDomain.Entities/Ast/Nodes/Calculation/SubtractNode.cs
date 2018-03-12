using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class SubtractNode : 
        CalculationBinaryExpressionNodeBase, 
        ISubtractNode
    {
        public SubtractNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) 
            : base(id, definition, leftCalculation, rightCalculation)
        {
        }
    }
}