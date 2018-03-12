using System;
using QuestionaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Calculation
{
    internal class AddNode : 
        CalculationBinaryExpressionNodeBase, 
        IAddNode
    {
        public AddNode(
            Guid id,
            string definition,
            Reference<ICalculationNode> leftCalculation,
            Reference<ICalculationNode> rightCalculation) : 
            base(id, definition,leftCalculation,rightCalculation)
        {
        }
    }
}